import java.io.*;
import java.util.*;
public class p1517 {
    static int n;
    static long cnt, arr[], sorted[];
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cnt = 0;
        arr = new long[n];
        sorted = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        mergeSort(0, n - 1);
        System.out.println(cnt);
    }   

    static void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid+ 1, right);
            merge(left, mid, right);
        }
    }

    static void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int idx = left;

        while (i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                sorted[idx++] = arr[i++];
            } else {
                sorted[idx++] = arr[j++];
                cnt += (mid + 1 - i);
            }
        }

        while(i <= mid) {
            sorted[idx++] = arr[i++];
        }
        while(j <= right) {
            sorted[idx++] = arr[j++];
        }

        for (int k = left; k <= right; k++) {
            arr[k] = sorted[k];
        }

    }
}