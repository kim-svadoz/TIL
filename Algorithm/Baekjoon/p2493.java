/*
    BOJ 탑
    스택 활용
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n + 1];
        Stack<Integer> stk = new Stack<>();
        for (int i = 1; i <= n; i++) {
            while (stk.size() > 0) {
                if (arr[stk.peek()] >= arr[i]) {
                    answer[i] = stk.peek();
                    break;
                } else {
                    // 현재 있는 타워보다 왼쪽에 있는 타워는 영원히 수신받지 못하므로 지워도 된다.
                    // 아니, 지워야 한다.
                    stk.pop();
                }
            }

            stk.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
