import java.util.*;
public class Socar_2021L_01 {
    public static void main(String[] args) {
        String s = "[]([[]){}";
        System.out.println(solution(s));
    }

    static class Brackets {
        int smallCnt;
        int middleCnt;
        int bigCnt;
        public Brackets() {
            smallCnt = 0;
            middleCnt = 0;
            bigCnt = 0;
        }

        public char findDisapperChar() {
            if (smallCnt > 0) {
                return ')';
            } else if (smallCnt < 0) {
                return '(';
            } else if (middleCnt > 0) {
                return '}';
            } else if (middleCnt < 0) {
                return '{';
            } else if (bigCnt > 0) {
                return ']';
            }
            return '[';
            
        }
    }

    public static int solution(String s) {
        int answer = 0;
        Brackets brackets = new Brackets();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    brackets.smallCnt++;
                    break;
                case ')':
                    brackets.smallCnt--;
                    break;
                case '{':
                    brackets.middleCnt++;
                    break;
                case '}':
                    brackets.middleCnt--;
                    break;
                case '[':
                    brackets.bigCnt++;
                    break;
                case ']':
                    brackets.bigCnt--;
                    break;
            }
        }

        char target = brackets.findDisapperChar();

        StringBuilder sb = new StringBuilder(s);
        
        for (int i = 0; i < s.length(); i++) {
            if (isNiceString(sb.insert(i, target))) {
                answer++;
            }
            sb.delete(i, i+1);
        }
        return answer;
    }

    static boolean isNiceString(StringBuilder sb) {
        Stack<Character> stack = new Stack<>();

        //System.out.println("sb :: "+sb.toString());

        for (char c : sb.toString().toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (!stack.isEmpty()) {
                char top = stack.peek();
                if (top == '(' && c == ')') {
                    stack.pop();
                } else if (top == '{' && c == '}') {
                    stack.pop();
                } else if (top == '[' && c == ']') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
