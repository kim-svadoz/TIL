import java.io.*;
import java.util.*;

public class p14712 {
    static int n, m, len, totalSum, cnt;
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

        dfs(0, 1);
        System.out.println("cnt:"+cnt);
        dfs(0, 0);
        System.out.println("cnt:"+cnt);
        
        totalSum = 1 << len;
        
        System.out.println(totalSum - cnt);
    }

    // 모든 배치를 만들자.
    static void dfs(int index, int num) {
        // 끝까지 탐색했을 때
        if (index == len) {
            //cnt = Math.max(cnt, sum);
            return;
        }

        int r = index / m;
        int c = index % m;
        map[r][c] = num;
        
        //print();
        // 해당 위치에 넴모가 올려져있다면 2x2 모양 체크
        if (map[r][c] == 1) {
            if (r < n && r - 1 >= 0 && c < m && c - 1 >= 0)  {
                if (map[r][c - 1] == 1 && map[r - 1][c] == 1 && map[r - 1][c - 1] == 1) {
                    System.out.println("index:"+index+", r:"+r+", c:"+c+", map[r][c]:"+map[r][c]);
                    print();
                    cnt++;
                }
            }
        }

        // 다음 index에 넴모를 하나 올려 놓는다.
        dfs(index + 1, 1);

        // 다음 index에서는 넴모를 올려놓지 않는다.
        dfs(index + 1, 0);
    }
    
    static void print() {
        System.out.println("-----------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}