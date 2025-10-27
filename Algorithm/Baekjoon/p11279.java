import java.util.*;
import java.io.*;

public class p11279 {
    static class PQ implements Comparable<PQ>{
        int n;
        public PQ(int n) {
            this.n = n;
        }
        public int compareTo(PQ o) {
            return o.n - n;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<PQ> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int cal = Integer.parseInt(br.readLine());
            if (cal == 0) {
                if (pq.size() != 0) {
                    sb.append(pq.poll().n).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                pq.offer(new PQ(cal));
            }
        }
        System.out.println(sb.toString());
    }
}