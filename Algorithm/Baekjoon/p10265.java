/*
    MT
    SCC + DP + knapsack + 위상정렬
    
    -------fail code
*/
import java.io.*;
import java.util.*;
public class p10265 {
    static List<List<Integer>> graph;
    static List<List<Integer>> regraph;
    static List<List<Integer>> res;
    static List<Integer> ret;
    static Stack<Integer> stk;
    static int[][] dp;
    static int n, k;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        regraph = new ArrayList<>();
        res = new ArrayList<>();
        stk = new Stack<>();
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            regraph.add(new ArrayList<>());
            res.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(st.nextToken());
            graph.get(x).add(i);
            regraph.get(i).add(x);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        
        Arrays.fill(visited, false);
        int groupNum = 0;
        while (!stk.isEmpty()) {
            int start = stk.pop();
            
            if (visited[start]) {
                continue;
            }
            
            redfs(start, groupNum);
            groupNum++;
        }
        
        int[] arr = new int[groupNum];
        ret = new ArrayList<>();
        // 총 groupNum 개수만큼의 그룹이 형성되었다.
        for (int i = 0; i < groupNum; i++) {
            int size = res.get(i).size();
            System.out.println("i:"+i+", size:"+size);
            arr[i] = size;
        }
        Arrays.sort(arr);
        
        dp = new int[groupNum][k + 1];
        System.out.println(knapsack(0, k));
    }
    
    static int knapsack(int startIndex, int remainSeats) {
        if (dp[startIndex][remainSeats] != 0) {
            return dp[startIndex][remainSeats];
        }
        if (startIndex == res.size() || remainSeats == 0) return 0;

        int r1 = knapsack(startIndex + 1, remainSeats);

        int r2 = -1;
        List<Integer> pouch = res.get(startIndex);
        Integer[] balls = pouch.toArray(new Integer[0]);
        Arrays.sort(balls);

        for (int ball : balls) {
            if (ball <= k) {
                r2 = Math.max(r2, knapsack(startIndex + 1, k - ball) + ball);
            } else {
                break;
            }
        }
        dp[startIndex][remainSeats] = Math.max(r1, r2);
        
        return dp[startIndex][remainSeats];
    }
    
    static void dfs(int start) {
        visited[start] = true;
        
        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        stk.push(start);
    }
    
    static void redfs(int start, int groupNum) {
        visited[start] = true;
        res.get(groupNum).add(start);
        
        for (int next : regraph.get(start)) {
            if (!visited[next]) {
                redfs(next, groupNum);
            }
        }
    }
}