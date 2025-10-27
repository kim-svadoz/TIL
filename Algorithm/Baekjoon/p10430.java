import java.io.*;
import java.util.*;
public class p10430 {
    static int a, b, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        int w, x, y, z = 0;
        w = (a + b) % c;
        x = ((a % c) + (b % c)) % c;
        y = (a * b) % c;
        z = ((a % c) * (b % c)) % c;
        System.out.println(w);
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        
    }
}