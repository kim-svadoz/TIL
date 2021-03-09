import java.io.*;
import java.util.*;
public class p1476 {
    static int E, S, M, result;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int e = 0, s = 0, m = 0;
        result = 0;
        while(true) {
            if (E == e && S == s && M == m) {
                break;
            }
            e++;
            s++;
            m++;
            if (e == 16) {
                e = 1;
            }
            if (s == 29) {
                s = 1;
            }
            if (m == 20) {
                m = 1;
            }
            result++;
        }
        System.out.println(result);
    }
}