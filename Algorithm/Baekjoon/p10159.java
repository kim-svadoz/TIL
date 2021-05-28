/*
    저울
    Floyd-warshall

    2458(키순서) 와 비슷한 문제
    n이 100으로 모든 간선을 저장하기 충분하고,
    모든 정점간의 연결 유무를 파악하는 문제이므로 플로이드-와샬 알고리즘을 활용한다.
*/
import java.io.*;
import java.util.*;
public class p10159 {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int win = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());

            map[win][lose] = 1; // 연결
        }
        
        // floyd
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // 거쳐가는 정점인 K를 이용하여 연결되어 있는지 판별할 수 있다.
                    // i>k>j or i<k<j 이므로 대소관계를 알 수 있다.
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (map[i][j] == 0 && map[j][i] == 0) cnt++;
            }
            ans.append(cnt).append("\n");
        }
        System.out.println(ans.toString());
    }
}