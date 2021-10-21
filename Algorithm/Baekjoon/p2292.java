/**
 * BOJ 2292 벌집
 * 수학
 */
import java.io.*;

public class p2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        /**
         * 1
         * 2 ~ 7 : 6개
         * 8 ~ 19 : 12개
         * 20 ~ 37 : 18개
         * 38 ~ 61 : 24개
         * ... : 30개
         */
        int answer = 1;
        int range = 2;
        if (n == 1) {
            System.out.println(1);
        }
        else {
            while (range <= n) {
                range = range + (6 * answer);
                answer++;
            }
            System.out.println(answer);
        }
        
    }
}
