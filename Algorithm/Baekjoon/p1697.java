import java.io.*;
import java.util.*;

public class p1697 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, cnt;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cnt = 0;

        int diff = Math.abs(n - k);

        recur(diff);
        System.out.println(cnt);
    }

    static void recur(int num) {
        if (num == 1) {
            cnt ++;
            return;
        }
        num /= 2;
        cnt++;
        recur(num);
    }
}
