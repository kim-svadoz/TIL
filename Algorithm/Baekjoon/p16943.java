/**
 * BOJ 16943 숫자 재배치 : Silver 1
 * 순열
 */
import java.io.*;
import java.util.*;

public class p16943 {
    static String a, b;
    static int bNum;
    static int[] arr;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken();
        bNum = Integer.parseInt(st.nextToken());

        visited = new boolean[a.length()];
        arr = new int[a.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = a.charAt(i) - '0';
        }

        answer = -1;
        nextPermutation(0, 0);
        System.out.println(answer);
    }
    /**
     * 
     * @param depth depth번째 수
     * @param num : 순열로 만들어진 숫자.
     */
    static void nextPermutation(int depth, int num) {
        // base case
        if (depth == a.length()) {
            answer = Math.max(answer, num);
            return;
        }
        // implementation
        for (int i = 0; i < a.length(); i++) {
            if (visited[i]) continue;
            if (depth == 0 && arr[i] == 0) continue; // 맨 앞자리가 0일 땐 넘어감
            if (num * 10 + arr[i] > bNum) continue; // 다음 순열에 해당하는 값이 b보다 크다면 넘어감
            visited[i] = true;
            nextPermutation(depth + 1, num * 10 + arr[i]);
            visited[i] = false;
        }
    }
}
