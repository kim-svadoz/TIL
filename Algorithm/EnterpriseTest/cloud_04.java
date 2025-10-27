import java.io.*;
import java.util.*;

public class cloud_04 {
    static class Time {
        int s, e;
        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    static int n;
    static Time[] time;
    public static void main(String[] args) {
        int[][] info = {
            {1, 2},
            {2, 3},
            {4, 5},
            {5, 6}
        };
        solution(info);
    }

    static int[] total;
    static void solution(int[][] info) {
        n = info.length;
        time = new Time[n];
        total = new int[86400];

        for (int i = 0; i < info.length; i++) {
            int[] t = info[i];
            int start = t[0];
            int end = t[1];
            time[i] = new Time(start, end + 1);

            for (int j = start; j <= end; j++) {
                total[j] += 1;
            }
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
        for (int i = 0; i < 86400; i++) {
            if (total[i] == cnt) {
                System.out.print(i+" ");
            }
        }
    }
}
