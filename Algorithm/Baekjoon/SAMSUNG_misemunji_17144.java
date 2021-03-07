import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SAMSUNG_misemunji_17144 {
	public static int R, C, T, mR, cnt;
	public static int result = 0;
	public static int[][] map, copymap, copymap2, copymap3;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // ��
		C = Integer.parseInt(st.nextToken()); // ��
		T = Integer.parseInt(st.nextToken()); // T�ʰ� ���� �� ?
		map = new int[R][C];
		copymap = new int[R][C];
		copymap2 = new int[R][C];
		copymap3 = new int[R][C];
		copymap = map;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					mR = i;
				}
			}
		}
		for(int t=0; t<T; t++) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j]>0) {
						dfs(i, j);
					}
				}
			}
			copymap2 = copymap;
			copymap2[mR][t+1] = 0;
			copymap2[mR+1][t+1] = 0;
			
			result = antiClock(mR, t+1) + Clock(mR+1, t+1);
		}
		
		System.out.println(result);
	}
	public static void dfs(int x, int y) {
		if(map[x][y]>=5) {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && ny>=0 && nx<R && ny<C && map[nx][ny]!=-1) {
					copymap[nx][ny] =  map[nx][ny] + (map[x][y]/5);
					cnt++;
				}
			}
			copymap[x][y] = map[x][y] - cnt*(map[x][y]/5);
		}
	}
	public static int antiClock(int x, int y) { // �ݽð�
		/*if(x==mR && y==1) {
			copymap2[x][y] =0;
			int num=0;
			for(int i=0; i<mR; i++) {
				for(int j=0; j<C; j++) {
					num += copymap2[i][j];
				}
			}
			
			return num;
		}*/
		
		if(x>=0 && y>=0 && x<R && y<C) {
			if(x<=mR && y==C-1) {
				up(x, y);
			}else if(x==0 && y<=C-1){
				left(x, y);
			}else if(x>=0 && x<mR && y==0){
				down(x, y);
			}else if(x==mR && y<C-1){
				right(x, y);
			}
		}
		
		return 0;
	}
	public static int Clock(int x, int y) { // �ð�
		/*if(x==mR+1 && y==1) {
			copymap2[x][y] =0;
			int num=0;
			for(int i=mR; i<R; i++) {
				for(int j=0; j<C; j++) {
					num += copymap2[i][j];
				}
			}
			
			return num;
		}*/
		
		if(x>=0 && y>=0 && x<R && y<C) {
			if(x<R && x>=mR && y==C-1) {
				down(x, y);
			}else if(x==R-1 && y>0){
				left(x, y);
			}else if(x>mR+1 && y==0){
				up(x, y);
			}else if(x==mR+1 && y>=0 && y<C){
				right(x, y);
			}
		}
		
		
		return 0;
	}
	public static void right(int x, int y) {
		copymap2[x][y+1] = copymap[x][y];
		result+= copymap[x][y];
		if(x>=mR) {
			Clock(x, y+1);
		}else {
			antiClock(x, y+1);
		}
		
	}
	public static void up(int x, int y) {
		copymap2[x-1][y] = copymap[x][y];
		result+= copymap[x][y];
		if(x>=mR) {
			Clock(x-1, y);
		}else {
			antiClock(x-1, y);
		}
	}
	public static void left(int x, int y) {
		copymap2[x][y-1] = copymap[x][y];
		result+= copymap[x][y];
		if(x>=mR) {
			Clock(x, y-1);
		}else {
			antiClock(x, y-1);
		}
	}
	public static void down(int x, int y) {
		copymap[x+1][y] = copymap[x][y];
		result+= copymap[x][y];
		if(x>=mR) {
			Clock(x+1, y);
		}else {
			antiClock(x+1, y);
		}
	}
	

}
