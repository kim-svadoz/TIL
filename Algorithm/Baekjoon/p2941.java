/*
    처음에 틀린 부분 -->  dz= 일때 idx++ 을 해주면 안되고 idx = idx + 1로 해줘야한다.
    딥카피?!
    두번째 틀린 부분 --> 알파벳의 개수만 세어야하기때문에 else에서 알파벳을 체크해줘야한다.

*/
import java.io.*;

public class p2941 {
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        int ans = 0;
        int maxIdx = input.length();
        for (int i = 0; i < maxIdx; i++) {
            char c = input.charAt(i);
            if (c == 'c') {
                int idx = i + 1;
                if (idx < maxIdx) {
                    if (input.charAt(idx) == '=' || input.charAt(idx) == '-') {
                        i = idx;
                    }
                }
                ans++;
            } else if (c == 'd') {
                int idx = i + 1;
                if (idx < maxIdx) {
                    if (input.charAt(idx) == 'z') {
                        idx = idx + 1;
                        if (idx < maxIdx) {
                            if (input.charAt(idx) == '=') {
                                i = idx;
                            }
                        }
                    } else if (input.charAt(idx) == '-') {
                        i = idx;
                    }
                }
                ans++;
            } else if (c == 'l' || c == 'n') {
                int idx = i + 1;
                if (idx < maxIdx) {
                    if (input.charAt(idx) == 'j') {
                        i = idx;
                    }
                }
                ans++;
            } else if (c == 's' || c == 'z') {
                int idx = i + 1;
                if (idx < maxIdx) { 
                    if (input.charAt(idx) == '=') {
                        i = idx;
                    }
                }
                ans++;
            } else if (Character.isAlphabetic(c)) {
                ans++;
            }
            
        }
        System.out.println(ans);
    }
}
