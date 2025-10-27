import java.io.*;
import java.util.*;

public class p11652 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] card = new long[n];

        for (int i = 0; i < n; i++) {
            card[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(card);

        long target = card[0];
        int cnt = 1, max = 1;

        for (int i = 1; i < n; i++) {
            if(card[i] == card[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            } 
            
            if (cnt > max) {
                max = cnt;
                target = card[i];
            }
        }

        System.out.println(target);
    }

}
