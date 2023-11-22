package org.example.week4_Teamproject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightMain {
    private  static Scanner scan=new Scanner(System.in);
    private static byte viacheck =0;

    public static void main(String[] args) {
        System.out.println("안녕하세요 '항공항공'입니다.");
        // 멤버 객체 생성
        System.out.println("1) 멤버 객체 생성");
        Member[] members = new Member[6];
        members[0] = new Member(15, "김병진");
        members[1] = new Member(25, "김지민");
        members[2] = new Member(35, "유지현");
        members[3] = new Member(45, "이지민");
        members[4] = new Member(55, "정수흠");
        members[5] = new Member(66, "최동훈");
        System.out.println("회원 정보가 등록되었습니다.");
        System.out.println();

        // 항공편 객체 생성
        System.out.println("2) 항공편 객체 생성");
        FlightManager f = new FlightManager();
        f.makeFlight("flightslist.txt");
        System.out.println();

        // 메뉴 - 항공사 검색 메뉴 추가
        while (true) {
            System.out.println();
            System.out.println("1) 항공편 기본 검색 2) 항공권 선택 검색(직항만) 3) 항공권 선택 검색(경유만) 4) 항공권 선택 검색(가격) "
                    + " 5) 항공사 검색 6) 사용자 정보 확인  7) readme 8) 종료");
            System.out.print("필요하신 서비스를 선택해주세요 : ");
            int choice = 0;
            try {
                choice = scan.nextInt();
                if (choice < 1 || choice > 8) {
                    System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                scan.nextLine();
                continue;
            }
            switch (choice) {
                case 1: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = "";
                    String to = "";
                    int day = 0;
                    try {
                        from = scan.next();
                        to = scan.next();
                        day = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    //변경 부분. 기본검색시 경유, 직항 출력 나눠서 인덱스와 같이 출력뒤 배열 합치기
                    Flight[] directFlights = f.searchFlight(f.flights, from, to, day);
                    System.out.println("*직항편 목록*");
                    for (int i = 0; i < directFlights.length; i++) {
                        System.out.println((i+1)+": "+directFlights[i].showStatus());
                    }
                    Flight[] viaFlights = f.advancedSearchFlightVia(f.flights, from, to, day);

                    System.out.println("*경유항공편 출력*");
                    if (viaFlights != null) {
                        for (int i = 0; i < viaFlights.length; i++) {
                            if ((i) % 2 == 0) {
                                System.out.println("-------------------------------------------------------------");
                            }
                            System.out.println((i+1)+": "+viaFlights[i].showStatus());
                        }
                    }
                    Flight[] flight=null;
                    if(viaFlights!=null) {
                        viacheck =1;// 경유 체크 비트 추가
                         flight = new Flight[directFlights.length + viaFlights.length];
                        System.arraycopy(directFlights, 0, flight, 0, directFlights.length);
                        System.arraycopy(viaFlights, 0, flight, directFlights.length, viaFlights.length);
                    }else{
                         flight=directFlights;
                    }
                    // 위에까지 변경부분.
                    if (flight.length == 0) {
                        System.out.println("검색된 항공편이 없습니다. 메인화면으로 돌아갑니다.");
                    } else {
                        System.out.print("항공권을 구매하실 것입니까? (y/n): ");
                        String select = "";
                        String name = "";
                        try {
                            select = scan.nextLine();
                            if (select.equals("y")) {
                                System.out.print("사용자 이름을 입력해주세요: ");
                                name = scan.nextLine();
                                for (int i = 0; i < members.length; i++) {
                                    if (name.equals(members[i].name)) {
                                        if (viacheck != 1) {

                                            Flight selectedFlight = f.selectFlight(flight);
                                            System.out.println("선택된 표" + selectedFlight.showStatus());
                                            if (selectedFlight != null) {
                                                f.purchaseTicket(selectedFlight, members[i]);
                                                break;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            System.out.println("경유 노선을 구매합니다. 이경우 출발지 항공편 숫자를 입력하면 두개다 구매 가능합니다.");
                                            System.out.println("만약 직항편 구매를 원하시면 직항검색 기능으로 검색해주세요");


                                        }
                                    }
                                    if (i == members.length - 1) {
                                        System.out.println("일치하는 사용자가 없습니다. 메인화면으로 돌아갑니다.");
                                        continue;
                                    }
                                }
                            } else if (select.equals("n")) {
                                System.out.println("항공편을 구매하지 않습니다. 메인화면으로 돌아갑니다.");
                                continue;
                            } else {
                                System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                            scan.nextLine();
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = "";
                    String to = "";
                    int day = 0;
                    try {
                        from = scan.next();
                        to = scan.next();
                        day = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    //변경부분
                    Flight[] flight = f.advancedSearchFlightDirect(f.flights, from, to, day);

                    if (flight== null) {
                        System.out.println("검색된 항공편이 없습니다. 메인화면으로 돌아갑니다.");
                    } else {
                        for (int i = 0; i < flight.length; i++) {
                            System.out.println(flight[i].showStatus());
                        }
                        //변경부분
                        System.out.print("항공권을 구매하실 것입니까? (y/n): ");
                        String select = "";
                        String name = "";
                        try {
                            select = scan.nextLine();
                            if (select.equals("y")) {
                                System.out.print("사용자 이름을 입력해주세요: ");
                                name = scan.nextLine();
                                for (int i = 0; i < members.length; i++) {
                                    if (name.equals(members[i].name)) {
                                        Flight selectedFlight = f.selectFlight(flight);
                                        if (selectedFlight != null) {
                                            f.purchaseTicket(selectedFlight, members[i]);
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (i == members.length - 1) {
                                        System.out.println("일치하는 사용자가 없습니다. 메인화면으로 돌아갑니다.");
                                        continue;
                                    }
                                }
                            } else if (select.equals("n")) {
                                System.out.println("항공편을 구매하지 않습니다. 메인화면으로 돌아갑니다.");
                                continue;
                            } else {
                                System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                            scan.nextLine();
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = "";
                    String to = "";
                    int day = 0;
                    try {
                        from = scan.next();
                        to = scan.next();
                        day = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    Flight[] flight = f.advancedSearchFlightVia(f.flights, from, to, day);
                    //변경부분
                    if (flight== null) {
                        System.out.println("검색된 항공편이 없습니다. 메인화면으로 돌아갑니다.");
                    } else {
                        for (int i = 0; i < flight.length; i++) {
                            System.out.println(flight[i].showStatus());
                        }
                        //변경부분
                        System.out.print("항공권을 구매하실 것입니까? (y/n): ");
                        String select = "";
                        String name = "";
                        try {
                            select = scan.nextLine();
                            if (select.equals("y")) {
                                System.out.print("사용자 이름을 입력해주세요: ");
                                name = scan.nextLine();
                                for (int i = 0; i < members.length; i++) {
                                    if (name.equals(members[i].name)) {
                                        Flight selectedFlight = f.selectFlight(flight);
                                        if (selectedFlight != null) {
                                            f.purchaseTicket(selectedFlight, members[i]);
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (i == members.length - 1) {
                                        System.out.println("일치하는 사용자가 없습니다. 메인화면으로 돌아갑니다.");
                                        continue;
                                    }
                                }
                            } else if (select.equals("n")) {
                                System.out.println("항공편을 구매하지 않습니다. 메인화면으로 돌아갑니다.");
                                continue;
                            } else {
                                System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                            scan.nextLine();
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = "";
                    String to = "";
                    int day = 0;
                    try {
                        from = scan.next();
                        to = scan.next();
                        day = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("입력을 확인해주세요.");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();

                    int priceMax = 0;
                    try {
                        System.out.print("얼마 이하의 항공권을 찾으시나요: ");
                        priceMax = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                        scan.nextLine();
                        continue;
                    }
                    Flight[] flight = f.advancedSearchFlightPrice(f.flights, from, to, day, priceMax);
                    for (int i = 0; i < flight.length; i++) {
                        System.out.println(flight[i].showStatus());
                    }
                    if (flight.length == 0) {
                        System.out.println("검색된 항공편이 없습니다. 메인화면으로 돌아갑니다.");
                    } else {
                        System.out.print("항공권을 구매하실 것입니까? (y/n): ");
                        String select = "";
                        String name = "";
                        try {
                            select = scan.nextLine();
                            if (select.equals("y")) {
                                System.out.print("사용자 이름을 입력해주세요: ");
                                name = scan.nextLine();
                                for (int i = 0; i < members.length; i++) {
                                    if (name.equals(members[i].name)) {
                                        Flight selectedFlight = f.selectFlight(flight);
                                        if (selectedFlight != null) {
                                            f.purchaseTicket(selectedFlight, members[i]);
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (i == members.length - 1) {
                                        System.out.println("일치하는 사용자가 없습니다. 메인화면으로 돌아갑니다.");
                                        continue;
                                    }
                                }
                            } else if (select.equals("n")) {
                                System.out.println("항공편을 구매하지 않습니다. 메인화면으로 돌아갑니다.");
                                continue;
                            } else {
                                System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("메뉴 입력을 확인해주세요. 메인화면으로 돌아갑니다.");
                            scan.nextLine();
                        }
                    }
                    break;
                }
                case 5: { //특정 항공사 검색
                    System.out.print("검색하고자 하는 항공사를 입력해주세요: ");
                    String flightcompany=scan.next();
                    f.searchCompanyFlight(flightcompany);
                    break;
                }
                case 6: {
                    scan.nextLine();
                    System.out.print("검색하고 싶은 사람의 이름을 입력하세요: ");
                    String name = scan.nextLine();
                    boolean found = false;
                    for (int i = 0; i < members.length; i++) {
                        if (members[i].name.equals(name)) {
                            members[i].showTickets();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("등록되지 않은 사용자입니다.");
                    }
                    break;

                }
                case 7: {
                    System.out.println("*".repeat(80));
                    System.out.println("안녕하세요. 항공항공입니다.");
                    System.out.println("1) 기본 정보");
                    System.out.println("서비스 가능 일자: 5일 ~ 9일");
                    System.out.println("서비스 가능 공항: 인천, 베이징, 홍콩, 도쿄, 오사카, 뉴욕");
                    System.out.println("서비스 가능 항공사: 대한항공, 아시아나, 티웨이, 제주항공, 에어부산, 진에어");
                    System.out.println("등록 된 사용자: 김병진(15), 김지민(25), 유지현(35), 이지민(45), 정수흠(55), 최동훈(66)");
                    System.out.println();
                    System.out.println("2) 제공 기능");
                    System.out.println(" - 항공편 기본 검색: 출발지, 도착지, 출발일을 입력하여 모든 항공편을 검색할 수 있습니다.");
                    System.out.println(" - 항공편 선택 검색(직항만): 출발지, 도착지, 출발일을 입력하여 직항 항공편을 검색할 수 있습니다.");
                    System.out.println(" - 항공편 선택 검색(경유만): 출발지, 도착지, 출발일을 입력하여 경유 항공편을 검색할 수 있습니다.");
                    System.out.println(" - 항공권 선택 검색(가격) : 출발지, 도착지, 출발일, 최대가격을 입력하여 항공편을 검색할 수 있습니다.");
                    System.out.println(" - 항공사 검색: 항공사에 등록된 모든 항공편을 출력합니다.");
                    System.out.println(" - 사용자 정보 확인: 사용자의 정보와 구매한 항공권을 출력합니다.");
                    System.out.println("*".repeat(80));
                    System.out.println();
                    break;
                }
                case 8: {
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                }
            }
        }



    }

}

// 구매 기능 완성하기
// 1. 항공권 빈 좌석 출력하기
// 2. 구매할 좌석 입력받기
// 3. 구매 후 Member객체의 ticekts 배열에 티켓 추가하기
// 4. 구매 시 할인 가격 적용하기
// 5. 구매 시 항공편의 수익과 항공사의 수익 모두 증가시키기
// 메뉴 5번 기능 완성하기
// 검색할 항공사의 이름을 String으로 받기
// 검색된 항공사의 전체 항공편 출력 하기
// 검색된 항공사의 판매현황 출력 하기
// 시간 출력 수정하기
// 3:0의 경우 3:00, 3:5의 경우 3:05와 같이 분 단위를 모두 두 자리로 수정하기
// 검색된 항공편이 없는 경우 '조회가능한 항공편이 없습니다.'와 같이 출력하고 메인메뉴로 돌아가기
// 모든 메뉴에서 조회된 항공편이 없는 경우 이런 예외 처리 부분이 없음
// 가격 검색 기능 수행 시 입력된 날짜만 조회되는 것이 아니라 전체 날짜에서 검색이 이루어짐
// 입력한 날짜에서만 조회되도록 수정
//가능하면 1~4번 항공편 전부 출력 후 searchCompanyFlight 메소드 이용해서 특정 항공사의 항공편만 필터 가능하게 추가하기
// 선택사항 (위의 사항 구현 우선)
