/*
    중량제한
    1. 최대 스패닝 트리
        -> 최소 스패닝 트리의 반대 버전으로, 가중치가 가장 큰 간선을 선택하면서 스패닝 트리를 만들ㅇ 너간다.
        -> 그러면 어떤 정점S  와 다른정점 E 가 같은 집합에 속하는 순간 우리가 원하는 경로가 완성된다.
        -> 이렇게 완성된 경로는 최장경로 처럼, 가능하면 무거운 짐을 옮길 수 있는 다리만 선택하여 만들어진 것
    2. 이분탐색 + BFS
*/
import java.io.*;
import java.util.*;
public class p1939 {
    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        public int compareTo(Edge o) {
            return o.cost - cost;
        }
    }
    static int n, m, s, e;
    static PriorityQueue<Edge> pq;
    static int[] parent, rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        parent = new int[n + 1];
        rank = new int [n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        
        System.out.println(mst());
    }
    
    static int mst() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.u;
            int v = edge.v;
            union(u, v);
            if (find(s) == find(e)) return edge.cost;
        }
        
        return -1;
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (rank[a] < rank[b]) {
                parent[a] = b;
            } else {
                parent[b] = a;
                if (rank[a] == rank[b]) {
                    rank[a]++;
                }
            }
        }
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        
        return find(parent[x]);
    }
}

/*
public class p1939 {
    static class Node {
        int to;
        long cost;
        public Node (int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static final int INF = 987654321;
    static int n, m, max = 0;
    static long[] dist;
    static boolean[] visited;
    static List<Node>[] list;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        list = new ArrayList[n + 1];
        q = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, cost));
            list[v].add(new Node(u, cost));
            max = Math.max(max, cost);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        long ret = binarySearch(s, e);

        System.out.println(ret);
    }

    static long binarySearch(int from, int to) {
        long left = 1, right = max;
        long mid = 0, ans = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (go (from, to, mid)) {
                ans = mid > ans ? mid : ans;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    static boolean go (int from, int to, long weight) {
        clear();

        q.offer(from);
        visited[from] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == to) {
                return true;
            }

            for (Node next : list[cur]) {
                if (visited[next.to]) continue;
                if (weight <= next.cost) {
                    q.offer(next.to);
                    visited[next.to] = true;
                }
            }
        }

        return false;
    }

    static void clear() {
        q.clear();

        Arrays.fill(visited, false);
    }
}
*/

/* 크루스칼 알고리즘으로 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, S, E;
	static PriorityQueue<Edge> pq;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(dijkstra());
	}
	
	static int dijkstra(){
		
		int pa,pb, max=Integer.MAX_VALUE;
		Edge te;
		while(!pq.isEmpty()) {
			te = pq.poll();
			union(te.s, te.e);
			max = Math.min(max, te.w);
			pa = getParent(S);
			pb = getParent(E);
			if(pa == pb)
				return max;
		}
		return -1;
	}
	
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initParents(N+1);
		
		pq = new PriorityQueue<>();
		int s,e,w;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(s,e,w));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	}
	
	static void initParents(int size) {
		parents = new int[size];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	
	static class Edge implements Comparable<Edge>{
		int s,e,w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return o.w - this.w;
		}
	}
}

*/