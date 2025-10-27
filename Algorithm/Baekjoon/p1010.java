import java.io.*;
import java.util.*;
public class p1010 {
    static int n, m, tc, cnt;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            visit = new boolean[m + 1];
            cnt = 0;
            // m개에서 n개 뽑기
            System.out.println(comb(m, n));
        }
    }
    static int comb(int n, int r) {
        int result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }
    
}

/* 파스칼의 삼각형 원리 + bottom-Up + DP
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] dp = new int[30][30];
 
		// 2번 성질 (n == r, r == 0)
		for (int i = 0; i < 30; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
			
 
		for (int i = 2; i < 30; i++) {
			for (int j = 1; j < 30; j++) {
				// 1번 성질 
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
        
		for(int i = 0; i < T; i++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			
			// M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N이다.
			int N = Integer.parseInt(st.nextToken());	// N = r
			int M = Integer.parseInt(st.nextToken());	// M = n
					
			sb.append(dp[M][N]).append('\n');
		}
		System.out.println(sb);
	}
}

*/

/* Top-Down + DP
public class Main {
	static int[][] dp = new int[30][30];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			
			// M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N이다.
			int N = Integer.parseInt(st.nextToken());	// N = r
			int M = Integer.parseInt(st.nextToken());	// M = n
					
			
			sb.append(combi(M, N)).append('\n');
		}
		
		System.out.println(sb);
 
	}
	
	static int combi(int n, int r) {
		
		// 이미 풀린 경우 바로 반환
		if(dp[n][r] > 0) {
			return dp[n][r]; 
		}
		
		// 2번 성질
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		
		// 1번 성질
		return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
	}

*/