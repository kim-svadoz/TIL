import java.io.*;

public class p10872 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int ans = recur(n);
        System.out.println(ans);
    }

    public static int recur(int N) {
        if(N <= 1) return 1;
        return N * recur(N - 1);
    }
}
