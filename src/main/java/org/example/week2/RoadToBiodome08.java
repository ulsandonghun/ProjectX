package org.example.week2;

public class RoadToBiodome08 {
    public static void main(String[] args) {
        DynamicQueue dynamicQueue = new DynamicQueue(10);
        for (String str : args) {
            dynamicQueue.enqueue(Integer.valueOf(str));
        }
        while (!dynamicQueue.isEmpty()) {
            System.out.println("자원 " + dynamicQueue.dequeue() + "을 제공했습니다.");
        }
        System.out.println("모든 요청이 처리되었습니다.");
    }


}
class DynamicQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] queueArray;

    public DynamicQueue(int size) {
        this.maxSize = size;
        this.front = 0;
        this.rear = -1;
        this.queueArray = new int[maxSize];
    }

    public void enqueue(int value) {
        if (rear == maxSize - 1) {
            // 큐가 가득 찼으므로 큐의 크기를 늘립니다.
            maxSize += 10;
            int[] newQueueArray = new int[maxSize];
            System.arraycopy(queueArray, 0, newQueueArray, 0, queueArray.length);
            queueArray = newQueueArray;
            System.out.println("큐의 크기가 " + maxSize + "으로 늘어났습니다.");
        }
        queueArray[++rear] = value;
    }

    // dequeue, peek, isEmpty 메서드는 앞서 구현한 것과 동일합니다.
    public int dequeue() {
        if (front > rear) {
            System.out.println("큐가 비었습니다.");
            return -1;
        } else {
            return queueArray[front++];
        }
    }

    public int peek() {
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (front > rear);
    }

}
