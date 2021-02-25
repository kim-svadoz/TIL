package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RotatingSushi_2531 {
	static int N, d, k, c;
	static int[] arr, visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 8, 접시의 수
		d = Integer.parseInt(st.nextToken());	// 30, 초밥 가지수
		k = Integer.parseInt(st.nextToken());	// 4, 연속해서 먹는 접시 수
		c = Integer.parseInt(st.nextToken());	// 30, 쿠폰 번호
		
		/*
		 * 7 9 7 30 2 7 9 25
		 */
		visit = new int[d+1];
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(slide());
	}
	
	private static int slide() {
		// sliding window 구현
		int total = 0, max = 0;
		// 처음 초기 4접시 먹은거 초기화 작업
		for(int i=0; i<k; i++) {
			if(visit[arr[i]] == 0) total ++;
			visit[arr[i]]++;
		}
		max = total; // 3
		for(int i=1; i<N; i++) {
			if(max <= total) {
				// 내가먹은거 중에 쿠폰이 없다면 +1 추가해주고
				if(visit[c]==0) max = total + 1;
				// 쿠폰음식을 이미 먹었다면 그대로
				else max = total;
			}
			
			// 맨 왼쪽에서 먹은거 하나 빼고
			visit[arr[i-1]]--;
			// 그러니 전체 먹은 개수도 빼고
			if(visit[arr[i-1]] == 0) total--;
			
			// 그다음 전진할 방향 먹은 개수랑 먹었다고 추가해주고
			if(visit[arr[(i+k-1) % N]] == 0) total++;
			visit[arr[(i+k-1) % N]]++;
		}
		
		return max;
	}
	

}
