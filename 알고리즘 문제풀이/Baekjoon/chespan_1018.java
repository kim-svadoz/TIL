package baekjoon;

import java.util.Scanner;

public class chespan_1018 {
	static int M, N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		Scanner key = new Scanner(System.in);
		M = key.nextInt();
		N = key.nextInt();
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			String t = key.next();
			for(int j=0; j<N; j++) {
				map[i][j] = t.charAt(j);
			}
		}
		int result = Integer.MAX_VALUE;
		for(int i=0; i<=M-8; i++) {
			for(int j=0; j<=N-8; j++) {
				result = Math.min(result, solution(i, j));
			}
		}
		System.out.println(result);
	}
	
	private static int solution(int x, int y) {
		int result = 0;
		int temp = 'B';
		for(int i=x; i<x+8; i++) {
			if(map[i][y]!=temp) result++;
			for(int j=y+1; j<y+8; j++) {
				if(map[i][j]==temp) {
					result++;
					if(temp=='B') {
						temp = 'W'; 
					}
					else {
						temp = 'B';
					}
				}else {
					temp = map[i][j];
				}
			}
		}
		
		int result2 = 0;
		temp = 'W';
		for(int i=x; i<x+8; i++) {
			if(map[i][y]!=temp) result2++;
			for(int j=y+1; j<y+8; j++) {
				if(map[i][j]==temp) {
					result2++;
					if(temp=='B') {
						temp = 'W'; 
					}
					else {
						temp = 'B';
					}
				}else {
					temp = map[i][j];
				}
			}
		}
		
		return result>result2?result2:result;
		
	}
}