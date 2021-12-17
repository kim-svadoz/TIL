/**
 * BOJ 1455 뒤집기II Silver2
 * Greedy
 * 
 * 뭔가 함정이 있을 줄 알았지만 그냥 단순히 1일 때만 뒤집어 주면 바로 Greedy이다.
 */
import java.io.*;
import java.util.*;

public class p1455 {
    static int n, m;
    static int[][] coins;
    public static void main(String[] args) throws IOException {
        input();
        process();
    }
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        coins = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                coins[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
    }
    
    static void process() {
        int cnt = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (coins[i][j] == 1) {
                    //System.out.println("before===== i:"+i+", j:"+j);
                    //print();
                    cnt += toggle(i, j);
                    //System.out.println("after=====");
                    //print();
                }
            }
        }
        System.out.println(cnt);
    }

    static int toggle(int r, int c) {
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                coins[i][j] = coins[i][j] == 0 ? 1 : 0;
            }
        }
        return 1;
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(coins[i][j]);
            }
            System.out.println();
        }
    }
}
