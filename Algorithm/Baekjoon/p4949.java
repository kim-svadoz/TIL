import java.io.*;
import java.util.*;
public class p4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        while (true) {
            String input = br.readLine();
            if (input.equals(".")) {
                break;
            }
            
            boolean flag = true;
            Stack<Character> st = new Stack<>();
            for (char c : input.toCharArray()) {
                if (c == '(' || c == '[') {
                    st.push(c);
                }

                if (c == ')') {
                    if (st.size() == 0) {
                        flag = false;
                        break;
                    }
                    char cc = st.peek();
                    if (cc == '(') {
                        st.pop();
                    } else {
                        flag = false;
                        break;
                    }
                } else if (c == ']') {
                    if (st.size() == 0) {
                        flag = false;
                        break;
                    }
                    char cc = st.peek();
                    if (cc == '[') {
                        st.pop();
                    } else {
                        flag = false;
                        break;
                    }
                }
            }

            if (st.size() != 0 || !flag) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }

}