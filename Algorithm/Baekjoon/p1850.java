import java.io.*;
import java.util.*;
public class p1850 {
    static long a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        long mGcd = gcd(Math.max(a, b), Math.min(a, b));
        
        String result = "1".repeat((int) mGcd);

        System.out.println(result);
    }
    
    static long gcd(long p, long q) {
        if (q == 0)
          return p;
        else
          return gcd(q, p % q);
	}
}