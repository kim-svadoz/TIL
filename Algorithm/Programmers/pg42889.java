import java.util.*;
class Solution {
    class Failure implements Comparable<Failure>{
        int num;
        double ratio;
        public Failure(int num, double ratio) {
            this.num = num;
            this.ratio = ratio;
        }
        
        public int compareTo(Failure o) {
            if (ratio > o.ratio) {
                return -1;
            } else if (ratio < o.ratio) {
                return 1;
            } 
            return num - o.num;
        }
    } 
    
    Map<Integer, Integer> map = new TreeMap<>();
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        for (int j = 1; j <= N + 1; j++) {
            map.put(j, 0);
        }
        for (int i : stages) {
            map.put(i, map.get(i) + 1);
        }
        int total = stages.length;
        PriorityQueue<Failure> pq = new PriorityQueue<>();
        // 1번 스테이지 부터 차례대로 1번 스테이지는 총 total 명수가 있다.
        // 2번 스테이지는 total - 이전 스테이지에 남아있는 사람 ...
        // 3 ...
        for (Object k : map.keySet().toArray()) {
            int key = (int)k;
            int val = map.get(key);
            if (key > N) {
                val = 0;
            }
            double ratio = (double)val / (double)total;
            System.out.println("key:"+key+", val:"+val+", total:"+total+", ratio:"+ratio);
            total -= val;
            if (key > N) continue;
            pq.offer(new Failure(key, ratio));
        }
        
        int idx = 0;
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Failure cur = pq.poll();
            //System.out.println("cur.num:"+cur.num+", cur.ratio:"+cur.ratio);
            
            answer[i] = cur.num;
        }
        for (int i = size; i < N; i++) {
            answer[i] = i;
        }
        
        return answer;
    }
}