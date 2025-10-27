import java.util.*;

public class Ebay_2021L_01 {
    public static void main(String[] args) {
        String s = "000111";
        solution(s);
    }

    static public int solution(String s) {
        int answer = -1;
        int n = s.length();
        Stack<Character> stk = new Stack<>();
        char[] arr = new char[s.length()];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i);
        }
        
        for (int i = 0; i < n; i++) {
            if (stk.isEmpty()) {
                stk.push(s.charAt(i));
            } else {
                char prev = stk.peek();
                char cur = s.charAt(i);
                if (cur == prev) {
                    stk.push(cur);
                } else {
                    stk.pop();
                }
            }
        }

        answer = stk.size();
        System.out.println(answer);
        return answer;
    }
}
