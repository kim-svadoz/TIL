import java.io.*;
import java.util.*;

public class p21277 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n1, m1, n2, m2, max1, max2, min;
    static int map1[][], map2[][], mapMax[][];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        map1 = new int[n1][m1];
        max1 = Math.max(n1, m1);

        // map1 셋팅
        for (int i = 0; i < n1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m1; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        map2 = new int[n2][m2];
        max2 = Math.max(n2, m2);

        // map2 셋팅
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m2; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    map2[i][j] = 2;
                }
            }
        }

        // 최대 퍼즐 셋팅
        mapMax = new int[max1 + max2][max1 + max2];

        // 탐색하겠다.
        for (int i = 0; i < max1 + max2; i++) {
            for (int j = 0; j < max1 + max2; j++) {
                
            }
        }

        for (int offset_i = -50; offset_i <= 50; offset_i++) {
            for (int offset_j = -50; offset_j <= 50; offset_j++) {
                

                int valid = 1;
                
            }
        }
        dfs(0, 0);
    }

    static void dfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            
        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
