/**
 * BOJ 1028 다이아몬드 광산
 * DP
 * 
 * dp 너무 어렵다.. 지속적으로 코드 보면서 친해지자.
 * https://www.acmicpc.net/problem/1915 의 업그레이드 버전
 * 
 * 참고: https://blog.encrypted.gg/267
 */
import java.io.*;
import java.util.*;

public class p1028 {
    static int n, m;
    static int[][] map, d1, d2, d3, d4;
    public static void main(String[] args) throws IOException {
        init();
        pro();
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[770][770];
        d1 = new int[770][770]; // 북동
        d2 = new int[770][770]; // 남서
        d3 = new int[770][770]; // 북서
        d4 = new int[770][770]; // 남동

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line.charAt(j)+"");
            }
        }
    }
    static void pro() {
        // 예외처리
        if (n == 1 && m == 1) {
            System.out.println(map[0][0]);
            return;
        }

        // 대각선 네 방향으로 DP 수행
        for (int i = 0; i < n + m - 2; i++) { // (r, c)에서 r + c = i인 애들 -> "/" 모양
            for (int r = 0; r < n; r++) { // 북동
                int c = i - r;
                if (OOB(r, c)) continue;
                if (OOB(r - 1, c + 1)) d1[r][c] = map[r][c];
                else d1[r][c] = map[r][c] * (d1[r - 1][c + 1] + 1);
            }
            for (int c = 0; c < m; c++) { // 남서
                int r = i - c;
                if (OOB(r, c)) continue;
                if (OOB(r + 1, c - 1)) d2[r][c] = map[r][c];
                else d2[r][c] = map[r][c] * (d2[r + 1][c - 1] + 1);
            }
        }

        for (int i = 1 - m; i <= n - 1; i++) { // (r, c)에서 r - c = i인 애들 -> "\" 모양
            for (int r = 0; r < n; r++) { // 북서
                int c = r - i;
                if (OOB(r, c)) continue;
                if (OOB(r - 1, c - 1)) d4[r][c] = map[r][c];
                else d4[r][c] = map[r][c] * (d4[r - 1][c - 1] + 1);
            }
            for (int r = n - 1; r >= 0; r--) { // 남동
                int c = r - i;
                if (OOB(r, c)) continue;
                if (OOB(r + 1, c + 1)) d3[r][c] = map[r][c];
                else d3[r][c] = map[r][c] * (d3[r + 1][c + 1] + 1);
            }
        }

        //print();

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { // (i, j) :: 마름모의 왼쪽 꼭짓점
                int side = Math.min(d1[i][j], d3[i][j]); // 왼쪽 꼿짓점이므로, 북동과 남동 중 작은 값으로 설정
                if (max > side) continue;

                for (int size = side; size >= 1; size--) {
                    if (j + 2 * (size - 1) >= m) continue; // 세로축 기준 광산의 범위를 넘어가면
                    if (size < max) break;
                    if (Math.min(d2[i][j + 2 * (size - 1)], d4[i][j + 2 * (size -1)]) >= size) { // 남서와 북서를 체크
                        max = Math.max(max, size);
                        break; // max를 찾아서 더이상 보지 않아도 되므로 break
                    }
                }
            }
        }
        System.out.println(max);
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(d1[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============북동");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(d2[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============남서");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(d3[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============북서");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(d4[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============남동");
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
