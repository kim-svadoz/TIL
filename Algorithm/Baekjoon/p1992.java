import java.io.*;
public class p1992 {
    static BufferedReader br;
    static int N, map[][];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String input = br.readLine();
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(input.substring(j, j + 1));
            }
        }
        divide(0, 0, N);
    }

    static boolean check(int r, int c, int n) {
        int a = map[r][c];
        for (int i = r; i < r + n; ++i) {
            for (int j = c; j < c + n; ++j) {
                if(a != map[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void divide(int r, int c, int n) throws IOException {
        if(check(r, c, n)) {
            System.out.print(map[r][c]);
        } else {
            System.out.print("(");
            int len = n / 2;
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    divide(r + len * i, c + len * j, len);
                }
            }
            System.out.print(")");
        }
    }
}