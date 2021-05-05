/*
    용액
    투포인터
*/
import java.io.*;
import java.util.*;

public class p2467 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = n-1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        // 비교하는 두 값이 같아지면 안되므로 left < right
        while (left < right) {
            int left_value = arr[left];
            int right_value = arr[right];
            if (min > Math.abs(left_value + right_value)) {
                min = Math.abs(left_value + right_value);
                answer[0] = left;
                answer[1] = right;
            } 

            if (left_value + right_value < 0) {
                left++;
            } else {
                right--;
            }

        }
        System.out.println(arr[answer[0]]+" "+arr[answer[1]]);
    }
}
