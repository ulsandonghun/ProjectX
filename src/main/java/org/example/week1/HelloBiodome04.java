package org.example.week1;

public class HelloBiodome04 {

    public static void main(String[] args) {
        try {
            if (args == null || args.length < 3) {
                System.out.println("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요.");
                return;
            }

            double temperature = Double.parseDouble(args[0]);
            double humidity = Double.parseDouble(args[1]);
            double oxygen = Double.parseDouble(args[2]);

            boolean safe = check(temperature, humidity, oxygen);

            if (safe) {
                double healthIndex = calculateHealthIndex(temperature, humidity, oxygen);
                System.out.printf("생명의 나무는 안정적인 상태입니다. 건강지수는 %.2f입니다.", healthIndex);
            } else {
                String invalidNum = checkNum(temperature, humidity, oxygen);
                System.out.println(invalidNum + "값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요.");
        }
    }

    private static boolean check(double temperature, double humidity, double oxygen) {
        if (temperature >= 10 && temperature < 27.5) {
            if (humidity > 40 && humidity < 60) {
                if (oxygen >= 19.5 && oxygen <= 23.5) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String checkNum(double temperature, double humidity, double oxygen) {
        StringBuilder invalidNum = new StringBuilder();
        if (!(temperature >= 10 && temperature < 27.5)) {
            invalidNum.append("온도 ");
        }
        if (!(humidity > 40 && humidity < 60)) {
            invalidNum.append("습도 ");
        }
        if (!(oxygen >= 19.5 && oxygen <= 23.5)) {
            invalidNum.append("산소 농도 ");
        }
        return invalidNum.toString();
    }

    private static double calculateHealthIndex(double temperature, double humidity, double oxygen) {
        double e = 1e-7;
        humidity = root(humidity, humidity / 2.0, e);
        double ht = abs(humidity, temperature);
        return UB * ht + oxygen / (PI * PI);
    }

    private static double abs(double humidity, double temperature) {
        if (humidity - temperature >= 0) {
            return humidity - temperature;
        } else {
            return -(humidity - temperature);
        }
    }

    private static double root(double num, double next, double e) {
        double diff = next * next - num;
        if (diff < 0)
            diff = -diff;
        if (diff < e) {
            return next;
        } else {
            return root(num, (next + num / next) / 2.0, e);
        }
    }

    public static final double UB = 0.415;
    public static final double PI = 3.14;
}
