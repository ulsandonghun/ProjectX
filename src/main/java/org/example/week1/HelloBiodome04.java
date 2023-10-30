package org.example.week1;

import java.util.HashMap;

public class HelloBiodome04 {

    public static void main(String[] args) {
        try {
            if (args==null) {
                System.out.println("Invalid format.");
                System.exit(0);
            }
            String str = String.join(" ", args);
            String[] s = str.split(" ");

            Double[] d = new Double[3];


            int safe=0;
            for (int i = 0; i < 3; i++) {
                d[i]=Double.valueOf(s[i]);
                if (i == 0) {
                    if (temperatureCheck(d[i])) {

                        System.out.println("온도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
                        safe++;
                    break;
                    }
                } else if (i == 1) {
                    if (humadityCheck(d[i])) {

                        System.out.println("습도값이 정상범위를 벗어났습니다. 확인이 피요합니다.");
                        safe++;
                    break;
                    }
                }else{
                    if (oxygenCheck(d[i])) {

                        System.out.println("산소값이 정상범위를 벗어났습니다. 확인이 필요합니다.");
                        safe++;
                    break;
                    }

                }
            }
            if (safe == 0) {
                System.out.println("생명의 나무는 안정적인 상태입니다.");
            }


        } catch (Exception e) {
            System.out.println("오류 메세지 e.getMessage() = " + e.getMessage());
        }


    }

    private static boolean oxygenCheck(Double d) {
        return !(19.5 <= d && d <= 23.5);
    }

    private static boolean humadityCheck(Double d) {
        return !(40 < d && d < 60);
    }

    private static boolean temperatureCheck(Double d) {
        return !(10 <= d && d < 27.5);
    }
}
