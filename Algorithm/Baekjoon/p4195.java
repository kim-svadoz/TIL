/*
    친구네트워크
    HashMap + Union-Find
*/
import java.io.*;
import java.util.*;
public class p4195 {
    static int[] parent;
    static int[] cnt;
    static int[] rank;
    static Map<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            cnt = new int[n*2 + 1];
            parent = new int[n*2 + 1];
            rank = new int[n*2 + 1];
            for (int i = 1, index = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                String[] strs = {st.nextToken(), st.nextToken()};
                for (int j = 0; j < 2; j++) {
                    if (!map.containsKey(strs[j])) {
                        cnt[index] = 1;
                        parent[index] = index;
                        map.put(strs[j], index++);
                    }
                }

                int u = map.get(strs[0]);
                int v = map.get(strs[1]);
                union(u, v);
                sb.append(cnt[find(v)]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    // Union-By-Rank 를 활용해 시간복잡도 대폭 저하
    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank[u] < rank[v]) {
                parent[u] = v;
                cnt[v] += cnt[u];
            } else {
                parent[v] = u;

                if (rank[u] == rank[v]) {
                    rank[u]++;
                }
                cnt[u] += cnt[v];
            }
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return find(parent[x]);
    }
}