import java.util.*;
import java.io.*;

public class p18430 {
    static int n, m, map[][], max;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = 0;
        bt(0, 0);
        System.out.println(max);
    }
    static void bt(int index, int sum) {
        if (index == n * m) {
            max = Math.max(max, sum);
            return;
        }
        int r = index / m;
        int c = index % m;

        if (!visit[r][c]) {
            if (r + 1 < n && c - 1 >= 0 && !visit[r+1][c] && !visit[r][c-1]) {
                visit[r][c] = true;
                visit[r+1][c] = true;
                visit[r][c-1] = true;
                int nSum = sum + rightUp(r, c);
                bt(index + 1, nSum);
                visit[r][c] = false;
                visit[r+1][c] = false;
                visit[r][c-1] = false;
            }
            if (r - 1 >= 0 && c - 1 >= 0 && !visit[r-1][c] && !visit[r][c-1]) {
                visit[r][c] = true;
                visit[r-1][c] = true;
                visit[r][c-1] = true;
                int nSum = sum + rightDown(r, c);
                bt(index + 1, nSum);
                visit[r][c] = false;
                visit[r-1][c] = false;
                visit[r][c-1] = false;
            }
            if (r + 1 < n && c + 1 < m && !visit[r+1][c] && !visit[r][c+1]) {
                visit[r][c] = true;
                visit[r][c+1] = true;
                visit[r+1][c] = true;
                int nSum = sum + leftUp(r, c);
                bt(index + 1, nSum);
                visit[r][c] = false;
                visit[r+1][c] = false;
                visit[r][c+1] = false;
            }
            if (r - 1 >= 0 && c + 1 < m && !visit[r-1][c] && !visit[r][c+1]) {
                visit[r][c] = true;
                visit[r-1][c] = true;
                visit[r][c+1] = true;
                int nSum = sum + leftDown(r, c);
                bt(index + 1, nSum);
                visit[r][c] = false;
                visit[r-1][c] = false;
                visit[r][c+1] = false;
            }
        }
        bt(index + 1, sum);
    }
    
    static int rightUp(int r, int c) {
        return map[r][c - 1] + map[r + 1][c] + map[r][c] * 2;
    }
    static int rightDown(int r, int c) {
        return map[r][c - 1] + map[r - 1][c] + map[r][c] * 2;
    }
    static int leftUp(int r, int c) {
        return map[r][c + 1] + map[r + 1][c] + map[r][c] * 2;
    }
    static int leftDown(int r, int c) {
        return map[r][c + 1] + map[r - 1][c] + map[r][c] * 2;
    }
}