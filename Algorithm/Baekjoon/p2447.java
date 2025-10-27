import java.io.*;
public class p2447 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int N;
    static char map[][];
    static int multi;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // 27

        map = new char[N][N];

        printStar(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    static void printStar(int r, int c, int n, boolean blank) {
        if (blank) {
            for (int i = r; i < r + n; ++i) {
                for (int j = c; j < c + n; ++j) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (n == 1) {
            map[r][c] = '*';
            return;
        }

        int len = n / 3;
        int count = 0;
        for (int i = r; i < r + n; i += len) {
            for (int j = c; j < c + n; j += len) {
                count ++;
                if (count == 5) {
                    printStar(i, j, len, true);
                } else {
                    printStar(i, j, len, false);
                }
            }
        }
    }
}