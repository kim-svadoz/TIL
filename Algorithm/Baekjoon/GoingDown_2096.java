package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoingDown_2096 {
	static int[][] d = new int[100001][3];
	static int[][] map = new int[1000001][3];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		// 메모이제이션 0번째
		d[1][0] = map[1][0];
		d[1][1] = map[1][1];
		d[1][2] = map[1][2];
		
		// max 점화식
		for(int i=2; i<=N; i++) {
			d[i][0] = map[i][0] + Math.max(d[i-1][0], d[i-1][1]);
			d[i][1] = map[i][1] + Math.max(d[i-1][0], Math.max(d[i-1][1], d[i-1][2]));
			d[i][2] = map[i][2] + Math.max(d[i-1][1], d[i-1][2]);
		}
		
		int max = Math.max(d[N][0], Math.max(d[N][1], d[N][2]));
		
		// min 점화식
		for(int i=2; i<=N; i++) {
			d[i][0] = map[i][0] + Math.min(d[i-1][0], d[i-1][1]);
			d[i][1] = map[i][1] + Math.min(d[i-1][0], Math.min(d[i-1][1], d[i-1][2]));
			d[i][2] = map[i][2] + Math.min(d[i-1][1], d[i-1][2]);
		}
		
		int min = Math.min(d[N][0], Math.min(d[N][1], d[N][2]));
		
		System.out.println(max + " " + min);
	}

}
