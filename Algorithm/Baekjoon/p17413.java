import java.io.*;
import java.util.*;

public class p17413 {
    static String S;
    static Stack<Character> st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        S = br.readLine();
        st = new Stack<>();
        
        boolean flag = true;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '<') {
                flag = false;
                sb.append(pop()).append(c);
            } else if (c == '>') {
                flag = true;
                sb.append(c);
            } else if (!flag) {
                flag = false;
                sb.append(pop()).append(c);
            } else {
                if (c == ' ') {
                    sb.append(pop()).append(c);
                } else {
                    add(c);
                }
            }

            if (i == S.length() - 1) {
                sb.append(pop());
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void add(Character c) {
        st.add(c);
    }

    static String pop() {
        String answer = "";
        while (!st.isEmpty()) {
            answer += st.pop();
        }
        return answer;
    }
}
