import java.io.*;
import java.util.*;

public class p9466 {
    static BufferedReader br;
    static StringTokenizer st;
    static int T, N, cnt, arr[];
    static boolean[] flag;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            visit = new boolean[N + 1];
            flag = new boolean[N + 1];
            arr = new int[N + 1];
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            // i 번 학생들의 선택은
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int start = 1; start <= N; start++) {
                if (!flag[start]) dfs(start);
            }
            System.out.println(N - cnt);
        }
    }

    static void dfs(int num) {
        if (visit[num]) {
            return;
        }
        visit[num] = true;

        int next = arr[num];

        if (!visit[next]) {
            dfs(next);
        } else {
            if (!flag[next]) {
                cnt++;
                for (int i = next; i != num; i = arr[i]) {
                    cnt++;
                }
            }
        }

        flag[num] = true;
    }
    
}
