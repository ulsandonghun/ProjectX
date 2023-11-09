package org.example.week2;

import java.util.LinkedList;
import java.util.Queue;

public class RoadToBiodome09 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("입력값이 없습니다");
            return;
        }
        // 입력값을 2차원 배열로 변환
        int N = args.length;
        int M = args[0].length();
        int[][] cave = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (args[i].length() != M) {
                System.out.println("입력값의 길이가 일치하지 않습니다.");
                return;
            }
            for (int j = 0; j < M; j++) {
                char cell = args[i].charAt(j);
                if (cell != '0' && cell != '1') {
                    System.out.println("입력값은 0 또는 1이어야 합니다.");
                    return;
                }
                cave[i][j] = cell  - '0';
            }
        }
        // BFS 알고리즘을 사용하여 최단 경로 계산
        int shortestPathLength = findPath(cave, N, M);
        if (shortestPathLength == -1) { //return값이 -1인 경
            System.out.println("경로가 존재하지 않습니다");
        } else {
            System.out.println(shortestPathLength);
        }
    }
    public static int findPath(int[][] cave, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
        // 방문 여부를 나타내는 배열
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        queue.add(new int[]{0, 0, 1}); // 시작 지점 (0,0)과 거리 1
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];
            if (x == N - 1 && y == M - 1) {
                return distance-1; // 출구에 도달했을 때 거리 반환. 1빼야 함
            }
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < N && newY >= 0 && newY < M && cave[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.add(new int[]{newX, newY, distance + 1});
                    visited[newX][newY] = true;
                }
            }
        }
        return -1; // 출구에 도달할 수 없는 경우 => 경로가 존재하지 않습니다
    }

}
