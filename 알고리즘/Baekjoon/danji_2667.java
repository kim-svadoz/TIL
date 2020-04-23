package baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class danji_2667 {
	static int N, cnt;
	static int[][] map;
	static int[][] visited;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static ArrayList<Integer> my = new ArrayList<Integer>();	
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		N= key.nextInt();
		
		map = new int[N][N];
		visited = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String input = key.next();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 1 && visited[i][j] ==0) {
					cnt = 1;
					solution(i,j);
					my.add(cnt);
				}
			}
		}
		
		Collections.sort(my);
		System.out.println(my.size());
		for(int i=0; i<my.size();i++) {
			System.out.println(my.get(i));
		}
		
	}
	
	public static int solution(int x, int y) {
		visited[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>=0 && ny>=0 && nx<N && ny<N) {
				if(map[nx][ny]==1 && visited[nx][ny]==0) {
					solution(nx,ny);
					cnt++;
				}
			}
		}
		return cnt;
	}	
}
///ÀÌ¾ß¾Ædddddddd
