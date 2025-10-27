/*
    줄세우기
    위상정렬 기본
    결과값이 여러개라면 아무거나 출력하면 되기때문에 단순 Queue를 이용해 구현하였다.
*/
import java.io.*;
import java.util.*;

public class p2252 {
    static int n, m;
    static int[] indegree;
    static boolean[] visited;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken());
            // a가 b보다 앞에 서야한다.
            list.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur+" ");
            for (int next : list.get(cur)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
