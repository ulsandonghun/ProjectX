package org.example.week1;

public class HelloBiodome06 {
    public static void main(String[] args) {
        String str = String.join(" ", args);
        String[] s = str.split(" ");

        int i=0;
        if (s[0].length() != s[1].length()) {
            System.out.println("일치하지 않습니다.");
        }
        while (i<s[0].length()) {
            if (s[0].charAt(i) != s[1].charAt(i)) {
                System.out.println("일치하지 않습니다.");
                break;
            }
            i++;
        }
        if (i == s[0].length()) {
            System.out.println("동일한 유전자 코드입니다.");
        }
    }
}
