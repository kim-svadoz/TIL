import java.io.*;

public class p1676 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int cnt = 0;

        while (n >= 5) {
            cnt += n / 5;
            n /= 5;
        }
        System.out.println(cnt);
    }

}
