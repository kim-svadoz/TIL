package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class FarsetNode_49189 {

	public static void main(String[] args) {

	}

	public int solution(int n, int[][] edge) {
        int[] dist = new int[n+1];
        boolean [][] map = new boolean[n+1][n+1];
        for (int i = 0; i < edge.length; i++) {
            map[edge[i][0]][edge[i][1]] = map[edge[i][1]][edge[i][0]] = true;
          }

        Queue<Integer> nodes = new LinkedList<Integer>();
        nodes.add(1);

        // BFS 탐색
        int maxDist = 0;
        while(!nodes.isEmpty()) {
            int i = nodes.poll();

            for (int j = 2; j <= n; j++) {
                if( dist[j] == 0 && map[i][j] ) {
                    dist[j] = dist[i] + 1;
                    nodes.add(j);
                    maxDist = Math.max(maxDist,dist[j]);
                }
            }
        }

        // 가장 멀리 있는 노드가 몇 개인지 계산
        int count = 0;
        for (int d : dist) {
            if( maxDist == d )
                count ++;
        }

        return count;

    }
}
