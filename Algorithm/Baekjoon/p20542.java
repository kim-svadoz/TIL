import java.io.*;
import java.util.*;

public class p20542 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m = 0;
    static long dist[][];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        dist = new long[n + 1][m + 1];

        String input = br.readLine();
        String answer = br.readLine();

        long cnt = levenshtein(input, answer);
        System.out.println(cnt);
    }

    static long levenshtein(String origin, String pattern) {
        for (int i = 1; i <= origin.length(); ++i) {
            dist[i][0] = i;
        }
        for (int j = 1; j <= pattern.length(); ++j) {
            dist[0][j] = j;
        }

        for (int j = 1; j <= pattern.length(); ++j) {
            for (int i = 1; i <= origin.length(); ++i) {
                if (isCorrect(origin.charAt(i - 1), pattern.charAt(j - 1))) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    dist[i][j] = Math.min(dist[i - 1][j - 1] + 1, Math.min(dist[i][j - 1] + 1, dist[i - 1][j] + 1));
                }
            }
        }
        return dist[origin.length()][pattern.length()];
    }

    static boolean isCorrect(char o, char p) {
        if (o == p) return true;
        else if (o == 'i' && (p == 'i' || p == 'j' || p == 'l')) return true;
        else if (o == 'v' && (p == 'v' || p == 'w')) return true;
        else return false;
    }

}
