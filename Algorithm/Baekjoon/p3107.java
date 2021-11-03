/**
 * BOJ 3107 IPv6
 * 문자열, 구현
 */
import java.io.*;
import java.util.*;

public class p3107 {
    static String s;
    static Queue<Character> q;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        q = new LinkedList<>();
        sb = new StringBuilder();
        boolean flag = false;
        boolean temp = false;
        int part = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ':') {
                if (!temp) {
                    part++;
                }
                if (flag) {
                    temp = true;
                    continue;
                }
                // 이전꺼까지 확실하게 맞춰야한다.
                make4length();

                sb.append(c);
                flag = true;
            } else {
                q.add(c);
                flag = false;
            }
        }
        make4length();

        int idx = (part - 1) * 4 + part - 1;
        String left = sb.substring(0, idx);
        String right = sb.substring(idx, sb.length());

        StringBuilder tmp = new StringBuilder();
        int check = sb.length();
        while (check < 39) {
            int zero = 0;
            zero++;
            while (zero < 5) {
                tmp.append("0");
                check++;
                zero++;
            }
            tmp.append(":");
            check++;
        }
        String answer = left + tmp.toString() + right;

        System.out.println(answer);
    }

    static void make4length() {
        int zero = 0;
        while (q.size() + zero < 4) {
            sb.append("0");
            zero++;
        }
        while (!q.isEmpty()) {
            sb.append(q.poll());
        }
    }
}
