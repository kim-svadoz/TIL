/*
    사다리조작
    dfs + 백트래킹
*/
import java.io.*;
import java.util.*;

public class p15684 {
    static class Line {
        int r, c;
        public Line (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n, m, h, answer;
    static boolean finish;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            //target 점선에 num번 세로선 부착 가능
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
            map[r][c+1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (finish) break;            
        }
        System.out.println(finish ? answer : -1);
    }

    static void dfs(int start, int cnt) {
        if (finish) return;
        if (answer == cnt) {
            if (check()) finish = true;
            return;
        }

        for (int i = start; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(i, cnt + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int r = 1, c = i;
            for (int j = 0; j < h; j++) {
                if (map[r][c] == 1) c++; // 우측
                else if (map[r][c] == 2) c--; // 좌측
                
                r++; // 아래
            }

            if (c != i) return false; // i번 출발해서 i번으로 도착하지 않는다면
        }
        return true;
    }
}
