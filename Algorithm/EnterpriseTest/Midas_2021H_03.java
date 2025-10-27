import java.io.*;
import java.util.*;

public class Midas_2021H_03 {
    public static void main(String[] args) {
        int n = 4;
        int k = 4;
        int[][] T = {
            {1, 3},
            {1, 1},
            {2, 3},
            {3, 4},
        };
        solution(n, k, T);
    }
    static class Date implements Comparable<Date> {
        int s, e;
        public Date(int s, int e) {
            this.s = s;
            this.e = e;
        }
        public int compareTo(Date o) {
            if (e == o.e) {
                return s - o.s;
            }
            return e - o.e;
        }
    }
    static public int solution(int N, int K, int[][] T) {
        boolean[] finished = new boolean[K + 1];
        PriorityQueue<Date> pq = new PriorityQueue<>();
        int answer = 0;

        for (int i = 0; i < T.length; i++) {
            pq.add(new Date(T[i][0], T[i][1]));
        }
        
        int curDate = 1;
        while (curDate <= K) {
            while (!pq.isEmpty()) {
                Date cur = pq.poll();
                int s = cur.s;
                int e = cur.e;
                if (curDate >= s && curDate <= e) {
                    if (!finished[curDate]) {
                        answer++;
                        finished[curDate] = true;    
                    }
                    break;
                }
            }

            curDate++;
        }

        System.out.println(answer);
        return answer;
    }
}
