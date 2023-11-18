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

    public FlightManager() {
        super();
        this.flights = new Flight[this.CAPACITY];
    }

    public static Scanner scan = new Scanner(System.in);

    void makeFlight(String filename) {
        try (Scanner file = new Scanner(new File(filename));) {
            while (file.hasNextLine()) { // EOF
                // 비행기 한 대 입력
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
            System.out.println("파일이름을 확인해 주세요.");
        }
    }

    void addFlight(Flight f) {
        if (this.count < this.flights.length) {
            this.flights[this.count++] = f;
        } else
            System.out.println("더이상 항공편을 등록할 수 없습니다.");
    }

    public Flight[] searchFlight(Flight[] flights, String start, String dest, int day) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                Flight flight = flights[i];
                if (flight.destination.equals(dest) && flight.departure.equals(start) && flight.day == day) {
                    result.add(flight);
                }
            }

        }
        return result.toArray(new Flight[0]);
    }

    public Flight[] advancedSearchFlightDirect(Flight[] flights, String departure, String destination, int day) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {

                Flight flight = flights[i];
                if (flight.destination.equals(destination) && flight.departure.equals(departure) && flight.day == day) {
                    result.add(flight);
                }
            }

        }
        return result.toArray(new Flight[0]);
    }

    public Flight[] advancedSearchFlightVia(Flight[] flights, String departure, String destination, int day) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {

                for (int j = 0; j < flights.length; j++) {
                    if (i != j) {
                        Flight firstFlight = flights[i];
                        Flight secondFlight = flights[j];
                        if (firstFlight.departure.equals(departure)
                                && firstFlight.destination.equals(secondFlight.departure)
                                && secondFlight.destination.equals(destination) && firstFlight.day == day
                                && secondFlight.day == day) {
                            result.add(secondFlight);
                            result.add(firstFlight);
                        }
                    }
                }
            }
        }
        return result.toArray(new Flight[0]);
    }

    public Flight[] advancedSearchFlightPrice(Flight[] flights, String departure, String destination, int price) {
        List<Flight> result = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                Flight flight = flights[i];
                if (flight.destination.equals(destination) && flight.departure.equals(departure)
                        && flight.seatPrice <= price) {
                    result.add(flight);
                }
            }
        }
        return result.toArray(new Flight[0]);

    }

    public Flight selectFlight(Flight[] flights) {
        Flight purchaseFlight = new Flight();
        int num=0;
        if (flights.length > 1) {
            System.out.println("검색된 항공편중 몇번째에 있는 항공편을 선택하실것입니까?");
            for (int i = 0; i < flights.length; i++) {
                Flight flight = flights[i];
            }
            try {

                System.out.println("몇번을 선택하시겠습니까?");
                Scanner s = new Scanner(System.in);
                 num = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자로 입력해 주시기 바랍니다. 올바르지 않은 입력입니다.");
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
