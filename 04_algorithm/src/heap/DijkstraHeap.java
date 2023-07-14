package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DijkstraHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());
        int nodes = Integer.parseInt(info.nextToken());
        int edges = Integer.parseInt(info.nextToken());
        int start = Integer.parseInt(reader.readLine());

        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken());
            int to = Integer.parseInt(edgeToken.nextToken());
            int cost = Integer.parseInt(edgeToken.nextToken());

            adjList.get(from).add(new int[]{to, cost});
        }

        // 방문 정보 visited
        boolean[] visited = new boolean[nodes];
        // 현재까지의 최서 거리 정보 dist
        int[] dist = new int[nodes];
        // 1. 모든 정점까지 아직 도달할 길이 없으므로 무한대 (또는 최대)로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 2. 시작 정점까지의 거리는 0
        dist[start] = 0;

        // 우선 순위 큐 활용
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[1])
        );
        // 처음 가는 곳은 시작 지점
        minHeap.offer(new int[]{start, 0});

        // 우선 순위 큐에 더이상 방문할 정점이 기록되지 않을때까지
        while (!minHeap.isEmpty()) {
            // 최소 힙에서 꺼내면 도달 가능한 가장 가까운 지점
            int[] current = minHeap.poll();

            if (visited[current[0]]) continue;
            visited[current[0]] = true;
            // adjList.get(index): 인덱스 정점에서 뻗어나가는 간선들의 리스트
            for (int[] neighbor: adjList.get(current[0])) {
                int neighborVertex = neighbor[0];
                int neighborCost = neighbor[1];

                // 방문하지 않은 정점이고 현재까지 기록된 거리보다 새로 가는 거리가 더 짧다.
                if (!visited[neighborVertex] &&
                        dist[neighborVertex] > current[1] + neighborCost) {
                    dist[neighborVertex] = current[1] + neighborCost;
                    minHeap.offer(new int[]{ neighborVertex, dist[neighborVertex] });
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }
}

/*
6 8
0
0 1 10
0 2 15
1 3 12
1 5 15
2 4 10
3 4 2
3 5 1
5 4 5
 */
/*
5 6
0
4 0 1
0 1 2
0 2 3
1 2 4
1 3 5
2 3 6
 */
