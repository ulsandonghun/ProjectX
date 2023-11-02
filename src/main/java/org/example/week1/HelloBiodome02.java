package org.example.week1;

public class HelloBiodome02 {

    public static void main(String[] args) {

        if (args==null) {
            System.out.println("Invalid format.");
            System.exit(0);
        }

        String str = String.join(" ", args);
        String[] s = str.split(" ");
        int[] arr = new int[3];
        int result=0;
        for (int i = 0; i < s.length; i++) {
            Integer result1 = Integer.valueOf(s[i]);
            arr[i]= result1;
            result+= result1;
        }


        System.out.println("총 에너지 사용량은 "+result+"입니다.");
        System.out.println("태양광 " + ((double)arr[0]/result)*100 + "%, 풍력 " + (double)arr[1]/result*100 + "%, 지열 " + (double)arr[2]/result*100 + "%");


    }
}
