import java.io.*;
import java.util.*;

public class KAKAO2021H_INTERN03 {
    public static void main(String[] args) throws IOException {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        solution(n, k, cmd);
    }
    
    static boolean[] useAble;
    static int n;
    static Stack<Integer> stk = new Stack<>();
    public static String solution(int n, int k, String[] cmd) {
        n = n;
        useAble = new boolean[n];
        Arrays.fill(useAble, true);
        int size = cmd.length;
        int idx = k;
        for (String s : cmd) {
            String[] cArr = s.split(" ");
            if (cArr.length == 1) {
                // C, Z
                String Command = cArr[0];
                if (Command.equals("C")) {
                    // 현재 위치 idx인 곳을 지운다.                    
                    stk.push(idx);
                    System.out.println();
                    useAble[idx] = false;
                    idx ++;
                } else {
                    // 가장 최근에 지운 것을 복원한다
                    stk.pop();
                    useAble[idx] = true;
                }

            } else {
                // U, X
                String Command = cArr[0];
                int q = Integer.parseInt(cArr[1]);
                if (Command.equals("U")) {
                    while (q > 0) {
                        idx--;
                        if (idx < 0) idx = 0;
                        if (useAble[idx]) q--;
                    }
                } else {
                    while (q > 0) {
                        idx++;
                        if (idx >= n) idx = n - 1;
                        if (useAble[idx]) q--;
                    }
                }
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < stk.size(); i++) {
            pq.add(stk.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (pq.size() > 0 && pq.peek() == i) {
                pq.poll();
                sb.append("X");
            } else {
                sb.append("O");
            }
        }

        String answer = sb.toString();
        System.out.println(answer);
        return answer;
    }
}
