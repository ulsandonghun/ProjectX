package org.example.week2;

public class RoadToBiodome04 {
    public static void main(String[] args) {

        String str = "";
        int count = 0;
        for (int i = 0; i < args.length; i++) {
            str += args[i];
            if (args[i].contains("]"))	// 입력된 배열이 여러개인지 확인하기
                count++;
        }

        // 입력된 배열이 여러개인 경우 중앙값들 출력하기
        if (count > 1) {
            String tmpstr[] = str.split("]");

            // 입력 전처리(,와 [) 제거
            for (int i = 0; i < tmpstr.length; i++) {
                tmpstr[i] = tmpstr[i].replace("[", "");
                tmpstr[i] = tmpstr[i].replace(",", " ");
                String[] tmparr = tmpstr[i].split(" ");
                int[] tmplist = new int[tmparr.length];

                // tmpstr[1]부터는 , [a, b, c...과 같은 구조이므로 맨 앞의 공백 한 칸을 제거
                if (i != 0) {
                    String[] newTmpArr = new String[tmparr.length - 1];
                    int[] newTmpList = new int[tmparr.length - 1];
                    System.arraycopy(tmparr, 1, newTmpArr, 0, newTmpArr.length);
                    for (int j = 0; j < newTmpArr.length; j++)
                        newTmpList[j] = Integer.parseInt(newTmpArr[j]);

                    arrSort(newTmpList);	// 선택정렬
                    double mid = arrMid(newTmpList);
                    if (i < tmpstr.length - 1) {	// 마지막 출력이 아닌 경우 , 출력
                        if (mid == (int) mid)
                            System.out.print((int) mid + ", ");
                        else
                            System.out.print(mid + ", ");
                    } else {	// 마지막 출력이라 ,를 출력하지 않음
                        if (mid == (int) mid)
                            System.out.print((int) mid);
                        else
                            System.out.print(mid);
                    }
                }

                // tmpstr[0]은 , [a, b, c...과 같은 구조가 아니므로 맨 앞의 공백 한 칸을 제거할 필요가 없음
                else {
                    for (int j = 0; j < tmparr.length; j++)
                        tmplist[j] = Integer.parseInt(tmparr[j]);

                    arrSort(tmplist);	// 선택정렬
                    double mid = arrMid(tmplist);
                    if (mid == (int) mid)
                        System.out.print((int) mid + ", ");
                    else
                        System.out.print(mid + ", ");
                }
            }
            return;
        }

        // 입력된 배열이 하나인 경우 전처리 (,와 [와 ]) 제거
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(",", "");

        // 입력된 배열을 int[] 타입으로 변환
        String[] arr = str.split(" ");
        int[] list = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            list[i] = Integer.parseInt(arr[i]);

        arrSort(list); // 선택정렬
        double avg = arrAvg(list); // 평균값
        double mid = arrMid(list); // 중앙값
        if (mid == (int) mid)	// 중앙값이 정수인 경우
            System.out.println("평균값: " + avg + ", 중앙값: " + (int) mid);
        else	// 중앙값이 실수인 경우
            System.out.println("평균값: " + avg + ", 중앙값: " + mid);

    }

    private static double arrMid(int[] list) {	// 중앙값 찾기
        double mid;
        if (list.length % 2 == 0) { // 배열의 원소 개수 짝수
            int idx1 = list.length / 2;
            int idx2 = list.length / 2 - 1;
            mid = ((double) list[idx1] + (double) list[idx2]) / (double) 2;
        } else { // 배열의 원소 개수 홀수
            mid = list[(list.length - 1) / 2];
        }
        return mid;
    }

    private static double arrAvg(int[] list) {	// 평균 찾기
        double sum = 0;
        for (int i = 0; i < list.length; i++) {
            sum += (double) list[i];
        }
        double avg = sum / list.length;
        return avg;
    }

	/*
	 <선택 정렬>
	 1번 원소를 2~마지막 원소와 비교한다. 비교하면서 1번 원소보다 큰 값을 계속 minIdx에 초기화한다.
	 1번 원소가 가장 작은 값인 경우 다음 2번 원소를 3~마지막 원소와 비교한다.
	 1번 원소가 아닌 다른 원소가 가장 작은 값인 경우 1번 원소와 가장 작은 원소를 바꾼 다음 2번 원소를 3~마지막 원소와 비교한다.
	 마지막-1번 원소까지 정렬을 하면 마지막 원소는 자동으로 정렬되어 있다.
	 */

    private static void arrSort(int[] list) {	// 선택정렬
        for (int i = 0; i < list.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[minIdx] > list[j])
                    minIdx = j;
            }
            if (i != minIdx) {
                list[i] = list[i] ^ list[minIdx];
                list[minIdx] = list[i] ^ list[minIdx];
                list[i] = list[i] ^ list[minIdx];
            }
        }
    }


}
