import java.io.*;
import java.util.*;

/*
MERGE - SORT
2 2
3 5
2 9
*/
public class p11728 {
    static int n, m, a[], b[], ms[];
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[m];
        ms = new int[n + m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b);

        for (int i = 0; i < n; ++i) {
            ms[i] = a[i];
        }
        for (int i = n; i < n + m; ++i) {
            ms[i] = b[i - n];
        }
        Arrays.sort(ms);
        for (int x : ms) {
            bw.write(x+" ");
        }

        bw.flush();
        bw.close();

    }
}
