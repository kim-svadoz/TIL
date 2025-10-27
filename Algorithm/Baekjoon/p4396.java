import java.io.*;
public class p4396 {
    static int n, m;
    static char[][] map, player;
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        player = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                player[i][j] = line.charAt(j);
            }
        }
        // 두 2차원 배열을 합치자.
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '*' && player[i][j] == 'x') {
                    flag = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 지뢰가 있는 칸이 열렸다면(flag) 지뢰가 있는 코든 칸이 별표(*)로 표시되어야 한다.
                if (flag && map[i][j] == '*') {
                    player[i][j] = '*';
                } else if (map[i][j] != '*' && player[i][j] == 'x') { // 지뢰가 없으면서 열린 칸은 숫자로 표시
                    int cnt = go(i, j);
                    player[i][j] = (char) (cnt + '0');
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(player[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static int go(int r, int c){
        int cnt = 0;
        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (OOB(nr, nc)) continue;
            if (map[nr][nc] == '*') cnt++;
        }
        return cnt;
    }
    static boolean OOB(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}