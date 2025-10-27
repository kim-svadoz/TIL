import java.io.*;
import java.util.*;

public class p10828 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st;

        for (int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            int push_num = 0;
            String command = st.nextToken();
            if (command.equals("push")) {
                push_num = Integer.parseInt(st.nextToken());
                stack.push(push_num);
            } else if (command.equals("pop")) {
                if (stack.size() == 0) System.out.println("-1");
                else System.out.println(stack.pop()); 
            } else if (command.equals("size")) {
                System.out.println(stack.size());
            } else if (command.equals("empty")) {
                if (stack.isEmpty()) System.out.println("1");
                else System.out.println("0");
            } else if (command.equals("top")) {
                if (stack.size() == 0) System.out.println("-1");
                else System.out.println(stack.peek());
            }
        }
    }
}
