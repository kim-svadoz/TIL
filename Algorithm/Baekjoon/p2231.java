import java.io.*;
import java.util.*;

public class p2231 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int len = String.valueOf(n).length();

        int minval = n - 9 * len;
        int answer = 0;
        for (int i = minval; i < n; i++) {
            int sum = i;
            int num = i;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            
            if (sum == n) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
