package org.example.week4_Teamproject;

public class Ticket {
    int seatPrice;
    String flightCompany;	// 항공사
    String departure;	// 출발지
    String destination;	// 도착지
    int row;
    int col;

    public Ticket(int seatPrice, String flightCompany, String departure, String destination, int row, int col) {
        this.seatPrice = seatPrice;
        this.flightCompany = flightCompany;
        this.departure = departure;
        this.destination = destination;
        this.row = row;
        this.col = col;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    @Override
    public String toString() {
        return "티켓 정보: " +
                "가격=" + seatPrice +
                ", 항공사='" + flightCompany + '\'' +
                ", 출발지='" + departure + '\'' +
                ", 도착지='" + destination + '\'' +
                ", 좌석 정보=(" + row + ", " + col + ')';
    }

}
