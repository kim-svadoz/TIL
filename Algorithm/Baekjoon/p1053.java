/**
 * BOJ 1053 팰린드롬 공장
 * DP
 */
import java.io.*;
import java.util.*;

/**
 * dp[l][r] : l에서 r까지 팰린드롬으로 만드는데 드는 최소비용
 */
public class p1053 {
    static String input;
    public static void main(String[] args) throws IOException {
        init();
        pro();
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }
    static void pro() {
        char[] pelindrome = input.toCharArray();
        int ans = solution(pelindrome); // 원래 문자열에서 팰린드롬을 만드는 법
        
        char[] tmp = new char[pelindrome.length];
        for (int i = 0; i < pelindrome.length - 1; i++) {
            for (int j = i + 1; j < pelindrome.length; j++) {
                if (pelindrome[i] == pelindrome[j]) continue;

                tmp = pelindrome;
                swap(i, j, tmp);
                ans = Math.min(ans, solution(tmp) + 1);
                swap(i, j, tmp);
            }
        }
        System.out.println(ans);
    }

    static int solution(char[] line) {
        int size = line.length;
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 0;
            if (i != size - 1) {
                dp[i][i + 1] = line[i] == line[i + 1] ? 0 : 1;
            }
        }

        for (int i = 2; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                dp[j][j + i] = Math.min(dp[j + 1][j + i] + 1, dp[j][j + i - 1] + 1);
                if (line[j + i] == line[j]) {
                    dp[j][j + i] = Math.min(dp[j + 1][j + i - 1], dp[j][j + i]);
                } else {
                    dp[j][j + i] = Math.min(dp[j + 1][j + i - 1] + 1, dp[j][j + i]);
                }
            }
        }
        return dp[0][size - 1];
    }

    static void swap(int a, int b, char[] line) {
        char temp = line[a];
        line[a] = line[b];
        line[b] = temp;
    }
}