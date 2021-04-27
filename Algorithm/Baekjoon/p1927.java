import java.util.*;
import java.io.*;

public class p1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int cal = Integer.parseInt(br.readLine());
            if (cal == 0) {
                if (pq.size() != 0) {
                    sb.append(pq.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                pq.offer(cal);
            }
        }
        System.out.println(sb.toString());
    }
}