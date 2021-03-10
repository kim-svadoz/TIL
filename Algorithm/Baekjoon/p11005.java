import java.io.*;
import java.util.StringTokenizer;

public class p11005 {
    static int N;
    static int B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        String ans = "";
        int rest;
        while (N > 0) {
            rest = N % B;
            if (rest < 10) {
                ans += (char)(rest + (int)'0');
            } else {
                ans += (char)(rest - 10 + (int)'A');
            }

            N /= B;
        }

        for (int i = ans.length() - 1; i >= 0; i--) {
            System.out.print(ans.charAt(i));
        }
    }
}
