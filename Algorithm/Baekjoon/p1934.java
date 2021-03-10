import java.io.*;
import java.util.*;
public class p1934 {
    static int n, a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println((a * b) / gcd(a, b));
        }
        
    }
    
    static int gcd(int p, int q) {
        if (q == 0)
          return p;
        else
          return gcd(q, p % q);
	}
}