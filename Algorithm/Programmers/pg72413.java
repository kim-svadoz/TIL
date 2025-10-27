/*
    카카오 2021 블라인드 4번 합승 택시 요금
    플로이드 와샬!
*/
import java.util.*;
class Solution {
    static final int INF = 400 * 100000;
    int[][] dist = new int[200][200];
    
    void floyd(int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        } 
        
        for (int[] edge : fares) {
            dist[edge[0] - 1][edge[1] - 1] = edge[2];
            dist[edge[1] - 1][edge[0] - 1] = edge[2];
        }
        
        floyd(n);
        
        --s;
        --a;
        --b;
        int answer = INF;
        for (int k = 0; k < n; k++) {
            answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        
        return answer;
    }
}