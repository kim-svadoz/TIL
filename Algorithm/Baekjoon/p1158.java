import java.io.*;
import java.util.*;
public class p1158 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
                
        StringBuilder sb = new StringBuilder("<");
        while (!q.isEmpty()) {
            for (int j = 1; j <= k; j++) {
                if (j != k) {
                    q.offer(q.poll());
                } else {
                    sb.append(q.poll());
                    sb.append(", ");
                }
            }
        }
        String s = sb.substring(0, sb.length() - 2);
        sb = new StringBuilder(s);
        sb.append(">");
        System.out.print(sb);
        br.close();
    }
}