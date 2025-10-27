import java.io.*;
import java.util.*;

public class KRAFTON_2021H_02 {
    public static void main(String[] args) throws IOException {
        int n = 8;
        int[][] network = {
            {1, 2},
            {3, 5},
            {4, 2},
            {5, 6},
            {7, 8}
        };
        int[][] repair =  {
            {3, 2, 10},
            {5, 4, 9},
            {4, 8, 2}
        };
        solution(n, network, repair);
    }

    static final int INF = 987654321;
    static int[][] map;
    static boolean[] visited;
    static int n;
    public static void solution(int n, int[][] network, int[][] repair) {
        n = n;
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++){
                if (i == j) continue;
                map[i][j] = INF;
            }
        }

        for (int[] net : network) {
            int u = net[0];
            int v = net[1];
            map[u][v] = 0;
            map[v][u] = 0;
        }
        

        for (int[] rep : repair) {
            int u = rep[0];
            int v =  rep[1];
            int cost = rep[2];
            map[u][v] = cost;
            map[v][u] = cost;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) map[i][j] = 0;

                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        int answer = 0;
        boolean flag = false;
        for  (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] >= INF) {
                    flag = true;
                    continue;
                }
                answer = Math.max(answer, map[i][j]);
            }
        }

        answer = flag ? -1 : answer;

        System.out.println(answer);
    }
}
