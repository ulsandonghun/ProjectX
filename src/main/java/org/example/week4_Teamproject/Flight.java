package org.example.week4_Teamproject;

public class Flight {
    int day;	// 출발일
    String flightCompany;	// 항공사
    String departure;	// 출발지
    String destination;	// 도착지
    String departureTime;	// 출발시각
    int departureHour;
    int departureMin;
    String arrivalTime;	// 도착시각
    int arrivalHour;
    int arrivalMin;
    String travelTime;	// 여행시간
    int travelHour;
    int travelMin;
    int row;
    int col;
    String[][] seat;	// 좌석 정보
    int seatPrice;	// 좌석 가격
    int totalFlightSell;	// 총 판매 금액


    public Flight() {
    }

    public Flight(int day, String flightCompany, String departure, String destination, int departureHour, int departureMin,
                  int arrivalHour, int arrivalMin, int row, int col, int seatPrice) {
        super();
        this.day = day;
        this.flightCompany = flightCompany;
        this.departure = departure;
        this.destination = destination;
        this.departureHour = departureHour;
        this.departureMin = departureMin;
        this.arrivalHour = arrivalHour;
        this.arrivalMin = arrivalMin;
        this.row = row;
        this.col = col;
        this.seat = new String[row][col];
        this.seatPrice = seatPrice;
    }

    void showSeat(String[][] seat) {
        System.out.println("****** 좌석 예매 현황 ******");
        System.out.print("  ");
        for (int i = 0; i < col; i++) {
            System.out.printf("%d ", (i + 1));
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.printf("%d ", (i + 1));
            for (int j = 0; j < col; j++) {
                String str = seat[i][j];
                if (str == null)
                    str = "□";
                else
                    str = "■";
                System.out.printf("%s ", str);
            }
            System.out.println();
        }
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getDay() {
        return day;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    public int getDeparatureMin() {
        return departureMin;
    }

    public int getArrivalHour() {
        return arrivalHour;
    }

    public String showStatus() {
        // 날짜가 넘어가는 경우 처리
        if (this.departureHour > this.arrivalHour || (this.departureHour == this.arrivalHour && this.departureMin > this.arrivalMin)) {
            this.arrivalHour += 24;
        }
        //소요시간 구하기
        this.travelHour = this.arrivalHour-this.departureHour;
        this.travelMin = this.arrivalMin - this.departureMin;
        if(this.travelMin<0) {
            this.travelHour--;
            this.travelMin = 60 + this.travelMin;
        }
        /*모든 시간에서 분이 한자리인 경우 5분이 아니라 05분으로 출력되게 수정*/
        StringBuilder sb = new StringBuilder();
        sb.append("출발일: " + this.day + "일    ");
        if(this.flightCompany.length()==2)
            sb.append(" 항공사: "+this.flightCompany + "      ");
        else if(this.flightCompany.length()==3)
            sb.append(" 항공사: "+this.flightCompany + "     ");
        else
            sb.append(" 항공사: "+this.flightCompany + "    ");
        sb.append(this.departure + " -> " + this.destination + "    ");
        sb.append("비행시간: " + this.departureHour + ":" + this.departureMin + " ~ " + this.arrivalHour + ":" + this.arrivalMin);
        sb.append("    소요시간: " + this.travelHour + ":" + this.travelMin + "    ");
        sb.append("좌석가격: " + this.seatPrice + "원");

        return sb.toString();
    }


}
