/*
    ACM Craft
    인접리스트 + 위상정렬
    이 문제는 pq를 쓰긴 쓰는데 내림차순으로 정렬해주면 쉽게 풀 수 있다.    
*/
import java.io.*;
import java.util.*;

public class p1005 {
    static class Node implements Comparable<Node> {
        int e, cost;

        public Node (int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return o.cost - cost;
        }
    }
    static int t, n, k, w;
    static int[] time, indegree, dist;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            indegree = new int[n + 1];
            time = new int[n + 1];
            list = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(new Node(v, time[v]));
                indegree[v]++;
            }

            w = Integer.parseInt(br.readLine());

            dist = new int[n + 1];
            solve();
            System.out.println(dist[w]);
        }
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.add(new Node(i, time[i]));
                dist[i] = time[i];
            }
        }
        
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.e;
            
            for (Node node : list[cur]) {
                indegree[node.e]--;

                if (dist[node.e] < dist[cur] + node.cost) {
                    dist[node.e] = dist[cur] + node.cost;
                }

                if (indegree[node.e] == 0) {
                    pq.add(new Node(node.e, dist[node.e]));
                }
            }
        }
        
    }
}
