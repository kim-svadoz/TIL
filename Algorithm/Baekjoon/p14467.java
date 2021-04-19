/*
    어렵지 않은 구현문제이다
*/
import java.util.*;
import java.io.*;

public class p14467 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int place = Integer.parseInt(st.nextToken());

            if (!map.containsKey(num)) {
                map.put(num, place);
            } else {
                int val = map.get(num);
                if (val != place) {
                    cnt++;
                    map.put(num, place);
                }
            }
        }
        System.out.println(cnt);
    }
}