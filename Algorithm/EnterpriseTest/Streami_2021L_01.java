import java.util.*;

public class Streami_2021L_01 {
    public static void main(String[] args)  {
        String S = "azABaabza";
        solution(S);
    }

    public static int solution(String S) {
        int answer = -1;
        int len = S.length();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j ++) {
                String str = S.substring(i, j + 1);

                if (balanced(str) && str.length() > answer) {
                    
                    answer = str.length();
                }
            }
        }

        System.out.println(answer);
        return answer;
    }

    private static boolean balanced(String s) {
        int[] upper = new int[26];
        int[] lower = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ascii = (int) c;

            if (ascii > 96) { // 소문자
                int idx = c - 'a';
                if (lower[idx] == 0) {
                    lower[idx]++;
                }
            } else { // 대문자
                int idx = c - 'A';
                if (upper[idx] == 0) {
                    upper[idx]++;
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (upper[i] > 0 && lower[i] == 0) {
                return false;
            } else if (upper[i] == 0 && lower[i] > 0) {
                return false;
            }
        }

        return true;
    }
}
