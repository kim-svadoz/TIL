/*
    카카오 2020 인턴 경주로건설
    처음엔 dfs + dp로 풀려했지만, 풀이법이 생각나지 않음.
    다시보니 bfs + dp로 풀면 
    포인트는 현재 진행중인 방향을 체크해주는 것.
*/
import java.util.*;

class Solution {
    public class Node {
        int r, c, cost, dir;
        public Node (int r, int c, int cost, int dir) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.dir = dir;
        }
    }
    int n, min = Integer.MAX_VALUE;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int[][] map;
    
    public int solution(int[][] board) {
        n = board.length;
        map = board;
        bfs(new Node(0, 0, 0, -1));
        
        return min;
    }

    void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int cost = cur.cost;
            int dir = cur.dir;
            
            if (r == n - 1 && c == n - 1) {
                min = Math.min(min, cost);
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int ncost = cost;
                int ndir = i;
                
                if (OOB(nr, nc)) continue; // 범위 체크
                if (map[nr][nc] == 1) continue; // 벽 체크

                if (dir == -1) {
                    ncost += 100;
                } else if (dir == ndir) {
                    ncost += 100;
                } else {
                    ncost += 600;
                }
                    
                if (map[nr][nc] == 0 || map[nr][nc] >= ncost) {
                    map[nr][nc] = ncost;
                    q.add(new Node(nr, nc, ncost, i));
                }
            }
        }

    }

    boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= n;
    }
}