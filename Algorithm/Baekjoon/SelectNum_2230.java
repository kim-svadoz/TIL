package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SelectNum_2230 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 수열의 원소의 수
		M = Integer.parseInt(st.nextToken());	// 두 수를 골랐을 때, 만족해야 하는 차이의 최솟값
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 1 5 3, 1 2 3 4 5 (M=3)
		}
		Arrays.sort(arr);
		
		int lo=0, hi=0, result=Integer.MAX_VALUE;
		while(hi < N) {
			if(arr[hi] - arr[lo] < M) {
				hi++;
				continue;
			}
			
			if(arr[hi] - arr[lo] == M) {
				result = M;
				break;
			}
			
			result = Math.min(result, arr[hi]-arr[lo]);
			lo++;
		}
		if(result == 200000001) {
			bw.write(0+"\n");
		}else {
			bw.write(result+"\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}

}
