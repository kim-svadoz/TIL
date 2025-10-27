import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2146 {
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static boolean[][] visited;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		map = new int[N][N];
		visited = new boolean[N][N];
		ans = Integer.MAX_VALUE;
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		numbering();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(map[r][c] == 0) continue;
				
				init();
				q.offer(new Node(r, c));
				visited[r][c] = true;
				
				int dist = findShortestBridge(map[r][c]);
				
				if(dist == -1) continue;
				
				ans = ans > dist ? dist : ans;
			}
		}
		
		System.out.println(ans);
	}

	private static int findShortestBridge(int start) {
		int dist = -1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				
				if(map[cur.r][cur.c] != 0 && map[cur.r][cur.c] != start) {
					return dist;
				}
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(visited[nr][nc] || map[nr][nc] == start) continue;
					
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
			dist++;
		}
		
		return -1;
	}

	private static void init() {
		q.clear();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				visited[r][c] = false;
			}
		}
	}

	private static void numbering() {
		int number = 2;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(visited[r][c] || map[r][c] == 0) continue;
				map[r][c] = number;
				q.offer(new Node(r, c));
				visited[r][c] = true;
				
				while(!q.isEmpty()) {
					Node cur = q.poll();
					
					for(int d = 0 ; d < 4 ; ++d) {
						int nr = cur.r + dir[d][0];
						int nc = cur.c + dir[d][1];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N ||
						   visited[nr][nc] || map[nr][nc] == 0) continue;
						if(map[nr][nc] == 1) {
							q.offer(new Node(nr, nc));
							map[nr][nc] = number;
							visited[nr][nc] = true;
						}
					}
				}
				number++;
			}
		}
	}
}