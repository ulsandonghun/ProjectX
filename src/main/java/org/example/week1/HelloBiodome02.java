package org.example.week1;

public class HelloBiodome02 {

    public static void main(String[] args) {

        if (args==null) {
            System.out.println("Invalid format.");
            System.exit(0);
        }

        String str = String.join(" ", args);
        String[] s = str.split(" ");
        int result=0;
        for (int i = 0; i < s.length; i++) {
           result+= Integer.valueOf(s[i]);
            System.out.println("s[i] = " + s[i]);
        }
        System.out.println("총 에너지 사용량은 "+result+"입니다.");


    }
}
