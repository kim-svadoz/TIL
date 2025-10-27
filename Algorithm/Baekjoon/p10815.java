import java.io.*;
import java.util.*;

public class p10815 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, arr[], check[], answer[];
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        check = new int[M];
        answer = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M; ++i) {
            check[i] = Integer.parseInt(st.nextToken());
        }
        
        // 탐색 시작
        for (int i = 0; i < M; ++i) {
            int lo_idx = 0;
            int hi_idx = N - 1;
            int flag = 0;
            while(hi_idx >= lo_idx) {
                int mid_idx = (lo_idx + hi_idx) / 2;
                int mid = arr[mid_idx];
                
                if (check[i] > mid) {
                    lo_idx = mid_idx + 1;
                } else if (check[i] < mid){
                    hi_idx = mid_idx -1 ;
                } else {
                    flag = 1;
                    break;
                }
            }
            answer[i] = flag;
        }

        for (int i : answer) {
            System.out.print(i+" ");
        }
    }
}
