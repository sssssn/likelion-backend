package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11660 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int size = Integer.parseInt(infoToken.nextToken());
        int points = Integer.parseInt(infoToken.nextToken());
        int[][] board = new int[size + 1][size + 1];
        for (int i = 1; i < size + 1; i++) {
            StringTokenizer rowToken
                    = new StringTokenizer(reader.readLine());
            for (int j = 1; j < size + 1; j++) {
                board[i][j] = Integer.parseInt(rowToken.nextToken());
            }
        }
        int[][] dp = new int[size + 1][size + 1];
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                // 전체 구간 합 구해두기
                dp[i][j] = board[i][j]
                        + dp[i - 1][j]
                        + dp[i][j - 1]
                        - dp[i - 1][j - 1];
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < points; i++) {
            StringTokenizer pointToken = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(pointToken.nextToken());
            int y1 = Integer.parseInt(pointToken.nextToken());
            int x2 = Integer.parseInt(pointToken.nextToken());
            int y2 = Integer.parseInt(pointToken.nextToken());
            int sum
                    = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            answer.append(sum).append('\n');
        }
        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {
        new Boj11660().solution();
    }
}

/*
4 3
1 2 3 4
2 3 4 5
3 4 5 6
4 5 6 7
2 2 3 4
3 4 3 4
1 1 4 4
 */
/*
2 4
1 2
3 4
1 1 1 1
1 2 1 2
2 1 2 1
2 2 2 2
 */
