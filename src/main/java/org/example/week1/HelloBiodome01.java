package org.example.week1;

public class HelloBiodome01 {


    public static void main(String[] args) {

        if (args==null) {
            System.out.println("Invalid format.");
            System.exit(0);
        }

        String name = String.join(" ",args);




        System.out.println("환영합니다  ! "+name);


    }
}
