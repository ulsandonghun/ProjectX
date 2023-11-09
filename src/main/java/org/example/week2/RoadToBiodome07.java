package org.example.week2;

import java.util.HashSet;
import java.util.Set;

public class RoadToBiodome07 {
    public static void main(String[] args) {
        //숫자가 입력된 경우를 대비해서 예외처리 해주기..
        for (String arg : args) {
            try {
                int number = Integer.parseInt(arg);
                System.out.println("입력된 문자를 다시 한번 확인해주세요.");
                System.exit(0);
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 경우, 다음 command line argument를 처리
            }
        }

        // 입력받은 동물들의 이름을 배열에 저장
        String[] animals = new String[args.length];

        int i=0;
        while(i<args.length) {
            animals[i]=args[i];
            i++;
        }
        //중복을 제거해주는 set이라는 자료구조를 사용하여 중복 없이 동물들이 저장되어 있는 배열 생성
        System.out.print("[ ");
        for(int j=0;j<i;j++) {
            System.out.print(animals[j]+" ");
        }
        System.out.println("]");

        Set<String> animalsArray=new HashSet<>();

        for(String str:animals) {
            animalsArray.add(str);
        }
        String[] animalArray=new String[animalsArray.size()];
        animalsArray.toArray(animalArray);

        //동물마다 빈도수를 저장하는 배열 생성 후 메서드를 통해 값 집어넣기
        int[] count=new int[animalArray.length];
        for(int j=0;j<animalArray.length;j++) {
            count[j]=getCount(animals,animalArray[j]);
        }

        //선택 정렬 알고리즘을 이용해서 빈도수가 많은 순으로 정렬하기..
        for(int j=0;j<count.length-1;j++) {
            for(int p=j+1;p<count.length;p++) {
                if(count[j]<count[p]) {
                    String tmp;
                    tmp=animalArray[j];
                    animalArray[j]=animalArray[p];
                    animalArray[p]=tmp;
                    int tmp1;
                    tmp1=count[j];
                    count[j]=count[p];
                    count[p]=tmp1;
                }
            }
        }

        //빈도수가 동일한 경우에는 가나다 순서로 정렬하기 -compareTo 메서드 활용
        for(int j=0;j<count.length-1;j++) {
            if(count[j]==count[j+1]) {
                if(animalArray[j].compareTo(animalArray[j+1])>0) {
                    String tmp;
                    tmp=animalArray[j];
                    animalArray[j]=animalArray[j+1];
                    animalArray[j+1]=tmp;
                    int tmp1;
                    tmp1=count[j];
                    count[j]=count[j+1];
                    count[j+1]=tmp1;
                }
            }
        }


        System.out.print(">>> ");
        for(int j=0;j<animalArray.length;j++)
            System.out.print(animalArray[j]+" ");

    }

    public static int getCount(String[] animals, String animalArray) {
        // TODO Auto-generated method stub
        int count=0;

        for(int i=0;i<animals.length;i++) {
            if(animalArray.equals(animals[i]))
                count++;
        }

        return count;
    }

}
