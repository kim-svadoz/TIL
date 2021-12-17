/**
 * BOJ 21918 전구 Bronze2
 * 구현
 */
import java.io.*;
import java.util.*;

public class p21918 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[] bulbArr;
    public static void main(String[] args) throws IOException {

        input();
        command();
        printBulb();
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bulbArr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            bulbArr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void command() throws IOException {
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            process(command, b, c);
        }
    }

    static void process(int command, int b, int c) {
        switch(command) {
            case 1:
                bulbArr[b - 1] = c;
                break;
            case 2:
                for (int i = b; i <= c; i++) {
                    bulbArr[i-1] = bulbArr[i-1] == 0 ? 1 : 0;
                }
                break;
            case 3:
                for (int i = b; i <= c; i++) {
                    bulbArr[i-1] = 0;
                }
                break;
            case 4:
                for (int i = b; i <= c; i++) {
                    bulbArr[i-1] = 1;
                }
                break;
        }
    }

    static void printBulb() {
        StringBuilder sb = new StringBuilder();
        for(int i : bulbArr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}