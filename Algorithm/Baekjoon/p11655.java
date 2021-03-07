import java.io.*;

public class p11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int len = s.length();
        char[] res = new char[len];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            char ROT13_c = c;
            
            if ((c >= 'A' && c <='M') || (c >= 'a' && c <='m')) {
                ROT13_c = (char) ((c - 0) + 13);    
            } else if ((c >= 'N' && c <='Z') || (c >= 'n' && c <='z')) {
                ROT13_c = (char) ((c - 0) - 13);
            }

            res[i] = ROT13_c;
        }
        for (char c : res) {
            System.out.print(c);
        }
    }
}
