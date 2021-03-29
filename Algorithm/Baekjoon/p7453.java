/*
부분 합이 0인 네수

소팅할때 Collections.sort 대신 Arrays.sort를 사용해야 한다.
이유는 소팅에서 primitive type의 경우 dual pivot quicksort가 수행되는데 non primitive type은 merge sort가 수행이 되기 때문.
참조지역성으로 인해 merge sort에서는 캐시 히트율이 떨어져 퀵소트보다 느리다.

AB의 범위를 -1 해주는 오류를 범해서 계속 틀렸습니다가 떴었다... 범위체크를 확실하게 하고가자.
*/
import java.io.*;
import java.util.*;
public class p7453 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        
        int idx = 0;
        int[] AB = new int[n * n];
        int[] CD = new int[n * n];
        // A+B와 C+D의 부분 합 리스트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = (A[i] + B[j]);
                CD[idx] = (C[i] + D[j]);
                idx ++;
            }
        }

        // 오름차순으로 배열 정리
        Arrays.sort(AB);
        Arrays.sort(CD);
        
        // 투포인터 구현
        int left_index = 0;
        int right_index = n * n - 1;
        long result = 0;
        while (left_index < n * n && right_index >= 0) {
            int left_val = AB[left_index];
            int right_val = CD[right_index];
            
            if (left_val + right_val == 0) {
                long left_cnt = 0;
                long right_cnt = 0;

                while (left_index < n * n && AB[left_index] == left_val) {
                    left_cnt++;
                    left_index++;
                }
    
                while (right_index >= 0 && CD[right_index] == right_val) {
                    right_cnt++;
                    right_index--;
                }
                result += left_cnt * right_cnt;
            } else if (left_val + right_val > 0) {
                right_index--;
            } else {
                left_index ++;
            }
        }
        System.out.println(result);
    }
}