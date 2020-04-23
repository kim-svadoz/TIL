package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 class SAMSUNG_robot_14503 {
	public static int n, m, r, c, d, answer;
	public static int[][] map;
	public static boolean[][] visited;
	public static Robot robot;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
	public static class Robot{
		int x;
		int y;
		int d;
		public Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 11
		m = Integer.parseInt(st.nextToken()); // 10
		map = new int[n][m];
		visited = new boolean[n][m];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 7
		c = Integer.parseInt(st.nextToken()); // 4
		d = Integer.parseInt(st.nextToken()); // 0
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		robot = new Robot(r, c, d);
		visited[r][c] = true;
		++answer;
		
		bfs();
		System.out.println(answer);
	}
	public static void bfs() {
		Queue<Robot> q = new LinkedList<>();
		q.add(robot);
		while(!q.isEmpty()) {
			Robot r = q.remove();
			int x = r.x;
			int y = r.y;
			int d = r.d;
			int next_d = d;
			boolean flag = false;
			for(int i=0; i<4; i++) {
				next_d = turn_left(next_d);
				int nx = x + dx[next_d];
				int ny = y + dy[next_d];
				if(nx>0 && ny>0 && nx<n && ny<m) {
					if(map[nx][ny]==0 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Robot(nx, ny, next_d));
						++answer;
						flag = true;
						break;
					}
						
				}
			}
			if(!flag) {
				next_d = turn_back(d);
				int nx = x + dx[next_d];
				int ny = y + dy[next_d];
				if(nx>0 && ny>0 && nx<n && ny<m) {
					if(map[nx][ny]==0) {
						q.add(new Robot(nx, ny, d));
					}
				}
			}
		}
		
	}
	public static int turn_left(int d) {
		if(d==0) {
			return 3;
		}else if(d==1) {
			return 0;
		}else if(d==2) {
			return 1;
		}else {
			return 2;
		}
	}
	public static int turn_back(int d) {
		if(d==0) {
			return 2;
		}else if(d==1) {
			return 3;
		}else if(d==2) {
			return 0;
		}else {
			return 1;
		}
	}
	
	
}
