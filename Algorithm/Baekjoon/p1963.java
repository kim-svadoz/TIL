import java.io.*;
import java.util.*;

public class p1963 {
    static BufferedReader br;
    static StringTokenizer st;
    static int T;
    static List<Boolean> prime = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        prime.add(false);
        prime.add(false);

        // 에라토스테네스의 체로 미리 소수 세팅
        for (int i = 2; i < 10000; i++) {
            prime.add(true);
        }
        for (int i = 2; i * i < 10000; i++) {
            if (prime.get(i)) {
                for (int j = i * i; j < 10000; j+=i) {
                    prime.set(j, false);
                }
            }
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bfs(a, b);
        }
    }

    static void bfs(int a, int b) {
        Queue<Integer> q = new LinkedList<>();
        int[] d = new int[10000];
        boolean[] check = new boolean[10000];
        q.add(a);
        check[a] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int k = change(cur, i, j);
                    if (prime.get(k) && !check[k]) {
                        d[k] = d[cur] + 1;
                        check[k] = true;
                        q.add(k);
                    }
                }
            }
        }
        
        System.out.println(d[b]);
    }

    // StringBuilder의 setCharAt(인덱스, 바꿀 문자)를 사용하면 수를 바꿔넣을 수 있다.
    static int change(int num, int index, int v) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(index, (char)(v + '0'));
        return Integer.parseInt(sb.toString());
    }

}
