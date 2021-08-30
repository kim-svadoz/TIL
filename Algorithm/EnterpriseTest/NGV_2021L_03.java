import java.io.*;
import java.util.*;

public class NGV_2021L_03 {
    static int n, m, x, min, max;
    static int[] indegree;
    static boolean[] visited;
    static int[] parent, rank;
    static List<List<Integer>> list;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        indegree = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a가 b보다 우선순위가 높다.
            list.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) { // 위상이 0, 즉 자기 앞에 아무도 없는 자동차부터 탐색을 시작한다.
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.println(cur);
            for (int next : list.get(cur)) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    q.add(next);

                    if (find(cur) == find(next)) continue;
                    union(cur, next);
                }
            }
        }

        int min = 1;
        int max = 1;
        for (int i = 1; i <= n; i++) {
            System.out.println(i+" >>>> "+parent[i]);
            if (parent[i] != x) {
                if (parent[parent[i]] == parent[i]) {
                    continue;
                } else {
                    max = n;
                }
            } else if (parent[i] == i) {
                max = i;
            }
        }

        System.out.println(min+" "+max);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank[u] < rank[v]) {
                parent[u] = v;
            } else {
                parent[v] = u;
                if (rank[u] == rank[v]) {
                    rank[u]++;
                }
            }
        }
    }
    static int find(int x) {
        if (x == parent[x]) return x;
        
        return parent[x] = find(parent[x]);
    }

}
