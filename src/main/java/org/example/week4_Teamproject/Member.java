package org.example.week4_Teamproject;

public class Member {
    int age;
    String grade; // Normal, Gold, VIP
    Ticket[] tickets;
    int purchaseCount;
    String name;

    public Member(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    void showTickets(Ticket[] tickets) {
        String str = "";

        for (Ticket t : tickets) {
            if (t != null)
                str += t.toString();
            else
                break;
        }
        System.out.println(str);
    }

    void discount(int age, String grade, Ticket[] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            switch (grade) {
                case "Normal" -> {
                    tickets[i].seatPrice = tickets[i].seatPrice;
                }
                case "Gold" -> {
                    tickets[i].seatPrice = (int)(tickets[i].seatPrice*0.9);
                }
                case "VIP" -> {
                    tickets[i].seatPrice = (int)(tickets[i].seatPrice*0.8);
                }
            }
            if(age<20||age>65) {
                tickets[i].seatPrice = (int)(tickets[i].seatPrice*0.9);
            }
        }
    }

}
