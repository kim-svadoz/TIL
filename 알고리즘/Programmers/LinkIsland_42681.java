package programmers;

import java.util.PriorityQueue;

public class LinkIsland_42681 {

	public static void main(String[] args) {

	}
	
	class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }

    static int[] parent;
    static PriorityQueue<Edge> adj;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        adj = new PriorityQueue<>();

        for(int i = 0 ; i < costs.length ; ++i){
            Edge edge = new Edge(costs[i][0], costs[i][1], costs[i][2]);
            adj.offer(edge);
        }

        for(int i = 0 ; i < n ; ++i) parent[i] = i;

        while(!adj.isEmpty()) {
            Edge edge = adj.poll();

            if(find(edge.from) == find(edge.to)) continue;
            else {
                union(edge.from, edge.to);
                answer += edge.cost;    
            }
        }

        return answer;
    }

    public int find(int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

       if(rootA != rootB) parent[rootB] = rootA;
    }

}
