package org.example.week4_Teamproject;

public class Member {
    int age;
    String grade; // Normal, Gold, VIP
    Ticket[] tickets;
    int purchaseCount;
    String name;
    static final int CAPACITY = 100;
    int count;


    public Member(int age, String name) {
        super();
        this.age = age;
        this.name = name;
        this.tickets = new Ticket[this.CAPACITY];
    }

    void addTicket(Ticket t) {
        tickets[count++] = t;
    }

    void showTickets() {
        if (tickets == null || tickets.length == 0) {
            System.out.println("티켓이 없습니다.");
            return;
        }

        for (Ticket ticket : tickets) {
            if (ticket != null) {
                System.out.println(ticket);
            }
        }
    }

    void discount(Ticket[] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null) {
                switch (grade) {
                    case "Normal":
                        tickets[i].seatPrice = tickets[i].seatPrice;
                        break;
                    case "Gold":
                        tickets[i].seatPrice = (int)(tickets[i].seatPrice * 0.9);
                        break;
                    case "VIP":
                        tickets[i].seatPrice = (int)(tickets[i].seatPrice * 0.8);
                        break;
                }
                if (age < 20 || age > 65) {
                    tickets[i].seatPrice = (int)(tickets[i].seatPrice * 0.9);
                }
            }
        }
    }


}
