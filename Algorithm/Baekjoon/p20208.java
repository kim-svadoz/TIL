/**
 * BOJ 20208 
 * 진우의 민트초코우유
 * 구현 & permutation & bitmask
 */
import java.io.*;
import java.util.*;

public class p20208 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n, m, h;
    static int[][] map;
    static Point home;
    static List<Point> list;
    static int visited = 0;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 초기 체력
        h = Integer.parseInt(st.nextToken()); // 민트초코의 체력 증가량

        list = new ArrayList<>();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    home = new Point(i, j);
                } else if (map[i][j] == 2) {
                    list.add(new Point(i, j));
                }
            }
        }

        go(home.r, home.c, m, 0, false);        
        System.out.println(ans);
    }
    static void go(int r, int c, int hp, int count, boolean goBackHome) {
        if (goBackHome) {
            ans = Math.max(ans, count);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if ((visited & (1 << i)) > 0) { // 이미 방문된 곳
                continue;
            }
            int distance = Math.abs(r - list.get(i).r) + Math.abs(c - list.get(i).c);
            if (distance > hp) continue; // 현재 hp로 갈 수 없는 거리

            visited |= (1 << i); // 방문
            go(list.get(i).r, list.get(i).c, hp - distance + h, count + 1, false);
            visited ^= (1 << i); // 방문 해제
        }

        int distanceHome = Math.abs(r - home.r) + Math.abs(c - home.c);
        if (distanceHome <= hp && count > ans) { // 현재 위치에서 집에 갈 수 있다면 && 최대값이 갱신된다면
            go(home.r, home.c, hp - distanceHome, count, true);
        }
    }
}
