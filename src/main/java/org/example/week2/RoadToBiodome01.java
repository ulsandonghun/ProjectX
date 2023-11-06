package org.example.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoadToBiodome01 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        String[] stringArray1 = args[0].replaceAll("[^0-9]+", " ").trim().split("\\s+");
        int[] array1 = new int[stringArray1.length];
        for (int i = 0; i < stringArray1.length; i++) {
            array1[i] = Integer.parseInt(stringArray1[i]);
        }
//        System.out.println(Arrays.toString(stringArray1));
        for (int i : array1) {
            int count = countNum(array1, i);
            if (count == 1) {
                System.out.println(i);
                break;
            }
        }
    }

    static int countNum(int[] list,int findCountNum) {
        int size= list.length;
        int count=0;
        for (int i : list) {
            if(i==findCountNum){
                count++;
            }
        }
        return count;
    }

}
