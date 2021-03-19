import java.io.*;
import java.util.*;

public class p5397 {
    static BufferedReader br;
    static StringBuilder sb;
    static int T;
    static Deque dq;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String input = br.readLine();
            sb.append(getPassWord(input)).append("\n");
        }
        System.out.println(sb);
    }

    static String getPassWord(String input) {
        Stack<Character> preFix = new Stack<>();
        Stack<Character> postFix = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '<' :
                    if (!preFix.isEmpty()) {
                        postFix.push(preFix.pop());
                    }
                    break;
                case '>' :
                    if (!postFix.isEmpty()) {
                        preFix.push(postFix.pop());
                    }
                case '-' :
                    if (!preFix.isEmpty()) {
                        preFix.pop();
                    }
                    break;
                default :
                    preFix.push(c);
            }
        }

        while (!postFix.isEmpty()) {
            preFix.push(postFix.pop());
        }
        
        while (!preFix.isEmpty()) {
            sb.append(preFix.pop());
        }

        return sb.reverse().toString();
    }
}
