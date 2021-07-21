import java.io.*;
import java.util.*;

public class p3055 {
    static int N, M;
    static char[][] map;
    static int[][] dist_water, dist_hedgegog;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }

        dist_water = new int[N][M];
        dist_hedgegog = new int[N][M];
        visit = new boolean[N][M];

        process();
    }

    static void bfs_water() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist_water[i][j] = -1;
                visit[i][j] = false;
                if (a[i].charAt(j) == '*') {
                    q.add(i);
                    q.add(j);
                    dist_water[i][j] = 0;
                    visit[i][j] =  true;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (OOB(nx, ny)) continue;
                if (a[nx].charAt(ny) != '.') continue;
                if (visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y] + 1;
            }
        }
    }

    static void bfs_hedgehog() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist_hedgegog[i][j] = -1;
                visit[i][j] = false;
                if (a[i].charAt(j) == 'S') {
                    q.add(i);
                    q.add(j);
                    dist_hedgegog[i][j] = 0;
                    visit[i][j] =  true;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (OOB(nx, ny)) continue;
                if (a[nx].charAt(ny) != '.' && a[nx].charAt(ny) != 'D') continue;
                if (dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_hedgegog[x][y] + 1) continue;
                if (visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist_hedgegog[nx][ny] = dist_hedgegog[x][y] + 1;
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    static void process() {
        bfs_water();
        bfs_hedgehog();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i].charAt(j) == 'D') {
                    if (dist_hedgegog[i][j] == -1) {
                        System.out.println("KAKTUS");
                    }  else {
                        System.out.println(dist_hedgegog[i][j]);
                    }
                }
            }
        }
    }

}
