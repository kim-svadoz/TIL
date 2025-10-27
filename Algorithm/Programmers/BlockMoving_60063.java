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
		int x, y, dir, time, ox, oy; // Queue���� ���� �κ��� x, y, ����, �ð�, �ٸ� x, y
		int nx, ny, nox, noy, ndir;	// �κ��� �̵� �� ������ �Ǵ� ��ġ �� ����
		int rx, ry;		//ȸ�� �� �� �Ǵ��ؾ��� ���� ��ġ
		
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
				return time; // �����ϸ� ����
			}
			
			for(int i=0; i<4; i++) {	//��������
				nx = x + dx[i];
				ny = y + dy[i];
				nox = ox + dx[i];
				noy = oy + dy[i];
				
				if(!isValid(nx, ny) || !isValid(nox, noy)) continue;	// �� ������ �������� üũ
				if(board[nx][ny] == 1 || board[nox][noy] == 1 ) continue;	// ������ üũ
				if(visit[nx][ny][dir]) continue;	// �̹� �湮�� ������ üũ
				
				visit[nx][ny][dir] = true;
				q.add(new Robot(nx, ny, dir, time+1 ));
			}
			
			for(int i=1; i<4; i+=2) {	// x, y�� �������� 90�� ȸ��
				ndir = (dir + i) % 4;
				nox = x + dx[ndir];
				noy = y + dy[ndir];
				
				int tempDir = (i == 1) ? ndir : dir;
				rx = x + rdx[tempDir];
				ry = y + rdy[tempDir];
				
				if(!isValid(nox, noy) || !isValid(rx, ry)) continue;	// �� ������ �������� üũ
				if(board[nox][noy] == 1 || board[rx][ry] == 1 ) continue;	// ������ üũ
				if(visit[x][y][ndir]) continue;	// �̹� �湮�� ������ üũ
				
				visit[x][y][ndir] = true;
				q.add(new Robot(x, y, ndir, time+1));
			}
			
			dir = (dir + 2) % 4; // ���� �ݴ� ó��
			for (int i = 1; i < 4; i += 2) { // ox, oy�� �������� 90�� ȸ��
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
	private static boolean isValid(int x, int y) { // �� ������ ��������
		return x>=0 && y>=0 && x<n && y<n;
	}
	
	private static boolean isFinish(int x, int y) {
		return x == n-1 && y == n-1;
	}

}
