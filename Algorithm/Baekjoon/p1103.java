/*
    게임
    50 * 50 맵에서 완전탐색을 하는 것은 worst case로 이론상 2500! 에 가까운 경우의 수가 발생할 수 있다.
    여러 조건으로 가지치기가 될 경우에서 50*50 맵은 bfs,dfs의 마지노선이다.

    따라서 보다 간단한 형태의 dp 등을 접목해 메모이제이션을 통해 효율성을 높여야한다.
    이 문제는 bfs+dp를 하게 되면 백트래킹이 매우 까다로우므로 "dfs + dp"를 활용한다.
*/
import java.io.*;
import java.util.*;

public class p1103 {
    static int n, m, answer;
    static boolean[][] visited;
    static int[][] board, dp;
    static boolean flag;
    // dir : 북 동 남 서 (북쪽에서부터 시계방향)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[55][55];
        dp = new int[55][55];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char tmp = line.charAt(j);
                if (tmp == 'H') {
                    board[i][j] = 0;
                } else {
                    board[i][j] = tmp - '0';
                }
            }
        }

        flag = false;
        answer = 0;
        visited = new boolean[55][55];
        visited[0][0] = true;
        go(0, 0, 1);

        System.out.println(flag ? -1 : answer);
    }

    static void go(int r, int c, int cnt) {
        if (cnt > answer) {
            answer = cnt;
        }
        // 가지치기용 DP배열에 현재 카운트 메모
        dp[r][c] = cnt;
        for (int i = 0; i < 4; i++) {
            int jump = board[r][c];
            int nr = r + dr[i] * jump;
            int nc = c + dc[i] * jump;
            if (OOB(nr, nc) || board[nr][nc] == 0) continue;
            if (visited[nr][nc]) {
                flag = true;
                return;
            };
            // 메모이제이션을 통해서 기억해놨다가 해당 칸이 지금보다 많다면 해당 루트로 탐색할 필요가 없다.
            if (dp[nr][nc] > cnt) continue;

            visited[nr][nc] = true;
            go(nr, nc, cnt + 1);
            visited[nr][nc] = false;
        }
    }
    

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
