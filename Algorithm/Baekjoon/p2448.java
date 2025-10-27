import java.io.*;
import java.util.*;

public class p2448 {
    static int N;
    static char[][] map;
    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N - 1];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < 2 * N - 1; ++j) {
                map[i][j] = ' '; // 공백문자로 초기화
            }
        }

        printStar(0, N - 1, N);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < 2 * N - 1; ++j) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 분할정복 알고리즘
    static void printStar(int r, int c, int n) {
        // 패턴의 가장 작은 단위인가?
        if (n == 3) {
            map[r][c] = '*';
            map[r + 1][c - 1] = map[r + 1][c + 1] = '*';
            map[r + 2][c - 2] = map[r + 2][c - 1] = map[r + 2][c] = map[r + 2][c + 1] = map[r + 2][c + 2] = '*';
            return;
        }

        // 가장 작은 단위가 아니라면 더 작은 단위로 쪼개기
        int len = n / 2;
        // 그리고 그 단위의 가장 꼭대기 * 좌표로 재귀 호출
        printStar(r, c, len);
        printStar(r + len, c - len, len);
        printStar(r + len, c + len, len);
    }
}
