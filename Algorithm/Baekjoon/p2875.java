import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2875 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, K, arr[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // female
        M = Integer.parseInt(st.nextToken()); // male
        K = Integer.parseInt(st.nextToken()); // intern


        int team = 0;
        while (N >= 2 && M >= 1 && N + M >= K + 3) {
            N -= 2;
            M -- ;
            team ++;
        }
        System.out.println(team);
    }
}
