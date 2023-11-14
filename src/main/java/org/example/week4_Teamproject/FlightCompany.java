package org.example.week4_Teamproject;

public class FlightCompany extends FlightManager{
    private String companyName;
    private Flight[] companyflights=new Flight[1000];
    private int totalCompanySell;

    public FlightCompany() {
        super();
    }

    // 특정 항공사만 검색
    void searchCompanyFlight(String input) {
        for(int i = 0; i<super.flights.length; i++) {
            if(super.flights[i].flightCompany.equals("input")) {
                System.out.println(super.flights[i]);
            }
        }
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "총 판매금액은" + this.totalCompanySell + " 입니다.";
        return str;
    }


}
