/*
    오리는 꽥꽥
    quack 문자를 미리 만들어 input 값에서 q를 방문했으면 index를 증가시키고 다음 문자를 비교하도록 한다.
*/

import java.util.*;
import java.io.*;
public class p12933 {
    static String quack = "quack";
    static int len = quack.length();
    static int cnt = 0;
    static boolean[] visit;
    static String duck = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        duck = br.readLine();
        visit = new boolean[duck.length()];
        
        if (duck.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }
        
        for (int i = 0; i < duck.length(); i++) {
            if (duck.charAt(i) == 'q' && !visit[i]) {
                go(i);
            }
        }
        
        for (int i = 0; i < duck.length(); i++) {
            if (visit[i] == false) {
                System.out.println(-1);
                return;
            }
        }
        
        if (cnt == 0) {
            System.out.println(-1);
            return;
        }
        
        System.out.println(cnt);
      
    }
    static void go(int start) {
        int j = 0;
        boolean first = true;
        for (int i = start; i < duck.length(); i++) {
            if (duck.charAt(i) == quack.charAt(j) && !visit[i]) {
                visit[i] = true;
                if (duck.charAt(i) == 'k') {
                    if (first) {
                        cnt++;
                        first = false;
                    }
                    j = 0;
                    continue;
                }
                j ++;
            }
        }
    }
}