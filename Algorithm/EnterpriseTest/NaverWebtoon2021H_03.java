/*
    2021-07-04
    네이버 웹툰 개발 챌린지 1차 코딩 테스트(Back-End) 
*/
import java.io.*;
import java.util.*;

public class NaverWebtoon2021H_03 {
    public static void main(String[] arsg) throws IOException {
        String s = "aaaaabbbbb";
        String t = "ab";
        solution(s, t);
    }

    static int cnt, result;
    static List<Integer> li;
    static StringBuilder sb;
    public static int solution(String s, String t) {
        sb = new StringBuilder(s);
        result = 0;
        go(sb.toString(), t);

        System.out.println(result);
        return result;
    }
    static void go(String org, String ptn) {
        li = new ArrayList<>();
        cnt = 0;
        KMP(org, ptn);
        if (cnt != 0) {
            result++;
            int start = li.get(0);
            int end = start + ptn.length();
            go(sb.delete(start, end).toString(), ptn);
        }
    }

    
    static int[] failFuncForGetPi(String ptn) {
        int[] pi = new int[ptn.length()];
        int j = 0;
        for (int i = 1; i< ptn.length(); i++){
            while (j > 0 && ptn.charAt(i) != ptn.charAt(j)){
                j = pi[j - 1];
            }
            if (ptn.charAt(i) == ptn.charAt(j))
                pi[i] = ++j;
        }
        return pi;
    }
    
    static void KMP(String org, String ptn) {
        int pi[] = failFuncForGetPi(ptn);
        int j = 0;
        for (int i = 0; i < org.length(); i++) {
            while (j > 0 && org.charAt(i) != ptn.charAt(j)) {
                j = pi[j - 1];
            }
            if (org.charAt(i) == ptn.charAt(j)) {
                if (j == ptn.length() - 1) {
                    ++cnt;
                    li.add(i - j);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
    }
    
}
