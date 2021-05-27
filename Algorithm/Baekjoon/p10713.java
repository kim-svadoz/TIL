/*
    기차 여행
    누적합 or Segment Tree (Lazy Propagation)

    <누적합 풀이>
    : 손익분기점과 같이 a*k 와 b*k + c 중 최소를 찾으면된다.
    : 도로를 지나는 횟수인 k를 구하는 것이 누적합 풀이 핵심

    <Lazy 풀이>
    : 업로드 예정
*/
import java.io.*;
import java.util.*;
public class p10713 {
    static int n, m;
    static int[] a, b, c, d, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        a = new int[n + 1];
        b = new int[n + 1];
        c = new int[n + 1];
        p = new int[n + 1];
        d = new int[n + 1];

        // 여행 순서
        for (int i = 1; i <= m; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 도시번호 1 2 3 4
        // 방문횟수 +   -
        // 방문횟수   + -
        // 방문횟수   +   -
        for (int i = 2; i <= m; i++) {
            int prev = p[i - 1]; // 1, 3, 2
            int next = p[i]; // 3, 2, 4
            
            if (prev > next) {
                int tmp = prev;
                prev = next;
                next = tmp;
            }
            // 방문횟수
            d[prev]++; // 이 철도부터는 방문 횟수가 1 늘어난다.
            d[next]--; // 이 철도부터는 방문 횟수가 1 줄어든다.
        }
        
        // 철도
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }
        
        long result = 0, psum = 0;
        for (int i = 1; i < n; i++) {
            psum += d[i];
            //System.out.println(i+"번째 철도의 d[i]:"+d[i]+", 방문횟수(psum):"+psum);
            result += Math.min(psum * a[i], psum * b[i] + c[i]);
        }
        System.out.println(result);
    }
}