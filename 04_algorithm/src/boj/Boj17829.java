package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17829 {
    private int[][] matrix;
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer row = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(row.nextToken());
            }
        }
        return pooling(n, 0, 0);
    }

    public int pooling(int n, int x, int y) {
        // 재귀호출하지 않고 계산한 결과를 반환
        if (n == 2) {
            int[] four = new int[] {
                    matrix[y][x],
                    matrix[y + 1][x],
                    matrix[y][x + 1],
                    matrix[y + 1][x + 1]
            };
            Arrays.sort(four);
            return four[2];
        }
        // 재귀호출 후 해당 결과를 수집한 뒤 자신의 결과로 반환
        else {
            int half = n / 2;
            int[] four = new int[] {
                    pooling(half, x, y),
                    pooling(half, x + half, y),
                    pooling(half, x, y + half),
                    pooling(half, x + half, y + half)
            };
            Arrays.sort(four);
            return four[2];
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Boj17829().solution());
    }
}

/*
4
-6 -8 7 -4
-5 -5 14 11
11 11 -1 -1
4 9 -2 -4
 */
/*
8
-1 2 14 7 4 -5 8 9
10 6 23 2 -1 -1 7 11
9 3 5 -2 4 4 6 6
7 15 0 8 21 20 6 6
19 8 12 -8 4 5 2 9
1 2 3 4 5 6 7 8
9 10 11 12 13 14 15 16
17 18 19 20 21 22 23 24
 */
