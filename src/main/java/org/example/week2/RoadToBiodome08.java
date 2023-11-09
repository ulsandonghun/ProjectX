package org.example.week2;

public class RoadToBiodome08 {
    private static int maxSize;
    private static int front;
    private static int rear;
    private static int[] queueArray;

    public static void main(String[] args) {
        init(10);
        for (String str : args) {
            enqueue(Integer.valueOf(str));
        }
        while (!isEmpty()) {
            System.out.println("자원 " + dequeue() + "을 제공했습니다.");
        }
        System.out.println("모든 요청이 처리되었습니다.");
    }

    public static void init(int size) {
        maxSize = size;
        front = 0;
        rear = -1;
        queueArray = new int[maxSize];
    }

    public static void enqueue(int value) {
        if (rear == maxSize - 1) {
            maxSize += 10;
            int[] newQueueArray = new int[maxSize];
            System.arraycopy(queueArray, 0, newQueueArray, 0, queueArray.length);
            queueArray = newQueueArray;
            System.out.println("큐의 크기가 " + maxSize + "으로 늘어났습니다.");
        }
        queueArray[++rear] = value;
    }

    public static int dequeue() {
        if (front > rear) {
            System.out.println("큐가 비었습니다.");
            return -1;
        } else {
            return queueArray[front++];
        }
    }

    public static boolean isEmpty() {
        return (front > rear);
    }
}
