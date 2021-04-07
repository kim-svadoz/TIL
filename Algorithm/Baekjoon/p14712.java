import java.io.*;
import java.util.*;

public class p14712 {
    static int n, m, len, totalSum, cnt;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        len = n * m;
        cnt = 0;

        dfs(0, true);
        dfs(0, false);
        
        totalSum = 1 << len;
        
        System.out.println(totalSum - cnt);
    }

    // 모든 배치를 만들자.
    static void dfs(int index, boolean flag) {
        // 끝까지 탐색했을 때
        if (index == len) {
            return;
        }
        int r = index / m;
        int c = index % m;
        map[r][c] = flag;

        System.out.println("r:"+r+", c:"+c+", map[r][c]:"+map[r][c]);
        if (map[r][c]) {
            if (r < n && r - 1 >= 0 && c < m && c - 1 >= 0)  {
                if (map[r][c - 1] && map[r - 1][c] && map[r - 1][c - 1]) {
                    System.out.println("aaaaaaaaaa");
                    cnt++;
                    map[r][c] = false;
                    map[r - 1][c] = false;
                    map[r][c - 1] = false;
                    map[r - 1][c - 1] = false;
                }
            }
        }

        // 다음 index에 넴모를 하나 올려 놓는다.
        dfs(index + 1, true);
        map[r][c] = false;

        // 다음 index에서는 넴모를 올려놓지 않는다.
        dfs(index + 1, false);
        map[r][c] = true;
    }
}
