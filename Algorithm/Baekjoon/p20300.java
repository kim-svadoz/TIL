/*
    m이 최소값이 되기 위해선 나머지 날들 큰 걸 들어야한다.
*/
import java.io.*;
import java.util.*;

public class p20300 {
    static int n;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long m = arr[n - 1];
        if (n % 2 == 0) {
            int left = 0, right = n - 1;
            while (right >= left) {
                long leftVal = arr[left++];
                long rightVal = arr[right--];
                m = Math.max(m, leftVal + rightVal);
            }
        } else {
            if (n == 1) {
                m = arr[0];
            } else {
                int left = 0, right = n - 2;
                while (right >= left) {
                    long leftVal = arr[left++];
                    long rightVal = arr[right--];
                    m = Math.max(m, leftVal + rightVal);
                }
            }
        }

        System.out.println(m);
    }
}
