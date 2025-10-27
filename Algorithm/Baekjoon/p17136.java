import java.io.*;
import java.util.*;

public class p17136 {
    static int min, cnt = 0;
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        dfs(0, 0, 0);
        int answer = min == Integer.MAX_VALUE ? -1 : min;
        System.out.println(answer);
    }

    static void dfs(int r, int c, int cnt) {
        if (r >= 9 && c > 9) {
            min = Math.min(cnt, min);
            return;
        }        

       if (min <= cnt) {
           return;
       }

       if (c > 9) {
           dfs(r + 1, 0, cnt);
           return;
       }
        
        if (map[r][c] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && promising(r, c, i)) {
                    attach(r, c, i, 0);
                    paper[i]--;
                    dfs(r, c + 1, cnt + 1);
                    attach(r, c, i, 1);
                    paper[i]++;
                }
            }
        } else {
            dfs(r, c + 1, cnt);
        }
        
    }

    static boolean promising(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (OOB(i, j)) return false;

                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void attach(int r, int c, int size, int state) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = state;
            }
        }
    }
    static boolean OOB (int r, int c) {
        return (r < 0 || r >= 10 || c < 0 || c >= 10);
    }
}