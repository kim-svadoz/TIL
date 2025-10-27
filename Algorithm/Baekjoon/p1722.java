/*
    순열의 순서
    조합론 + DP
    N개의 순열의 개수가 N!개 인 점을 생각
*/
import java.io.*;
import java.util.*;
public class p1722 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] f = new long[21];
        boolean[] visit = new boolean[21];
        Arrays.fill(f, 1);
        for (int i = 1; i <= 20; i++) {
            f[i] = f[i-1] * i;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int game = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        if (game == 1) {
            long k = Long.parseLong(st.nextToken());
            
            // 팩토리얼 가짓수 치기
            for (int i = 0; i < n; i++) {
                // 1~N 확인
                for (int j = 1; j <= n; j++) {
                    // 순열에 이미 존재하는 숫자면 넘어간다
                    if (visit[j]) continue;

                    // 팩토리얼 값이 k보다 작으면 k에서 팩토리얼 값을 빼준다
                    if (f[n - i - 1] < k) {
                        k -= f[n - i - 1];
                    } else {
                        // 팩토리얼 값이 k보다 크면 해당하는 원소의 숫자를 찾은 것.
                        // a[i]에 저장하고 순열에 존재하는 숫자를 체크해준다
                        arr[i] =j;
                        visit[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
        } else {
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            long ans = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < arr[i]; j++) {
                    // 1부터 해당하는 원소까지 팩토리얼 값을 늘려가며 더해준다.
                    if (visit[j]) continue;

                    ans += f[n - i - 1];
                }
                // 순열에 존재하는 숫자는 있다고 표시해준다.
                visit[arr[i]] = true;
            }
            sb.append(ans);
        }
        
        System.out.println(sb.toString());
    }
      
}