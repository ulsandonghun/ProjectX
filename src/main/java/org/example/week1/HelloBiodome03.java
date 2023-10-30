package org.example.week1;

public class HelloBiodome03 {
    static Double pie=3.14;

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

        double result;
        double cal=calculate(sqrt(d[1]),d[0]);
        result=health(0.415,cal,d[2]/(pie*pie));

        System.out.println(result);



    }

    public static double sqrt(double num) {
        System.out.println("루트 넘버 = " + Math.sqrt(num));
        return Math.sqrt(num);
    }

    public static double calculate(double a, double b) {

        System.out.println("습도 - 온도 절댓값 = " + Math.abs(a-b));
        return Math.abs(a - b);
    }

    public static double health(double a,double b,double c) {

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        System.out.println("a * b = " + a * b);
        System.out.println("(a * b) + c = " + (a * b) + c);
        return (a * b) + c;
    }
}
