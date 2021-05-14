/*
    MT
    SCC + DP + knapsack + 위상정렬
    
    정방향 그래프(outdegree 간선)와 역방향 그래프(indegree 간선)를 만들어서,
    SCC를 찾고 난 후에, 역방향 그래프를 이용해서
    갈 수 있는 최소값(SCC) + ...(역방향 그래프 ) + 갈수 있는 최대값을 구한다.

    위에서 구한 최소 ~ 최대 dp 배열을 통해 knapsack 알고리즘을 수행한다.

    int root[i] : i번째 노드의 조상
    int scc[i] : i번째 노드가 속한 SCC groupNum
    int size[i] : i번째 SCC그룹의 크기
    int indegree[i] : i번째 노드로 들어오는 간선의 개수
*/
import java.io.*;
import java.util.*;
public class p10265 {
    static int n, k, cnt = 0, groupNum = 0; // cnt와 groupNum은 1부터 시작
    static int[] root, scc, size, indegree;
    static boolean[] visited;
    static Stack<Integer> stk;
    static List<List<Integer>> graph; // 정방향
    static List<List<Integer>> regraph; // 역방향
    public static void main(String[] args) throws IOException {
        init();
        makeSCC();
    
        // SCC 단위로 위상정렬한다.
        for (int i =  1; i <= n; i++) {
            for (int next : graph.get(i)) {
                // i과 next가 서로 다른 SCC 그룹에 속한다면
                if (scc[i] != scc[next]) {
                    // 역방향 그래프를 생성
                    regraph.get(scc[i]).add(scc[next]);
                    indegree[scc[next]]++;
                }
            }
        }
        
        visited[0] = true;
        for (int i = 1; i <= groupNum; i++) {
            // 들어오는 간선이 하나도 없는 SCC 그룹부터 위상정렬한다.
            if (indegree[i] == 0) {
                func(i, k, 0);
            }
        }
        int tmp = k;
        while (!visited[tmp]) {
            tmp--;
        }
        System.out.println(tmp);
    }

    static void func(int root, int k, int sum) {
        // root번 SCC그룹의 크기
        int temp = size[root];
        for (int i = k - temp; i >= sum; i--) {
            if (visited[i]) {
                visited[i + temp] = true;
            }
        }
        for (int next : regraph.get(root)) {
            func(next, k, sum + temp);
        }
    }
    
    // 타잔 알고리즘으로 SCC 형성
    static int Tarjan(int start) {
        cnt++;
        root[start] = cnt;
        stk.push(start);
        
        int parent = cnt;
        for (int next : graph.get(start)) {
            if (root[next] == 0) {
                parent = Math.min(parent, Tarjan(next));
            }
            else if (scc[next] == 0) {
                parent = Math.min(parent, root[next]);
            }
        }

        if (parent == root[start]) {
            groupNum++;
            while (true) {
                int t = stk.pop();
                scc[t] = groupNum;
                size[groupNum]++;

                if (t == start) break;
            }
        }

        return parent;
    }

    static void makeSCC() {
        for (int i = 1; i <= n; i++) {
            if (root[i] == 0) {
                Tarjan(i);
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        regraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            regraph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(st.nextToken());
            graph.get(x).add(i);
        }
        
        stk = new Stack<>();
        indegree = new int[n + 1];
        scc = new int[n + 1];
        size = new int[n + 1];
        root = new int[n + 1];
        visited = new boolean[n + 1];
    }
}