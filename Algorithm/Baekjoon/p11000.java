/*
    강의실 배정
    Greedy + PriorityQueue

    처음엔 누적합으로 시도했지만 시간의 길이가 총 10^9으로 메모리 초과가 발생해서
    well-known인 PQ를 활용하여 풀었다.

    시작시간을 먼저 정렬하고 그 후에 종료시간으로 배치해야 가장 짧은 텀의 시간표을 가질 수 있다.
*/
import java.io.*;
import java.util.*;
public class p11000 {
    static class Lesson implements Comparable<Lesson> {
        int s, t;
        public Lesson (int s, int t) {
            this.s = s;
            this.t = t;
        }
        public int compareTo(Lesson o) {
            if (s == o.s) {
                return t - o.t;
            }
            return s - o.s;
        }
    }
    static int n;
    static PriorityQueue<Lesson> pq = new PriorityQueue<>();
    static PriorityQueue<Integer> ipq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.add(new Lesson(s, t));
        }
        while (!pq.isEmpty()) {
            Lesson l = pq.poll();
            int s = l.s;
            int t = l.t;
            // 이전 강의의 끝나는 시간이 현재 강의 시작시간보다 빠르다면 hit
            if (!ipq.isEmpty() && ipq.peek() <= s) {
                ipq.poll();
            }
            ipq.offer(t);
        }
        
        System.out.println(ipq.size());
    }
}