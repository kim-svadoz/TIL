/*
	키순서
	Floyd-Warshall
	
    모든정점간의 관계를 알아야 한다. -> 플로이드 와샬
	i학생이 바로 j학생 키를 알 수 있거나, i학생이 k 학생을 통해 j학생의 키를 알 수 있는지 체크하기 위해
	플로이드와샬을 사용한다.
*/
import java.io.*;
import java.util.*;
public class p2458 {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a학생이 b학생보다 작다면(연결되어 있다면) 1을 저장한다.
            map[a][b] = 1;
        }
        
        floyd();
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                
                // 둘다 1이 아니라는 것(연결되어 있지 않음)은 i와 j가 키를 비교할 수 없다는 것을 의미한다.
                // -> 자신의 키가 몇번째 인지 알 수 없음!
                // 하나라도 연결되어 있으면 비교할 수 있을텐데..
                if (map[i][j] != 1 && map[j][i] != 1) {
                    flag = false;
                    break;
                }
                
            }
            if (flag) answer++;
        }
        System.out.println(answer);
    }
    static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }
    }
}