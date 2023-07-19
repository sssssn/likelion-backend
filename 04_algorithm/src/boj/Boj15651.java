package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15651 {
    private int n;
    private int m;
    // 실제로 만든 중복 순열을 담기 위한 배열
    private int[] arr;
    // 정답을 저장할 StringBuilder
    private StringBuilder answer;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        // n과 m 입력
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        // 순열 저장용 배열 생성
        arr = new int[m];
        // 정답 저장용 StringBuilder 생성
        answer = new StringBuilder();
        // 재귀 시작
        dfs(0);
        System.out.println(answer);
    }

    // 몇번째 숫자를 고르고 있는지
    // 0 ~ level ~ m - 1
    private void dfs(int level) {
        // 모두 다 골랐다.
        if (level == m) {
            // 정답 저장
            for (int i = 0; i < m; i++) {
                answer.append(arr[i]).append(' ');
            }
            answer.append('\n');
        }
        // 아직 남았다.
        // 1 ~ n 까지 반복한다.
        else for (int i = 1; i < n + 1; i++) {
            // 이번 숫자로 i를 택한다.
            arr[level] = i;
            dfs(level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new Boj15651().solution();
    }
}
