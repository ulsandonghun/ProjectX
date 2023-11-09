package org.example.week2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RoadToBiodome06 {
    public static void main(String[] args) {

        String input=String.join(" ",args);
        String[] arrayStrings = input.split("\\]\\s+\\[");

        int[] array1 = filterAndConvertArray(arrayStrings[0]);
        int[] array2 = filterAndConvertArray(arrayStrings[1]);

        double mean = calculateMean(array1, array2);
        double median = calculateMedian(array1, array2);

        System.out.println("Mean : " + mean);
        System.out.println("Median : " + median);
    }

    private static int[] filterAndConvertArray(String arrayString) {
        String[] stringArray = arrayString.replaceAll("[^0-9]+", " ").trim().split("\\s+");
        return IntStream.range(0, stringArray.length)
                .map(i -> Integer.parseInt(stringArray[i]))
                .filter(num -> num >= 30)
                .sorted()
                .toArray();
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
            return findKth(array1, array2, totalLength / 2 + 1);
        } else {
            return (findKth(array1, array2, totalLength / 2) + findKth(array1, array2, totalLength / 2 + 1)) / 2.0;
        }
    }

    public static int findKth(int[] array1, int[] array2, int k) {
        int length1 = array1.length, length2 = array2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            if (index1 == length1) {
                return array2[index2 + k - 1];
            }
            if (index2 == length2) {
                return array1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(array1[index1], array2[index2]);
            }

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
