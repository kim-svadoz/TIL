import java.io.*;
import java.util.*;

public class p1697 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    static int[] check;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        check = new int[100001];

        if (n == k) {
            System.out.println(0);
        } else {
            bfs(n);
        }
    }

    static void bfs(int num) {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        check[num] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) {
                    next = cur + 1;
                } else if (i == 1) {
                    next = cur - 1;
                } else {
                    next = cur * 2;
                }

                if (next == k) {
                    System.out.println(check[cur]);
                    return;
                }

                if (next < 0 || next >= check.length || check[next] != 0) continue;

                q.add(next);
                check[next] = check[cur] + 1;
            }
        }
    }
}
