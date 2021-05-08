import java.io.*;
import java.util.*;

public class KAKAO2021H_INTERN05 {
    public static void main(String[] args) throws IOException {
        
    }
    
    static class Edge implements Comparable<Edge>{
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }
    static int n, m;
    static int[] parent, rank;
    static public int solution(int k, int[] num, int[][] links) {
        n = k;



        int answer = 0;
        return answer;
    }

    static void mst() {
        int ret = 0;
        int max = 0;
        // 가장 비용이 적은 순부터 탐색하기 때문에 가장 마지막에 있는 값을 제거하면 답이 된다.
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int u = cur.u;
            int v = cur.v;
            int cost = cur.cost;
            if (find(u) == find(v)) continue;
            
            union(u, v);
            ret += cost;
            max = cur.cost;
        }
        System.out.println(ret - max);
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        
        return find(parent[x]);
    }
    
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
                
                if (rank[x] == rank[y]) {
                    rank[x]++;
                }
            }
        }
    }
}
