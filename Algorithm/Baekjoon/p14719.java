import java.io.*;
import java.util.*;

public class p14719 {
    static int h, w, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = 0;
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 각 인덱스마다 모이는 빗물은 계산한다. (첫번째 기둥과 마지막 기둥은 제외)
        for (int i = 1; i < w - 1; i++) {
            int current = arr[i];
            int leftMax = current;
            int rightMax = current;

            // 왼쪽에서 가장 높은 기둥의 높이
            for (int k = i - 1; k >= 0; k--) {
                if (arr[k] > current) {
                    leftMax = Math.max(leftMax, arr[k]);
                }
            }

            // 오른쪽에서 가장 높은 기둥의 높이
            for (int k = i + 1; k < w; k++) {
                if (arr[k] > current) {
                    rightMax = Math.max(rightMax, arr[k]);
                }
            }

            // 현재 벽보다 높은 벽이 양쪽에 있는 경우
            if (Math.min(leftMax, rightMax) > current) {
                ans += Math.min(leftMax, rightMax) - arr[i];
            }
        }
        System.out.println(ans);
    }
}
