/*
    LCS2
    DP + LCS(수열 구하기) 알고리즘
*/
import java.io.*;
import java.util.*;
public class p9252 {
    static char[] str1, str2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[len1][len2]);

        // 메모이제이션 된 배열을 가지고 다시 끝에서부터 탐색한다.
        Stack<Character> stack = new Stack<>();
        while (len1 >= 1 && len2 >= 1) {
            // 위와 같은 경우 위로 이동
            if (dp[len1][len2] == dp[len1 - 1][len2]) {
                len1--;
            } else if (dp[len1][len2] == dp[len1][len2 - 1]) { // 왼쪽과 같은 경우 왼쪽으로 이동
                len2--;
            } else {
                // 대각선으로 같다면
                stack.push(str1[len1 - 1]);
                len1--;
                len2--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
}