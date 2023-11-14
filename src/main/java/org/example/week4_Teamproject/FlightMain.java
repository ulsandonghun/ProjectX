package org.example.week4_Teamproject;

import java.util.Scanner;

public class FlightMain {
    private  static Scanner scan=new Scanner(System.in);

    public static void main(String[] args) {
        // 멤버 객체 생성
        System.out.println("1) 멤버 객체 생성");
        Member[] members = new Member[6];
        members[0] = new Member(15, "김병진");
        ;
        members[1] = new Member(25, "김지민");
        members[2] = new Member(35, "유지현");
        members[3] = new Member(45, "이지민");
        members[4] = new Member(55, "정수흠");
        members[5] = new Member(66, "최동훈");

        // 항공편 객체 생성
        System.out.println("3) 항공편 객체 생성");
        FlightManager f = new FlightManager();
        f.makeFlight("filghtslist.txt");

        // 메뉴
        while (true) {
            System.out.println("안녕하세요 항공권 티켓 검색 프로그램입니다.");
            System.out.println("1) 항공편 기본 검색 2) 항공권 선택 검색(직항만) 3) 항공권 선택 검색(경유만) 4) 항공권 선택 검색(가격) 5) 사용자 정보 확인  6) 종료");
            System.out.print("필요하신 서비스를 선택해주세요 : ");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = scan.next();
                    String to = scan.next();
                    int day = scan.nextInt();
                    Flight[] flight = f.searchFlight(f.flights, from, to, day);
                    for (int i = 0; i < flight.length; i++) {
                        System.out.println(flight[i].showStatus());
                    }
                    Flight select = f.selectFlight(flight);
//				f.purchase(select);
                    break;
                }
                case 2: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = scan.next();
                    String to = scan.next();
                    int day = scan.nextInt();
                    Flight[] flight = f.advancedSearchFlightDirect(f.flights, from, to, day);
                    for (int i = 0; i < flight.length; i++) {
                        System.out.println(flight[i].showStatus());
                    }
                    Flight select = f.selectFlight(flight);
//				f.purchase(select);
                    break;
                }
                case 3: {
                    System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
                    String from = scan.next();
                    String to = scan.next();
                    int day = scan.nextInt();
                    Flight[] flight = f.advancedSearchFlightVia(f.flights, from, to, day);
                    for (int i = 0; i < flight.length; i++) {
                        System.out.println(flight[i].showStatus());
                    }
                    Flight select = f.selectFlight(flight);
//				f.purchase(select);
                    break;
                }
//			case 4: {
//				System.out.print("출발지, 목적지, 출발일을 입력하세요: ");
//				String from = scan.next();
//				String to = scan.next();
//				int day = scan.nextInt();
//				System.out.println("얼마 이하의 항공권을 찾으시나요: ");
//				int priceMax = scan.nextInt();
//				Flight[] flight = f.advancedSearchFlightPrice(f.flights, from, to, day, priceMax);
//				for (int i = 0; i < flight.length; i++) {
//					System.out.println(flight[i].showStatus());
//				}
//				Flight select = f.selectFlight(flight);
//				f.purchase(select);
//				break;
//			}
                case 5: {
                    System.out.print("검색하고 싶은 사람의 이름을 입력하세요: ");
                    String name = scan.nextLine();
                    for (int i = 0; i < members.length; i++) {
                        if (members[i].name.equals(name)) {
                            members[i].showTickets(members[i].tickets);
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                }
                default: {
                    System.out.println("제대로 입력했는지 다시 한번 확인해주세요. ");
                }
            }
        }

    }

}
