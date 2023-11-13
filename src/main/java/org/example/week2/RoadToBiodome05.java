package org.example.week2;

import java.util.Arrays;

public class RoadToBiodome05 {
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

        // 두 번째 배열 생성
        String[] stringArray2 = arrayStrings[1].replaceAll("[^0-9]+", " ").trim().split("\\s+");
        int[] array2 = new int[stringArray2.length];
        for (int i = 0; i < stringArray2.length; i++) {
            array2[i] = Integer.parseInt(stringArray2[i]);
        }
        int[] mergeStr = new int[stringArray1.length+ stringArray2.length];
        System.arraycopy(array1, 0, mergeStr, 0, array1.length);
        System.arraycopy(array2, 0, mergeStr, array1.length, array2.length);
        QuickSort.sort(mergeStr);
        System.out.println(Arrays.toString(mergeStr));

    }

    public class QuickSort {
        /*
            쉽게 설명하면 피봇 기준으로 왼쪽은 피봇보다 작은 수 오른족은 피봇보다 큰 수로 정렬되는 것이다. --최동훈이 적음.
             */

        public static void sort(int[] a) {
            l_pivot_sort(a, 0, a.length - 1);
        }

        /**
         *  왼쪽 피벗 선택 방식
         * @param a		정렬할 배열
         * @param lo	현재 부분배열의 왼쪽
         * @param hi	현재 부분배열의 오른쪽
         */
        private static void l_pivot_sort(int[] a, int lo, int hi) {

            /*
             *  lo가 hi보다 크거나 같다면 정렬 할 원소가
             *  1개 이하이므로 정렬하지 않고 return한다.
             */
            if(lo >= hi) {
                return;
            }


            int pivot = partition(a, lo, hi);

            l_pivot_sort(a, lo, pivot - 1);
            l_pivot_sort(a, pivot + 1, hi);
        }



        /**
         * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
         *
         * @param a		정렬 할 배열
         * @param left	현재 배열의 가장 왼쪽 부분
         * @param right	현재 배열의 가장 오른쪽 부분
         * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
         */
        private static int partition(int[] a, int left, int right) {


            int lo = left;
            int hi = right;
            int pivot = a[left];		// 부분리스트의 왼쪽 요소를 피벗으로 설정

            // lo가 hi보다 작을 때 까지만 반복한다.
            while(lo < hi) {

                /*
                 * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를
                 * 찾을 떄 까지 hi를 감소시킨다.
                 */
                while(a[hi] > pivot && lo < hi) {
                    hi--;
                }

                /*
                 * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를
                 * 찾을 떄 까지 lo를 증가시킨다.
                 */
                while(a[lo] <= pivot && lo < hi) {
                    lo++;
                }

                // 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
                swap(a, lo, hi);
            }


            /*
             *  마지막으로 맨 처음 pivot으로 설정했던 위치(a[left])의 원소와
             *  lo가 가리키는 원소를 바꾼다.
             */
            swap(a, left, lo);

            // 두 요소가 교환되었다면 피벗이었던 요소는 lo에 위치하므로 lo를 반환한다.
            return lo;
        }

        private static void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

}
