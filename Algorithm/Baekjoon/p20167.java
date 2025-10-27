/*
    꿈틀꿈틀 호석 애벌레
    DP + Two Pointer(Gold 5)
*/
import java.io.*;
import java.util.*;

public class p20167 {
    static class Item {
        int left;
        long satisfy;
    }
    static int n, k;
    static int[] arr;
    static long[] dp;
    static List<Item>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : i번째 먹이까지 먹어서 얻을 수 있는 최대 탈피 에너지
        dp = new long[n + 1];
        
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        long sum = 0;
        for (int l = 1, r = 0; l <= n; l++) {
            // r을 하나씩 늘려보면서 먹을 수 있다면 sum에 축적한다.
            while (r + 1 <= n && sum < k) {
                sum += arr[++r];
            }

            // r 에서 끝나는 위치의 정보들을 List에 담아준다.
            if (sum >= k) {
                Item item = new Item();
                item.left = l;
                item.satisfy = sum - k;
                list[r].add(item);
            }

            sum -= arr[l];
        }

        for (int r = 1; r <= n; r++) {
            // r번지 음식을 안먹는다면
            dp[r] = dp[r - 1];
            // r번지 음식을 먹는다면
            for (Item item : list[r]) {
                // 그전 까지 잘 먹고, 이번에 이 막대를 먹어서 생기는 에너지와의 최댓값
                dp[r] = Math.max(dp[r], dp[item.left - 1] + item.satisfy);
            }
        }
        System.out.println(dp[n]);
    }
}
