import java.io.*;

public class p1373 {
    static String n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();
        
        int len = n.length();
        if (len % 3 == 1) {
            System.out.print(n.charAt(0));
        } else if (len % 3 == 2) {
        	System.out.print((n.charAt(0) - '0') * 2 + (n.charAt(1) - '0'));
    	}
        for (int i = len % 3; i < len; i += 3) {
            System.out.print((n.charAt(i) - '0') * 4 + (n.charAt(i + 1) - '0') * 2 + (n.charAt(i + 2) - '0'));
        }
    }
}