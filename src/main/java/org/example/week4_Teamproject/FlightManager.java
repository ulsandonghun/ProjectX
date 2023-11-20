package org.example.week4_Teamproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlightManager {
    int count;
    Flight[] flights;
    static final int CAPACITY = 1000;
    public static Scanner scan = new Scanner(System.in);

    public FlightManager() {
        super();
        this.flights = new Flight[this.CAPACITY];
    }

    void makeFlight(String filename) {
        try (Scanner file = new Scanner(new File(filename));) {
            while (file.hasNextLine()) { // EOF
                String str = file.nextLine();
                String[] tmp = str.split("\t");
                int day = Integer.parseInt(tmp[0]);
                String flightCompany = tmp[1];
                String departure = tmp[2];
                String destination = tmp[3];
                String[] departureT = tmp[4].split(":");
                int departureHour = Integer.parseInt(departureT[0]);
                int departureMin = Integer.parseInt(departureT[1]);
                String[] arrivalT = tmp[5].split(":");
                int arrivalHour = Integer.parseInt(arrivalT[0]);
                int arrivalMin = Integer.parseInt(arrivalT[1]);
                int row = Integer.parseInt(tmp[6]);
                int col = Integer.parseInt(tmp[7]);
                int seatPrice = Integer.parseInt(tmp[8]);

                this.addFlight(new Flight(day, flightCompany, departure, destination, departureHour, departureMin,
                        arrivalHour, arrivalMin, row, col, seatPrice)); // 혹시 모를 공백을 위해 .trim 사용
            }
            System.out.println("항공편 정보를 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("파일이름을 확인해 주세요.");
        }
    }

    void addFlight(Flight f) {
        if (this.count < this.flights.length)
            this.flights[this.count++] = f;
        else
            System.out.println("더이상 항공편을 등록할 수 없습니다.");
    }

    public Flight[] searchFlight(Flight[] flights, String start, String dest, int day) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                Flight flight = flights[i];
                if (flight.destination.equals(dest) && flight.departure.equals(start) && flight.day == day)
                    result.add(flight);
            }
        }
        return result.toArray(new Flight[0]);
    }

    public Flight[] advancedSearchFlightDirect(Flight[] flights, String departure, String destination, int day) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                Flight flight = flights[i];
                if (flight.destination.equals(destination) && flight.departure.equals(departure) && flight.day == day)
                    result.add(flight);
            }
        }
        return result.toArray(new Flight[0]);
    }

    public Flight[] advancedSearchFlightVia(Flight[] flights, String departure, String destination, int day) {
        List<Flight> result = new ArrayList<>();
        if (!destination.equals("뉴욕")) {
            System.out.println("도착지가 뉴욕일 경우에만 경유검색을 지원합니다.");

            for (int i = 0; i < flights.length; i++) {
                if (flights[i] != null) {
                    Flight flight = flights[i];
                    if (flight.destination.equals(destination) && flight.departure.equals(departure) && flight.day == day)
                        result.add(flight);
                }
            }
            return result.toArray(new Flight[0]);

        }

        for (int i = 0; i < flights.length; i++) {
            if (flights[i] == null) {
                break;
            }
            Flight departureFlight = flights[i];
            if (departureFlight.departure.equals(departure) && departureFlight.day == day) {
                for (int j = 0; j < flights.length; j++) {
                    if (flights[j] == null) {
                        break;
                    }
                    Flight arrivalFlight = flights[j];
                    if (arrivalFlight.departure.equals(departureFlight.destination) && arrivalFlight.destination.equals(destination) && arrivalFlight.day == day) {
                        if((departureFlight.arrivalHour < arrivalFlight.departureHour)){

                            result.add(departureFlight);
                            result.add(arrivalFlight);
                        }
                    }
                }
            }
        }

        return result.toArray(new Flight[0]);

    }

    public Flight[] advancedSearchFlightPrice(Flight[] flights, String departure, String destination, int day, int price) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                Flight flight = flights[i];
                if (flight.destination.equals(destination) && flight.departure.equals(departure)
                        && flight.seatPrice <= price)
                    result.add(flight);
            }
        }
        return result.toArray(new Flight[0]);
    }

    // 수정 남음
    public Flight selectFlight(Flight[] flights) {
        Flight purchaseFlight = new Flight();
        if (flights.length > 1) {
            System.out.println("검색된 항공편중 몇번째에 있는 항공편을 선택하실것입니까?");
            for (int i = 0; i < flights.length; i++) {
                Flight flight = flights[i];
            }
            int num = 0;
            try {
                System.out.println("몇번을 선택하시겠습니까?");
                num = scan.nextInt();
                int len = flights.length;
                if(num<1||num>len-1) {
                    System.out.println("입력 범위를 벗어났습니다.");
                    // 이거 실행되고 뭐함?
                }
            } catch(InputMismatchException e) {
                System.out.println("메뉴를 확인해주세요.");
                scan.nextLine();
                // 이거 실행되고 어디로 돌아감..?
            }
            purchaseFlight = flights[num + 1];
            return purchaseFlight;
        } else if (flights.length == 1) {
            return flights[0];
        } else {
            System.out.println("검색된 항공편이 없습니다.");
            return null;
        }
    }

    String purchaseTicket(Flight flight,Member member) {
        // 좌석 현황 출력
        int rows = 10; // 비행기의 행 수
        int seats = 2; // 각 행의 좌석 수

        for (int i = 1; i <= rows; i++) {
            for (char seat = 'A'; seat < 'A' + seats; seat++) {
                System.out.print(i + "" + seat + " ");
            }
            System.out.println();
        }
        // 빈좌석 골랐을 경우 구매 진행
        System.out.println("구매할 좌석을 선택해주세요.");
        String seat = scan.nextLine();
        String space = " ";
        String[] rowColum = seat.split(space);
        System.out.println("티켓 구매가 완료되었습니다. ");
        return rowColum.toString();
    }


    @Override
    public String toString() {
        String str = "";
        for (Flight f : flights) {
            if (f != null) {
                str += f.departure + " ";
                str += f.destination + " ";
                str += f.seatPrice + " ";
                str += "\n";
            } else
                break;
        }
        return str;
    }
}
