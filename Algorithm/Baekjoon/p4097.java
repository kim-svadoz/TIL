/*
    수익
    DP or 구현
*/
import java.io.*;
import java.util.*;

public class p4097 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                int number = Integer.parseInt(br.readLine());
                sum += number;
                max = Math.max(max, sum);

                if (sum < 0) sum = 0;
            }

            System.out.println(max);
        }
    }
}
