/*
    List of Unique Numbers
    투포인터
*/
import java.io.*;
import java.util.*;
public class p13144 {
    static int n;
    static int[] arr, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        cnt = new int[100000 + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // "연속한" ~~ 수 -> 투포인터 접근해보자.
        tp();
    }

    static void tp() {
        long ans = 0;

        int lo = 1;
        int hi = 0;
        while (lo <= n) {
            // 현재 위치(lo)에서 시작해서 조건만족할때까지 hi를 늘려나간다
            while (hi + 1 <= n && cnt[arr[hi + 1]] == 0) {
                hi++;
                cnt[arr[hi]]++;
            }
            
            ans += hi - lo + 1;

            // lo를 한칸씩 밀면서 볼것이기 때문에 계산이 끝나고 나면 lo를 한 칸 밀어준다.
            // 이전 위치의 cnt는 다음번 계산에서 제외하기 위해 cnt[arr[lo]]--
            cnt[arr[lo++]]--;
        }
        System.out.println(ans);
    }
}
