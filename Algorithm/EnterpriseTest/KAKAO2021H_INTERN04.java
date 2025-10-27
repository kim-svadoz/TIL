import java.io.*;
import java.util.*;

public class KAKAO2021H_INTERN04 {
    public static void main(String[] args) throws IOException {
        int n = 4;
        int start = 1;
        int end = 4;
        int[][] roads = {
            {1, 2, 1},
            {3, 2, 1},
            {2, 4, 1}
        };
        int[] traps = {2, 3};
        solution(n, start, end, roads, traps);
    }

    static class Node implements Comparable<Node>{
        int start, to, cost;
        boolean flag;
        public Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public Node (int to, int cost, boolean flag) {
            this.to = to;
            this.cost = cost;
            this.flag = flag;
        }
        
        public int compareTo(Node o) {
            if (cost == o.cost) {
                return o.to - to;
            }
            return cost - o.cost;
        }

        void trigger() {
            flag = !flag;
        }
    }
    static boolean[][] visited;
    static Set<Integer> trap;
    static List<Node>[] list;
    static PriorityQueue<Node> pq;
    static int n, answer = Integer.MAX_VALUE;
    static public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        n = n;
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        trap = new HashSet<>();
        for (int x : traps) {
            trap.add(x);
        }
        for (int[] road : roads) {
            list[road[0]].add(new Node(road[1], road[2], true));
            if (trap.contains(road[1])) list[road[1]].add(new Node(road[0], road[2], false));
        }
        
        go(start, end);
        System.out.println(answer);
        return answer;
    }

    static void go(int start, int end) {
        pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, true));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.to; // 2
            int total = curNode.cost; // 1
            if (trap.contains(cur)) {
                onTrap(start);
            } 

            if (cur == end) {
                answer = Math.min(answer, total);
                return;
            }

            for (Node adj : list[cur]) {
                int next = adj.to; // 1, 4, 3
                int cost = adj.cost; // 1, 1, 1
                boolean flag = adj.flag; // t, t, f
                if (flag == false) continue;

                pq.add(new Node(next, total + cost)); // 4, 4
            }
        }
    }
    static void onTrap(int k) {
        for (Node node : list[k]) {
            node.trigger();
        }
    }
}
