import java.io.*;
import java.util.*;

public class p21275 {
    static BufferedReader br;
    static StringTokenizer st;
    static String x_a, x_b;
    static int x, a, b;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        x_a = st.nextToken();
        x_b = st.nextToken();

        int x_a_len = x_a.length();
        int x_b_len = x_b.length();

        for (int x = 0; x < Integer.MAX_VALUE; x++) {
            for (int i = 2; i <= 36; i++) {
                int tmp = x;
                int remain = 0;
                for (int j = x_a_len - 1; j > 0; j--) {
                    remain = tmp % i;
                    tmp /= i;
                    System.out.println("remain:"+remain+", >>>"+(x_a.charAt(j)-'0'));
                    if (remain != x_a.charAt(j)-'0') {
                        break;
                    }
                }
            }
        }
    }

}
