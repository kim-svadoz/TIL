package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SectionSum_1806 {
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 100001, sum = 0, lo = 0, hi = 0;
		while(true) {
			if(sum >= S) {
				sum -= arr[lo++];
				result = Math.min(result, hi-lo+1);
			}
			else if(hi == N) break;
			else {
				sum += arr[hi++];
			}
		}
		if(result == 100001) {
			bw.write(0+"\n");
		}else {
			bw.write(result+"\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}
