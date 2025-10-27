/*
    우수마을
    tree + dp !
    dfs로 풀면 시간초과가 나기 때문에 메모이제이션을 이용해야 한다.

    조건 1 ) "'우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.""

    조건 2) "마을 사이의 충돌을 방지하기 위해서, 만일 두 마을이 인접해 있으면 두 마을을 모두 '우수 마을'로 선정할 수는 없다. 즉 '우수 마을'끼리는 서로 인접해 있을 수 없다."

    조건 3) "선정되지 못한 마을에 경각심을 불러일으키기 위해서, '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다.""
    
    중 조건 3)은 의미 없는 조건임을 파악 하는 것이 핵심이다.

    -> 인접한 마을 중 우수 마을이 하나도 없을 경우 그 마을은 2번 조건을 위배하지 않고 우수 마을로 선정할 수 있고, 또한 선정하는것이 항상 이득이다.
    따라서 2번 조건만을 고려하면서 최대 합을 구해도 무방하다.
*/
import java.io.*;
import java.util.*;

public class p1949 {
    static int n;
    static int[] arr;
    static List<Integer>[] list;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        // 트리는 사이클을 이루지 않기 때문에 어느 마을을 root로 선정해도 된다.
        // 편리하게 시작점인 1을 루트로 지정.
        dfs(1, -1);
        bw.write(Math.max(dp[1][0], dp[1][1]) + "\n");
        bw.flush();
    }

    // dp[i][1] : i마을을 우수마을로 선정했을 때의 주민수 총합
    // dp[i][0] : i마을을 우수마을로 선정하지 않았을 때의 주민수 총합

    static void dfs(int now, int parent) {
        for (int adj : list[now]) {
            if (adj != parent) {
                // 루트에서 리프노드까지 top-down으로 내려간 후
                dfs(adj, now);
                // 다시 리프노드에서부터 위로 올라가면서 dp 배열을 업데이트
                dp[now][1] += dp[adj][0];
                dp[now][0] += Math.max(dp[adj][0], dp[adj][1]);
            }
        }
        dp[now][1] += arr[now];
    }
}

/* dfs 시간초과 풀이

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static List<Integer>[] list;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n + 1];
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        bw.write(Math.max(dfs(1, false), dfs(1, true) + arr[1]) + "\n");
        bw.flush();
    }

    static int dfs(int now, boolean flag) {
        int sum = 0;
        visit[now] = true;
        
        for (int adj : list[now]) {
            if (visit[adj]) continue;
            if (flag) {
                sum += dfs(adj, false);
            } else {
                sum += Math.max(dfs(adj, false), dfs(adj, true) + arr[adj]);
            }
        }
        
        visit[now] = false;
        
        return sum;
    }
}

*/