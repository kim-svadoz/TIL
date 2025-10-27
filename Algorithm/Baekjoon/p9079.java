import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p9079 {
    static int[] next;
    static boolean[] visited;
    static final int END = 511;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            next = new int[8];
            visited = new boolean[512];
            int start = 0;
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    start *= 2;
                    if (st.nextToken().equals("H")) start++;
                }
            }
            visited[start] = true;
            Queue<Integer> q = new LinkedList();
            q.add(start);
            int result = 0;
            boolean finish = false;
            while (!q.isEmpty()) {
                Queue<Integer> temp = new LinkedList();
                while (!q.isEmpty()) {
                    temp.add(q.poll());
                }
                while (!temp.isEmpty()) {
                    int cur = temp.peek();
                    if (cur == END || cur == 0) {
                        finish = true;
                        break;
                    }
                    temp.poll();
                    find_next(cur);
                    for (int i = 0; i < 8; i++) {
                        if (!visited[next[i]]) {
                            visited[next[i]] = true;
                            q.add(next[i]);
                        }
                    }
                }
                if (finish) break;
                result++;
            }
            if (!finish) result = -1;
            System.out.println(result);
        }
    }

    static void find_next(int start) {
        next[0] = change(start, 0, 1, 2);
        next[1] = change(start, 3, 4, 5);
        next[2] = change(start, 6, 7, 8);
        next[3] = change(start, 0, 3, 6);
        next[4] = change(start, 1, 4, 7);
        next[5] = change(start, 2, 5, 8);
        next[6] = change(start, 0, 4, 8);
        next[7] = change(start, 2, 4, 6);
    }
    
    static int change(int val, int idx1, int idx2, int idx3) {
        val = val^(1<<idx1);
        val = val^(1<<idx2);
        val = val^(1<<idx3);
        return val;
    }

}
