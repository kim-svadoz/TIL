/*
트리의 독립집합
1. 기본적으로 1번노드에서 시작해 그래프를 탐색하는데 , 현재노드를 선택과 비선택 두가지 가지로 나누어 메모이제이션을 채운다.
2. 지금 노드에서 선택했다면 인접한 노드는 선택하면 안되고, 선택하지 않았다면 선택해도 되고 안해도 된다.

트리는 비선형 구조이다. 탐색 순서를 정하기 위해서 dfs 트리를 만들어준다.
이 때 list[]는 입력 데이터를 이용해 만든 인접리스트,
p[][]는 dfs트리를 저장할 인접리스트이다.
dp[1][0] 은 i번 노드를 루트로 하는 서브트리에서 i노드를 포함하지 않는 경우의 답
dp[1][1] 은 i번 노드를 루트로 하는 서브트리에서 i노드를 포함하는 경우의 답 으로 정의.
*/
import java.io.*;
import java.util.*;

public class p2213 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, w[], dp[][];
    static List<Integer> list[];
    static List<Pair> p[][];
    static List<Integer> ans;
    static boolean visit[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[2][n + 1]; // dp 배열
        w = new int[n + 1]; // 가중치 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n + 1]; // 문제에 주어진 그래프
        p = new ArrayList[n + 1][2]; // dp의 계산을 위한 리스트배열

        // 0 : false, 1 : true
        for (int i = 1; i <= n; ++i) {
            list[i] = new ArrayList<>();
            p[i][0] = new ArrayList<Pair>();
            p[i][1] = new ArrayList<Pair>();
        }

        for (int i = 1; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        int start = Solution();

        // ans에 정답 리스트가 들어있음.
        ans = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();

        sb.append(getValue(1, start)).append('\n');

        Collections.sort(ans);
        for (int x : ans) {
            sb.append(x).append(' ');
        }
        System.out.println(sb.toString());
    }

    static int Solution() {
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        visit = new boolean[n + 1];
        if (dfs(1, true) > dfs(1, false)) {
            return 1;
        } else {
            return 0;
        }
    }

    static int dfs(int n, boolean t) {
        int tt = t ? 1: 0;
        if(dp[tt][n] != -1) {
            return dp[tt][n];
        }
        int sum = t ? w[n] : 0;
        visit[n] = true;
        if(t) {
            // true라면 다음 노드는 반드시 false를 pick
            for (int next : list[n]) {
                if(!visit[next]) {
                    p[n][tt].add(new Pair(next, 0));
                    sum += dfs(next, false);
                }
            }
        } else {
            // false라면 다음 노드는 true or false pick
            for (int next : list[n]) {
                if (visit[next]) continue;
                if (dfs(next, false) > dfs(next, true)) {
                    p[n][tt].add(new Pair(next, 0));
                    sum += dfs(next, false);
                } else {
                    p[n][tt].add(new Pair(next, 1));
                    sum += dfs(next, true);
                }
            }
        }
        visit[n] = false;
        return dp[tt][n] = sum;
    }

    static int getValue(int n, int t) {
        int sum = 0;
        if (t == 1) {
            ans.add(n);
            sum = w[n];
        }
        for (Pair next : p[n][t]) {
            sum += getValue(next.node, next.t);
        }
        return sum;
    }

    static class Pair {
        int node;
        int t;

        Pair(int node, int t) {
            this.node = node;
            this.t = t;
        }
    }
}

/* 
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////// 랭킹 50등대 고수님의 코드 /////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static PriorityQueue pq = new PriorityQueue();
    private static ArrayList<Integer>[] edges;
    private static int[] vertex;
    private static int[][] dp;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        vertex = new int[N + 1];
        edges = new ArrayList[N + 1];
        dp = new int[N + 1][3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i <= N; i++) {
            if(i != 0) vertex[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
            dp[i][0] = dp[i][1] = dp[i][2] = -1;
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            edges[node1].add(node2);
            edges[node2].add(node1);
        }

        StringBuilder sb = new StringBuilder();
        int[] result = {recursion(0, 1, 0), recursion(0, 1, 1)};

        sb.append(Math.max(result[0], result[1])).append(NEW_LINE);
        if(result[1] >= result[0]) vertexTrace(0, 1, 1);
        else vertexTrace(0, 1, 0);

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    private static void vertexTrace(int prev, int current, int select) {
        if(select == 1) pq.offer(current);

        for(int next: edges[current]) {
            if(prev == next) continue;

            int left = recursion(current,  next, 0);
            int right = recursion(current,  next, 1);


            if (left > right) {
                vertexTrace(current, next, 0);
            }
            else {
                if(select == 1) vertexTrace(current, next, 0);
                else vertexTrace(current, next, 1);
            }
        }
    }

    private static int recursion(int prev, int current, int select) {
        int result = dp[current][select];
        if(result != -1) return result;
        result = select == 1 ? vertex[current]: 0;

        for(int next: edges[current]) {
            if(prev == next) continue;

            if(select == 0) result += Math.max(recursion(current, next, 0), recursion(current, next, 1));
            else result += recursion(current, next, 0);
        }

        return dp[current][select] = result;
    }
}
*/
