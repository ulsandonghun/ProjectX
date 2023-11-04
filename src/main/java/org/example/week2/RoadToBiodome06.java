package org.example.week2;

import java.util.Arrays;

public class RoadToBiodome06 {
    public static void main(String[] args) {

        String input=String.join(" ",args);
        // 문자열에서 배열을 분리
        String[] arrayStrings = input.split("\\]\\s+\\[");

        // 첫 번째 배열 생성
        String[] stringArray1 = arrayStrings[0].replaceAll("[^0-9]+", " ").trim().split("\\s+");
        int[] array1 = new int[stringArray1.length];
        for (int i = 0; i < stringArray1.length; i++) {
            array1[i] = Integer.parseInt(stringArray1[i]);
        }
        Arrays.sort(array1);

        // 두 번째 배열 생성
        String[] stringArray2 = arrayStrings[1].replaceAll("[^0-9]+", " ").trim().split("\\s+");
        int[] array2 = new int[stringArray2.length];
        for (int i = 0; i < stringArray2.length; i++) {
            array2[i] = Integer.parseInt(stringArray2[i]);
        }
        Arrays.sort(array2);

        // 평균 계산
        double mean = calculateMean(array1, array2);

        // 중앙값 계산
        double median = calculateMedian(array1, array2);

        System.out.println("Mean : " + mean);
        System.out.println("Median : " + median);
    }
    public static double calculateMean(int[] array1, int[] array2) {
        double sum = 0;
        for (int num : array1) {
            sum += num;
        }
        for (int num : array2) {
            sum += num;
        }
        return sum / (array1.length + array2.length);
    }
    public static double calculateMedian(int[] array1, int[] array2) {
        int totalLength = array1.length + array2.length;
        if (totalLength % 2 == 1) {
            //홀수일 경우 중앙값은 두 값크기의 절반 +1이다.
            return findKth(array1, array2, totalLength / 2 + 1);
        } else {
            return (findKth(array1, array2, totalLength / 2) + findKth(array1, array2, totalLength / 2 + 1)) / 2.0;
        }
    }
    public static int findKth(int[] array1, int[] array2, int k) {
        int length1 = array1.length, length2 = array2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // array1이 빈 배열인 경우
            if (index1 == length1) {
                return array2[index2 + k - 1];
            }
            // array2가 빈 배열인 경우
            if (index2 == length2) {
                return array1[index1 + k - 1];
            }
            // k가 1인 경우
            if (k == 1) {
                return Math.min(array1[index1], array2[index2]);
            }

            // array1과 array2에서 새로운 인덱스 계산
            int newIndex1 = Math.min(index1 + k / 2 - 1, length1 - 1);
            int newIndex2 = Math.min(index2 + k / 2 - 1, length2 - 1);

            if (array1[newIndex1] <= array2[newIndex2]) {
                k -= newIndex1 - index1 + 1;
                index1 = newIndex1 + 1;
            } else {
                k -= newIndex2 - index2 + 1;
                index2 = newIndex2 + 1;
            }
        }
    }

}
