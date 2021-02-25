package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Work_2056 {
	static int N;
    static int[] indegree, times;
    static List<Integer>[] list;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
 
    public static void main(String[] args) throws IOException {
 
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
 
        times = new int[N + 1];
        indegree = new int[N + 1];
        list = new ArrayList[N + 1];
 
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
 
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
 
            times[i] = Integer.parseInt(st.nextToken());
            int work = Integer.parseInt(st.nextToken());
 
            for (int j = 0; j < work; j++) {
                int preWork = Integer.parseInt(st.nextToken());
                list[preWork].add(i);
                indegree[i] += 1;
            }
        }
        topologySort();
    }
 
    public static void topologySort() {
 
        int[] result = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
 
        for (int i = 1; i <= N; i++) {
 
            if (indegree[i] == 0) {
                q.offer(i);
                result[i] = times[i];
            }
        }
 
        for (int i = 1; i <= N; i++) {
 
            if (q.isEmpty()) {
                System.out.println("사이클 발생");
                return;
            }
 
            int node = q.poll();
 
            for (int j = 0; j < list[node].size(); j++) {
 
                int adj = list[node].get(j);
 
                if (result[adj] < times[adj] + result[node])
                    result[adj] = times[adj] + result[node];
                
                indegree[adj] -= 1;
                if (indegree[adj] == 0) {
                    q.offer(adj);
                }
 
            }
        }
        Arrays.sort(result);
        System.out.println(result[N]);
    }

}
