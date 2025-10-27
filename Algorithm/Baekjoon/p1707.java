import java.io.*;
import java.util.*;

public class p1707 {
    static int T, N, M, cnt;
    static BufferedReader br;
    static StringTokenizer st;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            list = new ArrayList[N + 1];

            for (int j = 1; j <= N; j++) {
                list[j] = new ArrayList<>();
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
    
                list[u].add(v);
                list[v].add(u);
            }

            boolean flag = true;
            int[] visit = new int[N + 1];
            Queue<Integer> q = new LinkedList<>();

            for (int j = 1; j <= N; j++) {
                if (visit[j] == 0) {
                    q.add(j);
                    visit[j] = 1;

                    while (!q.isEmpty() && flag) {
                        int cur = q.poll();
                        for (int next : list[cur]) {
                            if (visit[next] == 0) {
                                q.add(next);
                                visit[next] = visit[cur] * -1;
                            } else if (visit[next] == visit[cur]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(flag ? "YES" : "NO");
        }
    }
}
