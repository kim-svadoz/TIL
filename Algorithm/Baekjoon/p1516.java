/*
    게임개발
    위상정렬
    조건을 꼼꼼히 읽고 알맞은 코드를 작성하자.
*/
import java.io.*;
import java.util.*;
public class p1516 {
    static int n;
    static int[] time, indegree;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        time = new int[n + 1];
        indegree = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            time[i] = cost;
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                list[num].add(i);
                indegree[i]++;
            }
        }
        
        int[] answer = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                answer[i] = time[i];
            }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : list[cur]) {
                indegree[next]--;
                
                // 자신의 건물을 짓기전에 이전에 가장 오랜시간 걸린 값을 찾아서 올려야 한다.
                answer[next] = Math.max(answer[next], answer[cur] + time[next]);
                
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}