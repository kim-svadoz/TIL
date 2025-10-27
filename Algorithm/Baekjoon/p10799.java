import java.io.*;
import java.util.*;

public class p10799 {
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        input = br.readLine();
        int cnt = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            if (c == '(') {
                stack.push(i);
            } else {
                if (stack.peek() == i - 1) {
                    // 레이저
                    stack.pop();
                    cnt+=stack.size();
                } else {
                    stack.pop();
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
