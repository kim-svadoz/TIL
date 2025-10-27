import java.util.*;
import java.io.*;

public class p12896 {
    static class Pair {
        int v, cost;
        public Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static int n;
    static List<Pair>[] list;
    static int[] dp;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        // 최대 도시의 수보다 넉넉히 할당
        list = new ArrayList[100005];
        dp = new int[100005];
        visit = new boolean[100005];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(new Pair(v, 1));
            list[v].add(new Pair(u, 1));
        }
        
        // 가장 길이(cost)가 긴 자식을 리턴
        Pair p = dfs(1);
        visit = new boolean[n + 1];
        // 가장 길이가 긴 자식에서 다시 dfs를 돈다.
        Pair r = dfs(p.v);
        // 가장 먼 정점과의 거리 중 최소값
        System.out.println((1 + r.cost) / 2);
    }


    static Pair dfs(int cur) {
        Pair result = new Pair(cur, 0);
        // 노드 방문처리
        visit[cur] = true;
        for (Pair next : list[cur]) {
            // 자식 노드를 방문했다면 넘어가고
            if (visit[next.v]) continue;
            // 방문하지 않았다면 즉시 방문해본다.
            Pair x = dfs(next.v);
            // 모든 자식까지 모두 방문이 끝나면 리프노드부터 부모노드의 비용을 더한다.
            x.cost += next.cost;
            // 항상 자식노드들 중 가장 큰 비용을 가진 자식을 리턴한다.
            if (x.cost > result.cost) {
                result = x;
            }
        }
        
        return result;
    }
}