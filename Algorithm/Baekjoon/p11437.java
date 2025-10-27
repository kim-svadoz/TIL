/*
    LCA (Lowest Common Ancestor): 최소 공통 조상
    depth를 모두 구하고 부모 자식 간에 관계를 구하여 트리를 만든 다음에
    구하고자 하는 정점의 depth를 맞춰준 다음 같은 노드가 될 때까지 부모를 타고 하나씩 올라오면 된다.
*/
import java.io.*;
import java.util.*;

public class p11437 {
    static int n, m;
    static int[] parent, depth;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        // 트리 생성
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(u).add(v);
            list.get(v).add(u);
        }

        parent = new int[n + 1];
        depth = new int[n + 1];

        // 루트노드 1, depth 1을 시작으로 각 정점들의 depth를 구한다.
        dfs(1, 1);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int ret = lca(u, depth[u], v, depth[v]);
            System.out.println(ret);
        }
    }

    // dfs가 아닌 bfs로 각 노드의 depth를 구해도 된다.
    // depth 및 부모 관계 설정
    static void dfs(int from, int cnt) {
        depth[from] = cnt++;
        for (int next : list.get(from)) {
            // depth가 0이 아니면 이미 depth를 구한 노드이다.
            if (depth[next] != 0) continue;

            dfs(next, cnt);
            parent[next] = from;
        }
    }

    // LCA 알고리즘 
    static int lca(int a, int aDepth, int b, int bDepth) {
        // 두 노드의 depth가 같아질 때까지 한쪽을 올린다.
        if (aDepth > bDepth) {
            while (aDepth != bDepth) {
                aDepth--;
                a = parent[a];
            }
        } else if (aDepth < bDepth) {
            while (aDepth != bDepth) {
                bDepth--;
                b = parent[b];
            }
        }
        // 두 노드의 높이를 같게 했다.

        // 그 이후에 depth를 똑같이 증가시키다가 (내 윗 부모가 되는 것이 올라가는 과정)
        // 만난 같은 정점이 가장 가까운 공통 조상이 된다.
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
