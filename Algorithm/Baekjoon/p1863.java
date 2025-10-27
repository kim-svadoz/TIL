/*
	스카이라이 쉬운거
- 고도가 변하는 지점들을 모두 체크하면서, Stack 자료구조를 이용한다.
- 고도가 같다면 그대로 가면되고, 고도가 낮다면 건물의 개수가 추가되어야 한다.
*/
import java.io.*;
import java.util.*;
public class p1863 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[50002];
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = b;
        }
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!stk.empty() && stk.peek() > arr[i]) {
                answer ++;
                stk.pop();
            }
            
            if (!stk.empty() && stk.peek() == arr[i]) {
                continue;
            }
            
            stk.push(arr[i]);
        }
        System.out.println(answer);
    }
} 