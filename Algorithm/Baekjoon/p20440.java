import java.io.*;
import java.util.*;

public class p20440 {
    static class Time {
        int s, e;
        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    static int n;
    static Time[] time;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        time = new Time[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            time[i] = new Time(start, end);
        }
        Arrays.sort(time, new Comparator<Time>(){
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.s == o2.e) {
                    return o1.e - o2.e;
                }
                return o1.s - o2.s;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(time[0].e);
        int cnt = 1;
        int s = time[0].s;
        int e = time[0].e;
        
        for (int i = 1; i < n; i++) {
            while (!pq.isEmpty() && pq.peek() < time[i].s) {
                pq.poll();
            }
            if (!pq.isEmpty() && pq.peek() == time[i].s) {
                if (pq.peek() == e) {
                    e = time[i].e;
                }
                pq.poll();
            }

            pq.add(time[i].e);
            if (pq.size() > cnt) {
                cnt = pq.size();
                s = time[i].s;
                e = pq.peek();
            }
        }
        System.out.println(cnt);
        System.out.println(s + " " + e);

    }
}
