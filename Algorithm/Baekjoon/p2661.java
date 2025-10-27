/*
    좋은 수열인지, 나쁜 수열인지 분별해야 하는 코드를 구현해야 한다.
    길이가 n인 수열에서 인접하면서 동일한 수열이 있는 경우는 동일한 수열의 길이가 최소 1부터 최대 n/2인 경우 발생한다.
    따라서 가장 마지막에 집어넣은 수 기준으로
    마지막 1개와 그 앞의 1개의 수가 동일한지,
    마지막 2개와 그 앞의 2개의 수가 동일한지,
    마지막 3개와 그 앞의 3개의 수가 동일한지,
    ...
    마지막 n/2개와 그 앞의 n/2개의 수가 동일한지 비교를 해서 
    한번이라도 동일하다면 그 수열은 나쁜 수열이다.

    가장 첫 번째로 나오는 백트래킹 알고리즘의 결과가 결과중 가장 작은 수 이기 때문에
    첫번째를 바로 출력하면 된다.
*/
import java.io.*;
import java.util.*;

public class p2661 {
    static final int START = 1;
    static final int END = 3;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        bt("");
    }

    static void bt(String str) {
        if (str.length() == n) {
            System.out.println(str);
            System.exit(0);
            return;
        }

        for (int i = START; i <= END; i++) {
            if (promising(str + i)) {
                bt(str + i);
            }
        }
    }
    static boolean promising(String str) {
        int len = str.length();
        // ex. 1212가 들어올 경우
        // 한글자씩 비교했을 때는 유효하지만
        // 두글자씩 비교 했을 경우에는 12 12가 같으므로 유효하지 않다.
        for (int i = 1; i <= len / 2; i++) {
            String front_str = str.substring(str.length()-i-i, str.length()-i);
            String rear_str = str.substring(str.length()-i, str.length());
            if (front_str.equals(rear_str)) return false;
        }
        return true;
    }
}
