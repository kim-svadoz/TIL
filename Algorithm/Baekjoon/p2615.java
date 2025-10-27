/*
    오목!!
    6개 이상인 것들을 체크하는 부분이 까다로웠다.
    따라서 각 방향(4개)에 따른 3차원 배열을 생성해서 해결한다.
    // 가로(->) 세로(아래), 대각선 2개(오른쪽 아래, 오른쪽 위)
    static int[] dx = { 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1 };
*/
import java.io.*;
import java.util.*;
public class p2615 {
    static int map[][] = new int[21][21];
    static int[][][] memo = new int[21][21][4];
    static int dr[] = {1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }
    static String solve() {
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                if (map[j][i] != 0) { // 여기서 map[i][j]가 아니라 왜 map[j][i]인지 고민해보자.
                    for (int d = 0; d < 4; d++) {
                        if (memo[j][i][d] == 0 && calc(j, i, d, map[j][i]) == 5) {
                            return map[j][i] + "\n" + j + " " + i;
                        }
                    }
                }
            }
        }
        return "0";
    }
    static int calc(int r, int c, int dir, int color) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        if (map[nr][nc] == color) {
            return memo[nr][nc][dir] = calc(nr, nc, dir, color) + 1;
        }
        return 1;
    }
}

/*  bfs 풀이법

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static boolean[][] visited = new boolean[19][19];
    static char[][] map = new char[19][19];
    static int[] dx = {1, 0, 1, -1};
    static int[] dy = {1, 1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<19; i++) {
            String input = br.readLine().replace(" ", "");

            for(int j=0; j<19; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                if(map[i][j]!='0') {
                    for(int d=0; d<4; d++) {
                        int cnt = bfs(i, j, dk);

                        if(cnt==5) {
                            System.out.println(map[i][j]);
                            System.out.println((i+1)+" "+(j+1));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static int bfs(int x, int y, int dir) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y, 1));
        int max = 0;

        while(!queue.isEmpty()) {
            Pair temp = queue.poll();
            max = Math.max(temp.cnt, max);

            int nx = temp.x+dx[dir];
            int ny = temp.y+dy[dir];

            if(nx<0 || nx>=19 || ny<0 || ny>=19 || map[nx][ny]!=map[temp.x][temp.y]) continue;

            queue.add(new Pair(nx, ny, temp.cnt+1));
        }

        if(max==5) {
            int nx = x-dx[idx];
            int ny = y-dy[idx];

            if(nx>=0 && nx<19 && ny>=0 && ny<19 && map[nx][ny]==map[x][y])
                max++;
        }

        return max;
    }

    public static class Pair {
        int x;
        int y;
        int cnt;

        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

*/