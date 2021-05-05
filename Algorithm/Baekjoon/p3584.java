/*
    가장 가까운 공통 조상
    LCA 알고리즘 (최소 공통 조상)
    - 만약 트리의 루트가 한 루트로 고정되어 있다면 dfs를 통해 탐색할 수 있다. (BOJ 11437)
    - 루트가 정해져 있지 않다면 아래에서 위로 올라가는 방식을 이용하면된다.
*/
import java.io.*;
import java.util.*;

public class p3584 {
    static int t, n;
    static int[] parent;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            parent = new int[n + 1];

            // 트리 생성 및 부모관계 생성
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                list.get(p).add(c);
            }

            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int uDepth = getDepth(u);
            int vDepth = getDepth(v);

            int same = lca(u, uDepth, v, vDepth);
            System.out.println(same);
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

    static int getDepth(int x) {
        int ret = 0;
        int cur = x;
        while (cur != 0) {
            ret++;
            cur = parent[cur];
        }

        return ret-1;
    }
}
