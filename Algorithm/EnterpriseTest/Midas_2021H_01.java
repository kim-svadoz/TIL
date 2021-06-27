import java.io.*;
import java.util.*;

public class Midas_2021H_01 {
    public static void main(String[] args) {
        int n = 9;
        int[][] mine = {
            {1, 1},
            {1, 7},
            {2, 7},
            {3, 6},
            {4, 1},
            {4, 4},
            {4, 8},
            {8, 4},
            {8, 5},
            {9, 6},
        };
        solution(n, mine);
    }

    static int n;
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    public static int[][] solution(int N, int[][] mine) {
        n = N;
        int[][] answer = new int[n][n];
        map = new int[n][n];
        for (int[] curMine : mine) {
            map[curMine[0] - 1][curMine[1] - 1] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                if (map[i][j] == -1) {
                    cnt = -1;
                } else {
                    cnt = go(i, j);
                }
                
                answer[i][j] = cnt;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }

        return answer;
    }

    static int go(int r, int c) {
        int cnt = 0;
        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (OOB(nr, nc)) continue;
            if (map[nr][nc] == -1) cnt++;
        }
        return cnt;
    }

    static boolean OOB(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}
