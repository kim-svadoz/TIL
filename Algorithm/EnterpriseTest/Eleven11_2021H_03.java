/**
 * N의 범위는 int형까지.
 * INF를 넘어가면 -1을 출력
 */
import java.math.BigInteger;
import java.util.*;

public class Eleven11_2021H_03 {
    public static void main(String[] args) {
        int N = 23456789;
        System.out.println(solution(N));
    }

    static final int INF = 100000000;
    static List<Integer> list;
    public static int solution(int N) {
        list = new ArrayList<>();
        String s = N+"";
        char[] arr = s.toCharArray();
        for (char c : arr) {
            list.add(c - '0');
        }
        Collections.sort(list, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i);
        }
        BigInteger big = new BigInteger(sb.toString());
        int answer = big.intValue() > INF ? -1 : big.intValue();
        
        return answer;
    }
}
