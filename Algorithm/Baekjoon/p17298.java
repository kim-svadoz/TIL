/*
    LINE 2021 Intern No.2와 유사
    BOJ 오큰수
    스택 활용
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        Stack<Integer> stk = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            /*
                스택이 비어있지 않으면서
                현재 원소가 스택의 맨 위 원소가 가리키는 원소보다 큰 경우
                해당 조건을 만족할 때 까지 계속 stack의 맨 위 원소를 pop하면서
                해당 인덱스의 값을 현재 원소로 바꿔준다.
            */
            while (!stk.isEmpty() && arr[stk.peek()] < arr[i]) {
                arr[stk.pop()] = arr[i];
            }

            stk.push(i);
        }

        // 남아있는 놈 잔처리
        // 스택의 모든 원소를 pop함 과 동시에 해당 인덱스의 value를 -1로 초기화
        while (!stk.isEmpty()) {
            arr[stk.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
