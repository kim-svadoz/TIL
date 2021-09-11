import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_02 {
    public static void main(String[] args) throws IOException {
        int n = 5024;
        int k = 3;
        System.out.println(solution(n, k));
    }

    static boolean[] prime;
    static List<Integer> primeList;
    public static int solution(int n, int k) {
        prime = new boolean[44000];
        primeList = new ArrayList<>();
        // true : 소수가 아니다, false : 소수이다.
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i * i < 44000; i++) {
            if (!prime[i]) {
                for (int j = i * i; j < 44000; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 1; i < 44000; i++) {
            if (!prime[i]) {
                primeList.add(i); // 소수인 것들을 담았음.
            }
        }
        String s = convert2N(n, k);

        System.out.println("s::"+s);
        int answer = check(s);
        return answer;
    }

    public static int check(String s) {
        // 주어진 문자 s에서 0을 포함하지 않는 부분 소수 수열은 몇개?
        int ret = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                sb.append(s.charAt(i));
            } else {
                // 지금까지 이어온 숫자가 소수인지 아닌지 판단한다.
                //System.out.println("sb::"+sb.toString());
                int k = Integer.parseInt(sb.toString());
                if (sb.length() > 0 && primeList.contains(k)) {
                    ret++;
                }
                sb.setLength(0);
            }

            if (i == s.length() - 1) {
                int k = Integer.parseInt(sb.toString());
                if (sb.length() > 0 && primeList.contains(k)) {
                    ret++;
                }
                sb.setLength(0);
            }
        }
        return ret;
    }

    public static String convert2N(int number, int N){
        StringBuilder sb = new StringBuilder();
        int cur = number;

        while(cur > 0){
            if(cur % N < 10){
                sb.append(cur % N);
            } else {
                sb.append((char)(cur % N - 10 + 'A'));
            }
            cur /= N;
        }
        return sb.reverse().toString();
    }
}
