import java.io.*;
import java.util.*;

public class p11729 {
    static int N, map[][];
    static BufferedReader br;
    static StringTokenizer st;
    static BufferedWriter bw;
    /* 
    f(n) = 2 * f(n-1) + 1
    */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        bw.write((int) Math.pow(2, N) - 1 + "\n");

        Hanoi(N, 1, 2, 3);

        bw.flush();
        bw.close();
    }

    public static void Hanoi(int N, int start, int mid, int to) throws IOException {
        if (N == 1) {
            bw.write(start + " " + to + "\n");
            return;
        }

        // STEP 1 : A의 N - 1 개를 B로 이동
        Hanoi(N - 1, start, to, mid);

        // STEP 2 : A의 마지막 1개를 C로 이동
        bw.write(start + " " + to + "\n");

        // STEP 3 : B의 N - 1개를 C로 이동
        Hanoi(N - 1, mid, start, to);
    }

}
