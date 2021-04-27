import java.util.*;
import java.io.*;

public class p1202 {
    static class Pair implements Comparable<Pair>{
        int w, c;
        public Pair (int w, int c) {
            this.w = w;
            this.c = c;
        }
        
        public int compareTo(Pair o) {
            return w - o.w;
        }
    }
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        PriorityQueue<Pair> jew = new PriorityQueue<>();
        PriorityQueue<Integer> bag = new PriorityQueue<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            jew.offer(new Pair(w, c));
        }
        
        while (k-- > 0) {
            bag.offer(Integer.parseInt(br.readLine()));
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long ans = 0;
        while (!bag.isEmpty()) {
            int bagSize = bag.poll();
            while (!jew.isEmpty()) {
                Pair cur = jew.poll();
                // 꺼낸 보석의 무게가 가방의 무게를 초과한다면 보석을 다시 집어 넣고 다음 보석을 탐색한다.
                if (cur.w > bagSize) {
                    jew.offer(cur);
                    break;
                } else {
                    // 나중에 오름차순으로 빼기 위해서 -1을 곱하고 넣어준다.
                    pq.offer(cur.c * -1);
                }
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        
        System.out.println(ans * -1);
    }
}