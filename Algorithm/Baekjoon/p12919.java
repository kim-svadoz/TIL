/*
    시간초과의 늪

    문제는 첫번째 문자열 -> 두번째 문자열 바꿀수 있는가를 확인하라고 되어있지만

    코드는 "두번째 문자열 -> 첫번째 문자열로 원복할 수 있는가"를 구현하였다.

    1) 맨 뒤에 A를 붙인다

    2) 맨 뒤에 B를 붙이고 뒤집기 => 뒤집고 앞에 B 붙이기

    왜냐면 규칙을 원복했을 때 나올수 없는 결과 찾기가 더 간편하기 때문이다.
*/
import java.io.*;
public class p12919 {
    static String S, T;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        System.out.println(dfs(S, T));
    }
    
    static int dfs(String s, String t) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                return 1;
            }
            return 0;
        }
        
        // 두 규칙 중 하나는 성립해야 가능하다 -> 이말은 즉, A--------B 는 존재할 수 없다.

        int ans = 0;
        // 1번 규칙, 마지막 글자가 A
        if (t.charAt(t.length() - 1) == 'A') {
            ans += dfs(s, t.substring(0, t.length() - 1));
        }

        // 2번 규칙, 첫번째 글자가 B
        if (t.charAt(0) == 'B') {
            String str = new StringBuffer(t.substring(1, t.length())).reverse().toString();
            ans += dfs(s, str);
        }
        
        return ans > 0 ? 1 : 0;
    }
}