/*
    트리와 쿼리.
    tree + DP
*/
import java.io.*;
import java.util.*;

public class p15681 {
    static int N, R, Q;
    static List<Integer>[] list;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 트리의 간선 셋팅
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        // 루트의 번호 : R, 루트의 부모는 없으니 -1
        makeTree(R, -1);

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            int U = Integer.parseInt(br.readLine());
            // U를 root로 하는 서브트리에 속한 정점의 수는? -> U의 자식의 수는?
            sb.append(dp[U]).append("\n");
        }
        System.out.println(sb.toString());
    }

    // list를 바탕으로 SubTree를 만든다.
    // 루트노드는 부모가 없으므로 p가 -1이다.
    static int makeTree(int cur, int p) {
        // 이미 방문한 곳이라면 그대로 리턴
        if (dp[cur] != 0) {
            return dp[cur];
        }
        dp[cur] = 1;

        for (int child : list[cur]) {
            // 현재 노드의 붙어있는 노드가 부모노드가 아니라면 전부다 서브트리에 저장한다.
            if (child != p) {
                // 재귀 수행
                dp[cur] += makeTree(child, cur);
            }
        }

        return dp[cur];
    }

}

/* 다른 풀이

public class Main {
	public static LinkedList<Integer>[] adjList;
	public static LinkedList<Integer>[] subList;
	public static int[] parent;
	public static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		adjList = new LinkedList[N+1];
		subList = new LinkedList[N+1];
		parent = new int[N+1];
		size = new int[N+1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<Integer>();
			subList[i] = new LinkedList<Integer>();
		}

		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		makeTree(R, -1);
		countSubTree(R);

		for(int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());

			System.out.println(size[q]);
		}

		br.close();
	}

	public static void makeTree(int cur, int p) {
		for(int next : adjList[cur]) {
			if(next != p) {
				subList[cur].add(next);
				parent[next] = cur;
				makeTree(next, cur);
			}
		}
	}

	public static void countSubTree(int cur) {
		size[cur] = 1;

		for(int next : subList[cur]) {
			countSubTree(next);
			size[cur] += size[next];	
		}
	}
}

*/
