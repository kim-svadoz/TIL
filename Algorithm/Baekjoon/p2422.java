// 아이스크림 
import java.io.*;
import java.util.*;
public class p2422 {
    static int n, m, answer;
    static int[] arr;
    static boolean[] visit;
    static boolean[][] graph;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new boolean[n][n];
        visit = new boolean[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = true;
            graph[b][a] = true;
        }

        result = new int[3];
        
        answer = 0;
        comb(0, 0);
        System.out.println(answer);
    }

    static void comb(int start, int depth) {
        if (depth == 3) {
            if (check()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            result[depth] = i;
            comb(i, depth + 1); // i로 하로 하는게 i + 1로 하는거보다 시간을 덜먹는다
            visit[i] = false;
        }
    }
    
    static boolean check() {
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (graph[result[i]][result[j]]) return false;
            }
        }
        return true;
    }

}

/* 좋은풀이

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			boolean[][] check = new boolean[200][200];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int ice1 = Integer.parseInt(st.nextToken());
				int ice2 = Integer.parseInt(st.nextToken());
				check[ice1 - 1][ice2 - 1] = true;
				check[ice2 - 1][ice1 - 1] = true;
			}
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					for(int k = j + 1; k < N; k++) {
						if(check[i][j] || check[i][k] || check[j][k])
							continue;
						ans++;
					}
				}
			}
			
			System.out.println(ans);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

*/