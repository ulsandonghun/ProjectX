package org.example.week1;

public class HelloBiodome03 {
    Double pie=3.14;

    public static void main(String[] args) {

        if (args==null) {
            System.out.println("Invalid format.");
            System.exit(0);
        }
        String str = String.join(" ", args);
        String[] s = str.split(" ");

        Double[] d = new Double[3];
        for (int i = 0; i < 3; i++) {
            d[i]=Double.valueOf(s[i]);
            System.out.println("d[i] = " + d[i]);
        }



    }

    public static double sqrt(double num) {
        return Math.sqrt(num);
    }

    public static double calculate(double a, double b) {



    }
}
