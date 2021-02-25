package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwistedElectricWire_1365 {
	static int N, map[], d[];
    static int cnt = 1;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        d = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }
        d[0] = map[0];
        for(int i=1; i<N; i++){
            if(d[cnt-1] < map[i]){
                d[cnt++] = map[i];
            }else if(d[0] > map[i]){
                d[0] = map[i];
            }else{
                // Arrays API 내 이분탐색 함수
                int temp = Arrays.binarySearch(d, 0, cnt, map[i]);
                d[temp < 0 ? -temp-1 : temp] = map[i];
            }
        }
        bw.write(N-cnt+"\n");
    }  
	
	

}
