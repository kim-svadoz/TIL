/*
    서강그라운드
    다익스트라
    dist[i] : 시작지점부터 해당 지점(i) 까지 이동하는 최단 거리
    
*/
import java.io.*;
import java.util.*;
public class p14938 {
    static class Node implements Comparable<Node> {
        int e, cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static final int INF = 987654321;
    static int n, m, r, max;
    static int[] arr;
    static boolean[] visited;
    static int[] dist;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, cost));
            list[v].add(new Node(u, cost));
        }
        
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        int answer = 0;
        // 낙하산을 어디에 내릴 것인가
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, search(i));
        }
        System.out.println(answer);
    }
    static int search(int start) {
        int ret = 0;
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.e;
            // 이미 탐색한 곳이다.
            if (visited[cur]) continue;
            visited[cur] = true;
            
            for (Node nextNode : list[cur]) {
                // dist배열에는 어찌됐던 i번 마을로 가는 것이 가장 짧은 경로를 저장한다.
                if (dist[nextNode.e] > dist[cur] + nextNode.cost) {
                    dist[nextNode.e] = dist[cur] + nextNode.cost;
                    pq.add(new Node(nextNode.e, dist[nextNode.e]));
                }
            }
        }
        
        // 수색범위 내의 마을들의 아이템 개수만 더한다.
        // dist[i] : i 마을 로의 최단거리
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                ret += arr[i];
            }
        }
        
        return ret;
    }
}