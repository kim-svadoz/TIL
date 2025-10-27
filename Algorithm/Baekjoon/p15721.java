import java.io.*;
import java.util.*;

public class p15721 {
    static int a, t, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(br.readLine()); // 총 인원수
        t = Integer.parseInt(br.readLine()); // 구하고자 하는 번째
        k = Integer.parseInt(br.readLine()); // 뻔(0) or 데기(1)
        
        int answer = solution();
        System.out.println(answer);
    }

    static int solution() {
        int p = 0, q = 0, cnt = 2;
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    p++;
                } else {
                    q++;
                }
                if (p == t && k == 0) {
                    return (p + q - 1) % a;
                }
                if (q == t && k == 1) {
                    return (p + q - 1) % a;
                }
            }

            for (int i = 0; i < cnt; i++) {
                p++;
                if (p == t && k == 0) {
                    return (p + q - 1) % a;
                }
            }
            for (int i = 0; i < cnt; i++) {
                q++;
                if (q == t && k == 1) {
                    return (p + q - 1) % a;
                }
            }
            cnt++;
        }
    }
}
