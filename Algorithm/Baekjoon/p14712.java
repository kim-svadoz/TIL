import java.io.*;
import java.util.*;

public class p14712 {
    static int n, m, len, totalSum, cnt, ret;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        len = n * m;
        cnt = 0;
        ret = 0;
        totalSum = 1 << len;

        dfs(0, 1);
        dfs(0, 0);

        System.out.println(totalSum - cnt);
        System.out.println(ret);
    }

    // 모든 배치를 만들자.
    static void dfs(int index, int num) {
        // 끝까지 탐색했을 때
        if (index == len) {
            //cnt = Math.max(cnt, sum);
            return;
        }

        int i = index / m;
        int j = index % m;
        
        if (num == 0) {
            for (int r = 0; r <= i; r++) {
                for (int c = 0; c <= j; c++) {
                    // 해당 위치에 넴모가 올려져있다면 2x2 모양 체크
                    if (map[r][c] == 1) {
                        if (r < n && r - 1 >= 0 && c < m && c - 1 >= 0)  {
                            if (map[r][c - 1] == 1 && map[r - 1][c] == 1 && map[r - 1][c - 1] == 1) {
                                System.out.println("index:"+index+", r:"+r+", c:"+c+", map[r][c]:"+map[r][c]);
                                cnt += 1;
                                map[r][c] = 0;
                                map[r - 1][c] = 0;
                                map[r][c - 1] = 0;
                                map[r - 1][c - 1] = 0;

                                dfs(index + 1, 1);
                                dfs(index + 1, 0);
                                map[r][c] = 1;
                                map[r - 1][c] = 1;
                                map[r][c - 1] = 1;
                                map[r - 1][c - 1] = 1;
                            }
                        }
                    }
                }
            }
        } else {
            map[i][j] = num;
            dfs(index + 1, 1);
            dfs(index + 1, 0);
        }
    }
}
