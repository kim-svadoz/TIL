import java.io.*;
import java.util.*;
public class LINE_PIN_2021H_04 {
    public static void main(String[] args) throws IOException {
        int n = 4;
        int[][] queries = {
            {1,3},
            {1,2},
            {3,6},
            {3,-1},
            {4,5},
            {2,-1},
            {3, -1},
            {1, -1}
        };
        solution(n, queries);
    }
    static class MyStack {
        Deque<Integer>[] stk;
        int n;
        int center;
        public MyStack(int n) {
            stk = new Deque[n + 1];
            for (int i = 1; i <= n; i++) {
                stk[i] = new ArrayDeque<Integer>();
            }
            this.n = n;
            center = 0;
        }

        void input(int a, int b) {
            if (center == 0) {
                center = b;
                for (int i = 1; i <= n; i++) {
                    stk[i].push(b);
                }
            } else {
                stk[a].push(b);
            }
            
        }

        void pop(int a) {
            // size가 하나일 때 pop을 수행하면 center가 비게된다.
            if (stk[a].size() == 1) {
                list.add(stk[a].peek());
                for (int i = 1; i <= n; i++) {
                    if (stk[i].size() > 0) {
                        stk[i].pollLast();
                    }
                }

                int idx = a;
                if (stk[idx].size() == 0) {
                    idx = a + 1;
                    if (idx > n) {
                        idx = 1;
                    }
                }

                //System.out.println("a:"+a+", stk[i].peek():"+stk[a].peek());

                if (stk[idx].size() != 0) {
                    int num = stk[idx].peek();
                    center = num;
                    for (int i = 1; i <= n; i++) {
                        stk[i].addLast(num);
                    }
                }
            } else { // 해당 번호의 스택에서만 pop을 수행하면된다.
                //System.out.println("a:"+a+", stk[i].peek():"+stk[a].peekLast());
                if (stk[a].size() > 1) {
                    list.add(stk[a].peek());
                    stk[a].pop();
                } else {
                    list.add(-1);
                }
            }
        }
    }
    static List<Integer> list = new ArrayList<>();
    public static int[] solution(int n, int[][] queries) {
        MyStack stack = new MyStack(n);

        for (int[] query : queries) {
            int a = query[0];
            int b = query[1];
            // a번 스택에 b를 수행(push or pop(-1))한다.
            if (b != -1) {
                stack.input(a, b);
            } else {
                stack.pop(a);
            }
            System.out.println(stack.center);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
            System.out.print(answer[i]+" ");
        }
        return answer;
    }
}
