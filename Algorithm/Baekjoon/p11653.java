import java.io.*;

public class p11653 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int mod = 2;
        while (true) {
            if (n == 1) break;

            if (n % mod == 0) {
                n /= mod;
                System.out.println(mod);
                mod = 2;
            } else {
                mod++;
            }
        }
    }
}
