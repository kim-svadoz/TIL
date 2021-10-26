/**
 * BOJ 16918 봄버맨 : Silver 1
 * 구현
 * 
 * 문제 이해가 잘 안되서 하나하나 적어가며 이해 했다.
 * 0초 : 초기- 주어진 위치에 폭탄 설치
 * 1초 : 아무것도 안한다
 * 2초 : 폭탄이없는 곳에 폭탄을 설치한다
 * 3초 : 폭발(0초 폭탄)
 * 4초 : 폭탄 설치
 * 5초 : 폭발(2초  폭탄)
 * 6초 : 폭탄 설치
 * 7초 : 폭발(4초 폭탄)
 * ...
 */
import java.io.*;
import java.util.*;

public class p16918 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int r, c, n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static char[][] map;
    static int[][] time;
    static int t = 1;
    static Queue<Point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        time = new int[r][c];
        
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'O') {
                    q.add(new Point(i, j));
                    time[i][j] = 3; // 이 폭탄이 터지기 까지 3초 남음
                }
            }
        }

        while (t++ < n) {
            if (t % 2 == 0) {
                setBomb();
            } else {
                doBomb();
            }
        }
        print();
    }
    static boolean OOB(int i, int j) {
        return i < 0 || j < 0 || i >= r || j >= c;
    }

    static void setBomb() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '.') {
                    map[i][j] = 'O';
                    time[i][j] = t + 3;
                }
            }
        }
    }

    static void doBomb() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (time[i][j] == t) { // 현재 시간에 터져야 하는 폭탄들에 대해서 터트린다.(3초 전에 놓은 폭탄들)
                    bomb(i, j);
                }
            }
        }
    }

    static void bomb(int a, int b) {
        map[a][b] = '.';

        for (int i = 0; i < 4; i++) {
            int nr = a + dr[i];
            int nc = b + dc[i];
            if (OOB(nr, nc)) continue;
            if (time[nr][nc] == t) continue; // 연쇄폭발을 해야 하기 때문에 같은 시간에 놓은 폭탄들은 터트리지 않는다.
            if (map[nr][nc] == '.') continue;
            time[nr][nc] = 0;
            map[nr][nc] = '.';
        }
    }

    static void print() {
        for (int i = 0; i < r; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < c; j++) {
                sb.append(map[i][j]);
            }
            System.out.println(sb.toString());
        }
    }
}
