import java.util.*;
import java.io.*;
public class NTS_2021H_01_03_sql {
    public static void main(String[] args) throws IOException {
        int[] numbers = {1, 2, 4, 6, 9, 1, 3, 5};
        int[] start = {1, 3};
        int[] finish = {2, 4};
        solution1(numbers, start, finish);

        int n = 8;
        solution3(n);
    }
    /*  
    <sol.1>
    start배열의 idx부터 finish배열의 idx까지 더한 값을 return하는 answer[] 을 만들기.
    위의 예시에서는 answer[0] = numbers의 1번 idx ~ 2번 idx까지 더한 값,
    answer[1] = numbers의 3번 idx ~ 4번 idx까지 더한 값
    */
    public static int[] solution1(int[] numbers, int[] start, int[] finish) {
        int[] dp = new int[numbers.length];

        dp[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            dp[i] = dp[i - 1] + numbers[i];
        }

        int len = start.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            int sP = start[i];
            int fP = finish[i];
            answer[i] = dp[fP] - dp[sP - 1];
            System.out.println(answer[i]);
        }
        return answer;
    }

    /*
    <sol.3>
    숫자 n을 나타낼 수 있는 수 중 가장 좋은 수는 각 수를 곱했을 때 가장 큰 수 이다.
    예를 들어 8은
    8을 표현하는 방법에는 3 + 3 + 2로 표현할 수 있고, 3 * 3 * 2가 가장 가장 큰 값이며 가장 좋은 수이다.
    숫자 n이 주어질 때 가장 좋은 수를 구하여라.
    */
    static long[] dp;
    public static long solution3(int n) {
        int ret = 0;
        // 최대 길이 100이어서 넉넉히 크개 할당하여 선언.
        dp = new long[110];
        
        if (n < 4) {
            return n - 1;
        } else {
            // bottom up
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
                System.out.println("i:"+i+", dp[i]:"+dp[i]);
            }
        }

        long answer = dp[n];
        //long answer = recur(n);
        System.out.println("answer: "+answer);
        return answer;
    }

    /*
    <sol.SQL> - this is fail code
    아마 COUNT를 하는 곳에서 틀린 것 같다.

    SELECT C1.NAME NAME_X, C2.NAME NAME_Y, COUNT(*) as "장바구니 수"
    FROM CART_PRODUCTS C1 JOIN CART_PRODUCTS C2
    ON C1.CART_ID = C2.CART_ID
    WHERE C1.NAME <> C2.NAME
    GROUP BY C1.NAME, C2.NAME
    ORDER BY C1.NAME
    */
    
}
