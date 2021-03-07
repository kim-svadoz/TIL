import java.io.*;
import java.util.*;

public class p11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] res = new String[s.length()];

        for (int i = 0; i < s.length(); i++) {
            res[i] = s.substring(i, s.length());
        }

        Arrays.sort(res);

        for(String str : res) {
            System.out.println(str);
        }
    }
}
