import java.io.*;
import java.util.*;

public class p9012 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i ++) {
            String line = br.readLine();
            vps(line);
        }
    }

    public static void vps(String input) {
        int cnt = 0;
        for (int j = 0; j < input.length(); j ++) {
            char c = input.charAt(j);
            
            if (c == '(') {
                cnt ++;
            } else if (c == ')') {
                cnt --;
            }

            if (cnt == -1) {
                System.out.println("NO");
                return;
            }
        }
        if (cnt != 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}

