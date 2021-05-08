// 3 5 8 11 13 16
import java.io.*;
import java.util.*;

public class KAKAO2021H_INTERN02 {
    public static void main(String[] args) throws IOException {
        String[][] places = 
        {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
            {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"}, 
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        solution(places);
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static char[][] map;
    static boolean flag;
    static public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int room = 0; room < places.length; room++) {
            String[] thisRoom = places[room];
            map = new char[5][5];
            for (int i = 0 ; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = thisRoom[i].charAt(j);
                }
            }
            int ret = check();
            System.out.println(ret);
            answer[room] = ret;
        }
        
        return answer;
    }

    static int check() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    visited = new boolean[5][5];
                    if (dfs(i, j, 0) == false) return 0;
                }
            }
        }

        return 1;
    }

    static boolean dfs(int r, int c, int md) {
        visited[r][c] = true;
        //System.out.println("r:"+r+", nc:"+c+", md:"+md);
        if (md == 2 && map[r][c] == 'P') return false;
        if (md >= 2) return true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] == 'P') {
                //System.out.println("nr:"+nr+", nc:"+nc+", md:"+md);
                return false;
            } else if (map[nr][nc] == 'O') {
                if (md <= 2) {
                    if (dfs(nr, nc, md + 1) == false) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= 5 || c >= 5;
    }
}
