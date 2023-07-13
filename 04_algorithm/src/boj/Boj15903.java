package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj15903 {
    public long solution() throws IOException {
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(infoToken.nextToken());
        int actions = Integer.parseInt(infoToken.nextToken());

        StringTokenizer cardToken = new StringTokenizer(reader.readLine());
        // 우선 순위 큐에 넣어준다.
        PriorityQueue<Long> smallestCards = new PriorityQueue<>();
        for (int i = 0; i < cardCount; i++) {
            smallestCards.offer(Long.parseLong(cardToken.nextToken()));
        }

        // 두 개의 숫자를 뽑아서 합한 뒤
        // 다시 우선 순위 큐에 두번 넣어준다.
        for (int i = 0; i < actions; i++) {
            long first = smallestCards.poll();
            long second = smallestCards.poll();
            smallestCards.offer(first + second);
            smallestCards.offer(first + second);
        }
        // 정답을 구한다.
        long answer = 0;
        while (!smallestCards.isEmpty()) {
            answer += smallestCards.poll();
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Boj15903().solution());
    }
}

/*
3 1
3 2 6
 */
/*
4 2
4 2 3 1
 */
