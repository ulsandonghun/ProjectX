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
        double cal=calculate(sqrt(d[1],d[1]/2),d[0]);
        result=health(0.415,cal,d[2]/(pie*pie));

        System.out.println(result);



    }

    public static double sqrt(double num,double next) {
        double e = 1e-7;
        double diff = next * next - num;
        if (diff < 0) diff = -diff;

        if (diff < e) {
            return next;
        } else {
            return sqrt(num, (next + num / next) / 2.0);
        }

    }

    public static double calculate(double hum, double temp) {
        if(hum - temp>=0)
            return hum - temp;
        else
            return -(hum - temp);

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
