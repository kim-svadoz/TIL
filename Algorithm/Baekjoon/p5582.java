/*
    공통 부분 문자열
    DP
    LCS를 이용한 DP를 수행하면 된다.
*/
import java.io.*;
import java.util.*;
public class p5582 {
    static char[] str1, str2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        
        int len1 = str1.length;
        int len2 = str2.length;
        
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 두 문자가 서로 같다면 이전에 거에서 길이를 하나 추가하고
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // 최대 길이를 갱신한다.
                    max = Math.max(max, dp[i][j]);
                }
                // 같지 않다면 다시 길이는 1부터 초기화 되어야 한다.
            }
        }
        System.out.println(max);
    }
}