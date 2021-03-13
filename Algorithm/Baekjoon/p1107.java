import java.io.*;
import java.util.*;

public class p1107 {
    public static int N, M;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            while(M-- > 0) list.add(Integer.parseInt(st.nextToken()));
        }
        
        int min = Math.abs(N - 100);
        for (int i = 0; i <= 1000000; i++) {
            int length = solution(i);
            if (length > 0) {
                min = Math.min(min, Math.abs(N - i) + length);
            }
        }
        
        System.out.print(min);
    }

    public static int solution(int num) {
        int length = 0;
        if (num == 0) return list.contains(num) ? 0 : 1;

        while (num > 0) {
            if (list.contains(num % 10)) return 0;

            length++;
            num /= 10;
        }

        return length;
    }
}
