import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SAMSUNG_babyShark_16236 {
	public static final int max_val = 401, max_int = 21;
	public static int N, size, shark_x, shark_y, min_x, min_y, min_dist, result, eat_cnt=0;
	public static int[][] map, check;
	public static boolean[][] visited;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1}; // �� �� �� ��
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 3
		map = new int[N+1][N+1];
		check = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					size = 2;
					shark_x = i;
					shark_y = j;
					map[i][j] = 0;
				}
			}
		}
		while(true) {
			init_check(); // check�迭�� -1�� �ʱ�ȭ
			bfs(shark_x, shark_y);
			if(min_x != max_int && min_y!=max_int) {
				result +=check[min_x][min_y];
				
				eat_cnt++;
				if(eat_cnt == size) {
					size++;
					eat_cnt=0;
				}
				map[min_x][min_y] = 0;
				
				shark_x = min_x;
				shark_y = min_y;
			}else {
				break;
			}
		}
		
		
		System.out.println(result);
		//�Ʊ��� : 9(ũ��� 2)
		/*
		���� 1: �ڽ��� ũ�⺸�� ũ�� �������� ���� ������ ĭ�� ��� ������ �� ����.
		���� 2: �ڽ��� ũ�⺸�� ���� �����⸸ ���� �� ����
		=> ũ�Ⱑ ������ ������ ���� ���������� ����
		
		���� 3: �������ִ� ���Ⱑ ���ٸ� ������� return;
		���� 4: ������ �ִ� ���Ⱑ 1���� => �� �����⸦������ ����.
		���� 5: ������ �ִ� ���Ⱑ �������� => ���� ����� ������.
			=> ���� ����� �����Ⱑ ������ : ���� �����ִ� ������
			=> �׷��� �����Ⱑ �������� : ���� ���ʿ� �ִ� ������
		���� 6: �ڽ��� ũ��� ���� ���� �����⸦ ������ ���� ũ�Ⱑ 1����. 2 + 2 => 3, 3 +3 => 4	
		*/
		
	}
	public static void bfs(int x, int y) {
		Queue<Shark> q = new LinkedList<>();
		check[x][y] = 0;
		q.add(new Shark(x, y));
		while(!q.isEmpty()) {
			Shark cur = q.poll();
			x = cur.x;
			y = cur.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<1 || nx>N || ny<1 || ny>N) continue;
				if(check[nx][ny]!=-1 || map[nx][ny]>size) continue;
				check[nx][ny] = check[x][y] + 1;
				if(map[nx][ny]!=0 && map[nx][ny]<size) {
					if(min_dist>check[nx][ny]) {
						min_x = nx;
						min_y = ny;
						min_dist = check[nx][ny];
					}else if(min_dist==check[nx][ny]) {
						if(min_x==nx) {
							if(min_y>ny) {
								min_x=nx;
								min_y=ny;
							}
						}else if(min_x>nx) {
							min_x=nx;
							min_y=ny;
						}
					}
				}
				q.add(new Shark(nx, ny));	
			}
		}
		
	}
	
	public static void init_check() {
		min_dist = max_val;//�ִܰŸ� = 401
		min_x = max_int; // 21
		min_y = max_int; // 21 (������ ũ�Ⱑ 20)
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				check[i][j] = -1;
			}
			
		}
	}
	static class Shark{
		int x;
		int y;
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}


}
