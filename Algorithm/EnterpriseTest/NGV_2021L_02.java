import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NGV_2021L_02 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 1;

        Stack<Integer> stk = new Stack<>();

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (i == n) continue;
            stk.push(i);
        }

        while (stk.size() > 0) {
            if (arr[stk.peek()] > arr[n]) {
                // 사물 인식 가능
                answer++;
            } else {
                // 사물 인식 불가능
            }
            stk.pop();
        }
        System.out.println(answer);
    }
}
