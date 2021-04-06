/*
    구간 합
<<접근방식>>
1. A % M + B % M = (A + B) % M 이므로 입력 받은 배열을 Prefix Sum 기법을 통해 누적합시킨 배열로 바꾸고 모두 M으로 나눈 나머지로 치환시킨다.
2. 그럼 이제 sum 배열이 오른쪽 항이 된 것인데, Prefix Sum 배열에서 S[i] - S[i-2]는 i-2부터 i까지의 부분합이 된다. 이를 이용하여 sum[i] = sum[j] 인 부분을 찾으면 된다.
3. 'i부터 j까지의 합을 M으로 나눈 나머지가 0이다'라는 것이 증명 되었으니 개수를 계산하면 되는데, sum[i] = sum[j]인 부분에서 2개만 고르면 0이 되는 부분이므로 nC2가 된다.

문제의 예시를 통해 보자.
1 2 3 1 2를 Prefix Sum 배열로 바꾸고 M으로 나눈 나머지로 치환시키면 1 0 0 1 0이 된다.

이 배열의 의미가 무엇인지 파악해보자. 처음 1번째 인덱스의 0의 뜻은 처음부터 1번째까지 더하면 나누어 떨어진다는 뜻이다.
2번째 인덱스의 0의 뜻은 처음부터 2번째까지 더하면 나누어 떨어진다는 뜻이다.
3번째 인덱스의 1의 뜻은 자기와 같은 수의 0번째 인덱스의 다음 수부터 3번째까지 더하면 나누어 떨어진다는 뜻이다.
4번째 인덱스의 0의 뜻은 처음부터 4번째까지 더하면 나누어 떨어진다는 뜻과, 자기와 같은 수의 1번째 또는 2번째 인덱스의 다음 수부터
 4번째까지 더하면 나누어 떨어진다는 뜻이다.

즉 자기와 같은 수들 중 2개를 선택해서 부분합을 하면 나누어 떨어진다는 것을 응용하여 0이 3개이므로
 3C2 = 3, 1이 2개이므로 2C2 = 1, 총 4개에 자기까지 더해서 나누어 떨어지는 즉, 0이 3개를 더해서 7개이다.

 추가적으로 자료형을 long을 써야 통과가 된다.(생각해보자.)
*/
import java.io.*;
import java.util.*;
public class p10986 {
    static final int MAX = 1000000 + 1;
    static long[] psum, cnt;
    static long n, m, ret;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stol(st.nextToken());
        m = stol(st.nextToken());
        psum = new long[MAX];
        cnt = new long[(int)m];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            long num = stol(st.nextToken());
            // 부분합의 나머지를 저장
            psum[i] = (psum[i - 1] + num) % m;
            // 나머지가 같은 부분합을 그룹화
            cnt[(int) psum[i]]++;
            // 부분합의 나머지가 0인 경우에는 답이므로 바로 카운팅한다.
            if (psum[i] == 0) ret++;
        }

        // 각 부분합의 나머지가 0인 경우 같은 그룹에서 nC2를 구한다.
        for (int i = 0; i < m; i++) {
            ret += cnt[i] * (cnt[i] - 1) / 2;
        }
        System.out.println(ret);
    }

    static long stol(String s) {
        return Long.parseLong(s);
    }
}