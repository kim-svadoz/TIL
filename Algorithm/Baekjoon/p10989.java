import java.io.*;

public class p10989 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());

        int[] cnt = new int[10001];

        
        for (int i = 0; i < n; i++) {            
            cnt[Integer.parseInt(br.readLine())] ++;
        }
        br.close();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < 10001; i++) {
            while(cnt[i] > 0) {
                sb.append(i).append('\n');
                cnt[i]--;
            }
        }
        System.out.println(sb);
    }
}
