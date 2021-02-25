package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class BlockMoving_60063 {
	private static int n;
	private static int[][] board;
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] rdx = {-1, 1, 1, -1};
	private static final int[] rdy = {1, 1, -1, -1};
	private static class Robot {
		int x, y;
		int dir;
		int time;
		
		Robot(int x, int y, int dir, int time){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.time = time;
		}
		public int getOtherX() {
			return x + dx[dir];
		}
		
		public int getOtherY() {
			return y + dy[dir];
		}
		
	}
	public static void main(String[] args) {
		  int[][] map = {
	            {0, 0, 0, 1, 1},
	            {0, 0, 0, 1, 0},
	            {0, 1, 0, 1, 1},
	            {1, 1, 0, 0, 1},
	            {0, 0, 0, 0, 0}
	      };
	      solution(map);

	}
	public static int solution(int[][] map) {
		n = map.length;
		board = map;
		Queue<Robot> q = new LinkedList<>();
		q.add(new Robot(0, 0, 0, 0));
		boolean[][][] visit = new boolean[n][n][4];
		visit[0][0][0] = true;
		
		return bfs(q, visit);
	}
	
	private static int bfs(Queue<Robot> q, boolean[][][] visit) {
		int x, y, dir, time, ox, oy; // Queue에서 꺼낸 로봇의 x, y, 방향, 시간, 다른 x, y
		int nx, ny, nox, noy, ndir;	// 로봇이 이동 후 가지게 되는 위치 및 방향
		int rx, ry;		//회전 할 때 판단해야할 벽의 위치
		
		while(!q.isEmpty()) {
			Robot robot = q.poll();
			x = robot.x;
			y = robot.y;
			dir = robot.dir;
			time = robot.time;
			ox = robot.getOtherX();
			oy = robot.getOtherY();
			
			if(isFinish(x, y) || isFinish(ox, oy)) {
				
				System.out.println(time);
				return time; // 도착하면 리턴
			}
			
			for(int i=0; i<4; i++) {	//동서남북
				nx = x + dx[i];
				ny = y + dy[i];
				nox = ox + dx[i];
				noy = oy + dy[i];
				
				if(!isValid(nx, ny) || !isValid(nox, noy)) continue;	// 맵 밖으로 나갔는지 체크
				if(board[nx][ny] == 1 || board[nox][noy] == 1 ) continue;	// 벽인지 체크
				if(visit[nx][ny][dir]) continue;	// 이미 방문한 곳인지 체크
				
				visit[nx][ny][dir] = true;
				q.add(new Robot(nx, ny, dir, time+1 ));
			}
			
			for(int i=1; i<4; i+=2) {	// x, y를 기준으로 90도 회전
				ndir = (dir + i) % 4;
				nox = x + dx[ndir];
				noy = y + dy[ndir];
				
				int tempDir = (i == 1) ? ndir : dir;
				rx = x + rdx[tempDir];
				ry = y + rdy[tempDir];
				
				if(!isValid(nox, noy) || !isValid(rx, ry)) continue;	// 맵 밖으로 나갔는지 체크
				if(board[nox][noy] == 1 || board[rx][ry] == 1 ) continue;	// 벽인지 체크
				if(visit[x][y][ndir]) continue;	// 이미 방문한 곳인지 체크
				
				visit[x][y][ndir] = true;
				q.add(new Robot(x, y, ndir, time+1));
			}
			
			dir = (dir + 2) % 4; // 방향 반대 처리
			for (int i = 1; i < 4; i += 2) { // ox, oy를 기준으로 90도 회전
				ndir = (dir + i) % 4;
				nx = ox + dx[ndir];
				ny = oy + dy[ndir];
				
				int tempDir = (i == 1) ? ndir : dir;
				rx = ox + rdx[tempDir];
				ry = oy + rdy[tempDir];
				
				ndir = (ndir + 2) % 4;
				if (!isValid(nx, ny) || !isValid(rx, ry)) continue;
				if (board[nx][ny] == 1 || board[rx][ry] == 1) continue;
				if (visit[nx][ny][ndir]) continue;
				
				visit[nx][ny][ndir] = true;
				q.add(new Robot(nx, ny, ndir, time + 1));
			}
		}
		return -1;
		
	}
	private static boolean isValid(int x, int y) { // 맵 밖으로 나갔는지
		return x>=0 && y>=0 && x<n && y<n;
	}
	
	private static boolean isFinish(int x, int y) {
		return x == n-1 && y == n-1;
	}

}
