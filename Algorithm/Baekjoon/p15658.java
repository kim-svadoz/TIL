/*
    연산자끼워넣기
    백트래킹

    처음에 틀린 이유 : 최소 값을 0으로 지정해놓았다.. 문제를 잘 읽자.
*/
import java.io.*;
import java.util.*;
public class p15658 {
    static int n, min, max;
    static int[] arr;
    static int[] k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        k = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            k[i] = Integer.parseInt(st.nextToken());
        }
        
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        bt(arr[0], 1);
        System.out.println(max);
        System.out.println(min);
    }
    
    static void bt(int sum, int idx) {
        if (idx >= n) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            if (k[i] == 0) continue;
            k[i]--;
            switch (i) {
            case 0:
                bt(sum + arr[idx], idx + 1);
                break;
            case 1:
                bt(sum - arr[idx], idx + 1);
                break;
            case 2:
                bt(sum * arr[idx], idx + 1);
                break;
            case 3:
                bt(sum / arr[idx], idx + 1);
                break;
            }
            k[i]++;
        }
        return;
    }
}