import java.io.*;
import java.util.*;

public class p11365 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb;
        while (true) {
            String line = br.readLine();
            if (line.equals("END")) break;

            sb = new StringBuffer(line);
            String reverse = sb.reverse().toString();

            System.out.println(reverse);
        }
    }
}