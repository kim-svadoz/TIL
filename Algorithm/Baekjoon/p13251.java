import java.io.*;
import java.util.*;
public class p13251 {
    static int n, m, k;
    static double answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        arr = new int[m];
        n = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            n += arr[i];
        }
        
        k = Integer.parseInt(br.readLine());
        answer = 0;
        for (int i = 0; i < m; i++) {
            comb(i);
        }
        System.out.println(answer >= 1 ? 1 : answer);
    }
    
    static void comb(int pick) {
        if (arr[pick] >= k) {
            int tmp = n;
            int tmp2 = k;
            // k개 만큼 뽑는다.
            double tmpAnswer = 1;
            while (tmp2-- > 0) {
                // 뽑을 때마다 총 개수가 줄어드므로
                tmpAnswer *= (arr[pick]-- / (double) tmp--);
            }
            answer += tmpAnswer;
        }
        return;
    }
}