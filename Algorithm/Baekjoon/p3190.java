/**
 * BOJ 3190 뱀
 * 구현, 시뮬레이션
 */
import java.io.*;
import java.util.*;

public class p3190 {
    static class Trans {
        int x;
        String dir;
        public Trans(int x, String dir) {
            this.x = x;
            this.dir = dir;
        }
    }
    static class Snake {
        int r, c, dir; // 머리
        public Snake(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static int n, k, l;
    static int time = 0;
    static int[][] board;
    static boolean[][] check;
    static int[] trans;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 위쪽부터 시계방향
    static Queue<Snake> tail = new LinkedList<>();
    static Queue<Trans> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n][n];
        check = new boolean[n][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r - 1][c - 1] = 1; // 사과
        }
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            q.add(new Trans(x, dir));
        }

        go();
    }

    static void go() {
        Queue<Snake> head = new LinkedList<>();
        head.add(new Snake(0, 0, 1)); // 처음엔 오른쪽
        tail.add(new Snake(0, 0, -1));

        while (!head.isEmpty()) {
            Snake cur = head.poll();
            Snake next = step(cur);
            time++;
            if (next.dir == -1) break;
            if (!q.isEmpty() && q.peek().x == time) {
                Trans trans = q.poll(); // 방향전환
                int ndir;
                if (trans.dir.equals("L")) {
                    ndir = next.dir - 1;
                } else {
                    ndir = next.dir + 1;
                }
                if (ndir < 0) {
                    ndir = 3;
                } else if (ndir > 3) {
                    ndir = 0;
                }
                head.add(new Snake(next.r, next.c, ndir));
            } else {
                head.add(next);
            }
        }
        System.out.println(time);
    }

    static Snake step(Snake s) {
        int hr = s.r;
        int hc = s.c;
        int dir = s.dir;

        // 머리를 다음칸으로
        int nr = hr + dr[dir];
        int nc = hc + dc[dir];
        if (OOB(nr, nc)) return new Snake(0, 0, -1);
        if (check[nr][nc]) return new Snake(0, 0, -1);

        check[nr][nc] = true;
        tail.add(new Snake(nr, nc, -1));

        if (board[nr][nc] == 1) {
            board[nr][nc] = 0;
        } else {
            Snake t = tail.poll();
            check[t.r][t.c] = false;
        }

        return new Snake(nr, nc, dir);
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= n;
    }
}