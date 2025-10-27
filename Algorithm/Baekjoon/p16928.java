/**
 * BOJ 16928 뱀과 사다리 게임 : Silver 1
 * BFS
 */
import java.io.*;
import java.util.*;

public class p16928 {
    static int n, m;
    static int[] arr, cnt;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[110];
        cnt = new int[110];
        visited = new boolean[110];

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u] = v;
        }
        // 매번 가장 최선의 선택.. 최단거리.. -> bfs ?!
        bfs();
    }
    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1); // 1번부터 시작
        visited[1] = true; // 한번 밟은 곳은 더이상 방문할 필요가 없다.

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == 100) {
                System.out.println(cnt[cur]);
                return;
            }
            // 모든 주사위 이동 횟수만큼의 가능성을 다 본다.
            for (int i = 1; i <= 6; i++) {
                int next = cur + i;
                if (next > 100) break;
                if (visited[next]) continue;

                // 사다리나 뱀이 있는 자리라면
                if (arr[next] != 0) {
                    if (!visited[arr[next]]) {
                        visited[arr[next]] = true;
                        cnt[arr[next]] = cnt[cur] + 1;
                        q.add(arr[next]);
                    }
                } else {
                    visited[next] = true;
                    cnt[next] = cnt[cur] + 1;
                    q.add(next);
                }
            }
            
        }
    }

}
