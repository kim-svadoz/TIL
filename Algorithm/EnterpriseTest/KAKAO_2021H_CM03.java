import java.util.*;

public class KAKAO_2021H_CM03 {
    static int dist[][], arr[];
    static boolean[][] visit;
    static final int INF = -100001;
    public static void main(String[] args) throws Exception{
        int n = 5;
        int[] passenger = {1,1,2,3,4};
        int[][] train = {
            {1,2},{1,3},{1,4},{1,5}
        };
        solution(n, passenger, train);
    }

    public static int[] solution(int n, int[] passenger, int[][] train) {
        int[] answer = {};
        dist = new int[n + 1][n + 1];
        arr = new int[n + 1];
        visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }

        for (int i = 1; i <= train.length; i++) {
                int u = train[i - 1][0];
                int v = train[i - 1][1];

                dist[u][v] = passenger[u - 1];
                dist[v][u] = passenger[v - 1];
            }

        answer = floyd(n);
        return answer;
    }

    static int[] floyd(int n) {
        int[] tmp = new int[2];
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++)  {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] < 0 || dist[i][k] + dist[k][j] < 0) continue;
                    if (visit[i][j]) continue;

                    dist[i][j] = Math.max(dist[i][j], dist[i][k] + dist[k][j] - dist[j][i]);
                    visit[i][j] = true;
                    visit[i][k] = true;
                    visit[k][j] = true;
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i] = Math.max(dist[i][j], arr[i]);
            }
            
            max = Math.max(dist[i][i], max);
            System.out.println(max);
        }

        for (int i = n; i >= 1; i--) {
            if (arr[i] == max) {
                System.out.println("i:"+i+" arr[i]:"+arr[i]+" max:"+max);
                tmp[0] = i;
                tmp[1] = max;
                break;
            }
        }
        return tmp;
    }
}