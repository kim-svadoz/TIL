import java.io.*;
import java.util.*;
public class p1929 {
    public static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        boolean[] prime = new boolean[b + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= b; i++) {
            if (prime[i]) continue;
            
            if (i >= b) sb.append(i).append('\n');
            
            for (int j = i + i; j <= b; j += i) {
                prime[j] = true;
            }
        }

        System.out.println(sb);
    }
}