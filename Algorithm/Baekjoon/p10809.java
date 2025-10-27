import java.io.*;
import java.util.*;

public class p10809 {
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        int[] alpha = new int[26];

        Arrays.fill(alpha, -1);
        for(int i = 0; i < s.length(); i++) {
            if(alpha[s.charAt(i) - 'a'] == -1) {
                alpha[s.charAt(i) - 'a'] = i;
            }
        }

        for(int i = 0; i < alpha.length; i++) {
            System.out.print(alpha[i]+" ");
        }
    }
}
