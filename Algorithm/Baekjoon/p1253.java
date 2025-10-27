/*
    좋다
    투포인터
*/
import java.io.*;
import java.util.*;

public class p1253 {
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

        process();
    }

    static void process() {
        int ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (possible(i)) ans++;
        }

        System.out.println(ans);
    }

    static boolean possible(int idx) {
        int lo = 0;
        int hi = n - 1;
        int target = arr[idx];
        while (true) {
            if (lo == idx) lo++;
            else if (hi == idx) hi--;

            if (lo >= hi) break;

            int sum = arr[lo] + arr[hi];

            if (sum < target) lo++;
            else if (sum > target) hi--;
            else if (sum == target) {
                return true;
            }
        }
        return false;
    }
}
