/**
 * BOJ 1484 다이어트
 * 수학
 */
import java.io.*;
import java.util.*;

public class p1484 {
    static int g;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        // g: 15
        // 15킬로그램이나 쪘어!!!
        // 현재몸무게 cur^2 - 기억^2 = 15
        // (a + b)(a - b) = 15
        // 4 + 1 * 4 - 1
        // 8 + 7 * 8 - 7
        for (int i = 1; i <= g; i++) {
            // 현재 몸무게 테스트
            if (g % i != 0) continue;

            // 더해서 i가 만들어지는 경우를 본다
            for (int j = 1; j <= i / 2; j++) {
                int a = j;
                int b = i - j;

                int big = Math.max(a, b);
                int small = Math.min(a, b);
                if ((big + small) * (big - small) == g) {
                    list.add(big);
                }
            }
        }
        if (list.size() == 0) {
            System.out.println(-1);
        } else {
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
