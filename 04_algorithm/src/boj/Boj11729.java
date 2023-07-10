package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11729 {
    private StringBuilder towerBuilder;

    public void solution() throws IOException {
        int n = Integer.parseInt(
                new BufferedReader(new InputStreamReader(System.in)).readLine());
        // (int) (Math.pow(2, n) - 1) == 2^n - 1
        this.towerBuilder = new StringBuilder();
//        System.out.println((int) (Math.pow(2, n) - 1));
        this.towerBuilder
                .append((int) (Math.pow(2, n) - 1))
                .append("\n");
        hanoi(n, 1, 3, 2);
        System.out.println(this.towerBuilder.toString());
    }

    private void hanoi(
            // 옮기고자 하는 탑의 높이
            int height,
            // 시작 위치
            int start,
            // 목표 위치
            int end,
            // 이동하지 않는 위치
            // 재귀 호출시에는 end 로 바로가지 않고 other 로 보내야 하기 때문
            int other
    ) {
        // 하나짜리 원반은 바로 이동
        if (height == 1) {
            this.towerBuilder.append(start + " " + end + "\n");
        }
        else {
            // n 보다 작은 원반들을 start 에서 other
            hanoi(height - 1, start, other, end);
            // n 번째 원반을 start 에서 end 로
            this.towerBuilder.append(start + " " + end + "\n");
            // n 보다 작은 원반들을 other 에서 end 로
            hanoi(height - 1, other, end, start);
        }
    }

    public static void main(String[] args) throws IOException {
        new Boj11729().solution();
    }
}
