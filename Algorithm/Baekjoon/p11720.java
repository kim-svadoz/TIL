import java.io.*;
import java.util.*;

public class p11720 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (line.charAt(i)-'0');
        }
        System.out.println(sum);
    }
}