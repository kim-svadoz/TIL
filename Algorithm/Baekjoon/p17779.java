/*
    게리맨더링2
    브루트포스, 시뮬레이션
*/
import java.io.*;
import java.util.*;

public class p17779 {
    static int n, total;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        // brute force로 모든 경우의 수를 전부 구한다.
        // 왜 이게 되는것인가?
        // -> n과 d1,d2 모두 최대 20이므로 20^4 : 160,000 번으로 가능하다
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 >= n) continue;
                        if (y - d1 < 0 || y + d2 >= n) continue;

                        solution(x, y, d1, d2);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static void solution(int x, int y, int d1, int d2) {
        boolean[][] check = new boolean[n][n];

        // 문제에 주어진 경계선 조건으로 경계선을 설정한다.
        for (int i = 0; i <= d1; i++) {
            check[x + i][y - i] = true;
            check[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            check[x + i][y + i] = true;
            check[x + d1 + i][y - d1 + i] = true;
        }

        // 여기서 문제에 제시된 조건을 통해 각 구역을 나누어 준다.
        
        int[] section = new int[5];

        // 1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (check[i][j]) break;
                section[0] += map[i][j];
            }
        }

        // 2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (check[i][j]) break;
                section[1] += map[i][j];
            }
        }

        // 3번 선거구
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (check[i][j]) break;
                section[2] += map[i][j];
            }
        }

        // 4번 선거구
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (check[i][j]) break;
                section[3] += map[i][j];
            }
        }

        // 5번 선거구
        section[4] = total;
        for (int i = 0; i < 4; i++) {
            section[4] -= section[i];
        }

        Arrays.sort(section);

        ans = Math.min(ans, section[4] - section[0]);
    }
}
