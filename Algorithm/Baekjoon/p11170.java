/*
    0의 개수
    구현
*/
import java.io.*;
import java.util.*;
public class p11170 {
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            System.out.println(solve(n, m));
        }
    }
    static int solve(int n, int m) {
        int ret = 0;
        
        for (int i = n; i <= m; i++) {
            ret += calc(i);
        }
        
        return ret;
    }
    
    static int calc(int num) {
        int ret = 0;
        for (char c : String.valueOf(num).toCharArray()) {
            if (c == '0') {
                ret++;
            }
        }
        return ret;
    }
}