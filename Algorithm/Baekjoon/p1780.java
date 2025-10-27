import java.io.*;
import java.util.*;

public class p1780 {
    static int N, map[][], cnt[];
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cnt = new int[3];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        System.out.println(cnt[0]);
        System.out.println(cnt[1]);
        System.out.println(cnt[2]);
    }

    static boolean check(int r, int c, int n) {
        int a = map[r][c];
        for (int i = r; i < r + n; ++i) {
            for (int j = c; j < c + n; ++j) {
                if(map[i][j] != a) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static void divide(int r, int c, int n) {
        if (check(r, c, n)) {
            cnt[map[r][c] + 1]++;
        } else {
            // 다르면 n을 3으로 나눠서 3으로 나눈거에 대한 첫 인덱스에 해당 되는 좌표로 재호출한다.
            // 0 ~ 8 의 n이 9였는데 만족이 안됐다면 n이 3이 되고 각 시작점 {0,0}, {0,3}, {0,6} 으로 다시 재호출한다.
            //                                                         {3,0}, {3,3}, {3,6}
            //                                                         {6,0}, {6,3}, {6,6}
            // 현재 좌표 + 새로운길이 * 0 or 1 or 2 -> 9로 나눈 새로운 좌표가 나온다.
            int len = n / 3;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    divide(r + len * i, c + len * j, len);
                }
            }
        }
    }
}
