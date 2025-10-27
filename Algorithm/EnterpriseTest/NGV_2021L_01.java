import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NGV_2021L_01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();
        int cnt = 0;
        boolean flag = false;
        while (n > 0) {
            String tmp;
            if (n > 4) {
                tmp = input.substring(cnt * 4, cnt * 4 + 4);
            } else {
                tmp = input.substring(cnt * 4, cnt * 4 + n);
            }
            cnt++;
            System.out.println("n::"+n);
            int size = n % 4 == 0 ? 4 : n % 4;
            for (int i = 0; i < size; i++) {
                char c = tmp.charAt(i);
                if (i % 2 == 0) {
                    if (c == 'R') {
                        flag = true;
                        break;
                    } 
                } else {
                    if (c == 'L') {
                        flag = true;
                        break;
                    }
                }
            }
            n -= 4;
        }
        if (flag) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
