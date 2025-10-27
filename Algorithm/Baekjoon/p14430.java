/*
    자원 캐기
    DP, 메모이제이션 활용
*/
import java.io.*;
import java.util.*;
public class p14430 {
    static int n, m;
    static int[][] dist, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // max(위쪽, 왼쪽) + 현재 로 메모이제이션한다.
                dist[i][j] = Math.max(dist[i - 1][j], dist[i][j - 1]) + map[i][j];
            }
        }
        System.out.println(dist[n][m]);
    }
}