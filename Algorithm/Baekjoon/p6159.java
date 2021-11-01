/**
 * BOJ 6159 코스튬 파티 : Silver 4
 * 투포인터?
 */
import java.io.*;
import java.util.*;

public class p6159 {
    static int n, s;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        
        // 1 2 3 5
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (sum + arr[j] <= s) {
                    answer++;
                } else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }   
}
