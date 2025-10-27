import java.io.*;
import java.util.*;

public class p9019 {
    static BufferedReader br;
    static StringTokenizer st;
    static int T;
    static String[] command;
    static boolean[] visit;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            command = new String[10000];
            visit = new boolean[10000];
        
            bfs(a, b);
        }
    }

    static void bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        Arrays.fill(command, "");
        while (!q.isEmpty() && !visit[target]) {
            int cur = q.poll();
            int D = (2 * cur) % 10000;
            int S = (cur == 0) ? 9999 : cur - 1;
            int L = (cur % 1000) * 10 + cur / 1000;
            int R = (cur % 10) * 1000 + cur / 10;

            if(!visit[D]) {
                q.add(D);
                visit[D] = true;
                command[D] = command[cur] + "D";
            }

            if(!visit[S]) {
                q.add(S);
                visit[S] = true;
                command[S] = command[cur] + "S";
            }

            if(!visit[L]) {
                q.add(L);
                visit[L] = true;
                command[L] = command[cur] + "L";
            }

            if(!visit[R]) {
                q.add(R);
                visit[R] = true;
                command[R] = command[cur] + "R";
            }
        }
        System.out.println(command[target]);
    }
}
