/*
실제 binarySearch 메소드 -> 를 활용하는게 직접 구현하는것보다 시간효율이 조금 더 좋다

    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        rangeCheck(a.length, fromIndex, toIndex);
        return binarySearch0(a, fromIndex, toIndex, key);
    }

// Like public version, but without range checks.
	private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = a[mid];

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}
*/
import java.io.*;
import java.util.*;
public class p2352 {
    static int n, arr[], lis[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        lis = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[1] = arr[1];
        int lis_cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (i == 1 || arr[i] > lis[lis_cnt - 1]) {
                lis[lis_cnt++] = arr[i];
            } else {
                int left = 0, right = lis_cnt;
                int idx = lis_cnt;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (lis[mid] >= arr[i]) {
                        idx = Math.min(idx, mid);
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                lis[idx] = arr[i];
            }
        }
        
        System.out.println(lis_cnt);
    }
}

/***************************** Arrays.binarySearch() 메소드 사용 코드 ***********************

import java.io.*;
import java.util.*;
public class Main {
    static int n, arr[], lis[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lis = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lis_cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || arr[i] > lis[lis_cnt - 1]) {
                lis[lis_cnt++] = arr[i];
            } else {
                int idx = Arrays.binarySearch(lis, 0, lis_cnt, arr[i]);
                idx = (idx < 0) ? -idx -1 : idx;
                lis[idx] = arr[i];
            }
        }
        
        System.out.println(lis_cnt);
    }
}

*/