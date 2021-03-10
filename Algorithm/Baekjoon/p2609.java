import java.io.*;
import java.util.*;
public class p2609 {
    static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        System.out.println(gcd(a, b));
        System.out.println(lcm(a, b));
    }
    
    static int gcd(int p, int q) {
        if (q == 0)
            return p;
        else
            return gcd(q, p % q);
    }
    
    static int lcm(int m, int n) {
        int bigger = 0;
        bigger = (m > n) ? m : n;
        while (true) {
            if ((bigger % m == 0) && (bigger % n) == 0)
                return bigger;
            bigger++;
        }
    }
}