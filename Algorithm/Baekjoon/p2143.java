/*
단순 부분합이 아니라, 연속된 부분합이라면 투포인터를 사용한다음에 또다시 투포인터를 활용한다
*/
import java.io.*;
import java.util.*;

public class p2143 {
    static int t, n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // target number
        t = Integer.parseInt(br.readLine());
        
        n = Integer.parseInt(br.readLine());
        int arr1[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        
        m = Integer.parseInt(br.readLine());
        int arr2[] = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        tp(arr1, list1);
        tp(arr2, list2);
        
        Collections.sort(list1);
        Collections.sort(list2);
        
        int left = 0;
        int right = list2.size() - 1;
        long result = 0;
        while (left < list1.size() && right >= 0) {
            int left_value = list1.get(left);
            int right_value = list2.get(right);
            
            if (left_value + right_value == t) {
                long left_cnt = 0;
                long right_cnt = 0;
                
                while(left < list1.size() && left_value == list1.get(left)) {
                    left++;
                    left_cnt++;
                }
                
                while(right >= 0 && right_value == list2.get(right)) {
                    right--;
                    right_cnt++;
                }
                result += left_cnt * right_cnt;
            } else if (left_value + right_value > t) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(result);
    }
    
    static void tp(int[] arr, List<Integer> list) {
        int lo = 0, hi = 0, sum = 0;
        while (lo < arr.length) {
            sum += arr[hi++];
            
            list.add(sum);
            if (hi == arr.length) {
                lo++;
                hi = lo;
                sum = 0;
            }
        }
    }
}

/********** HashMap으로 푸는 방법*****************  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());        
        int[] a = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(temp[i]);

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++)
            b[i] = Integer.parseInt(temp[i]);

        int[] sumA = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                sumA[idx++] = sum;
            }
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        long ans = 0;
        for (int sum : sumA)
            ans += map.getOrDefault(t - sum, 0);
        System.out.println(ans);
    }
}

*/