import java.io.*;
import java.util.*;

public class p21278 {
    static int n, m, min = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dist;
    static int[] place;
    static boolean[] visited;
    static int[] minPlace = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        place = new int[2];
        visited = new boolean[n + 1];
        map = new int[n + 1][n + 1];
        // 각 도시간의 거리를 계산하기 위해 초기화
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++) {
                if (i == j) continue;
                map[i][j] = n;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u][v] = 1;
            map[v][u] = 1;
        }

        // 플로이드 와샬 알고리즘을 통해 최단거리 셋팅
        floyd_warshall();

        solution(1, 0);
        System.out.println(minPlace[0]+" "+minPlace[1]+" "+min);
    }

    static void floyd_warshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    static void solution(int start, int depth) {
        if (depth == 2) {
            int sum = 0;
            // 치킨집이 아닌 건물에서 가장 가까운 치킨집까지의 최단거리들을 더하자.
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    int mmin = Integer.MAX_VALUE;
                    for (int j = 0; j < 2; j++) {
                        mmin = Math.min(map[i][place[j]], mmin);
                    }
                    sum += mmin * 2;
                }
            }
            if (min > sum) {
                for (int j = 0; j < 2; j++) {
                    minPlace[j] = place[j];
                }
                min = sum;
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            place[depth] = i;
            solution(i, depth + 1);
            visited[i] = false;
        }
    }
}
/* 좀 더 간단한 풀이 -> dfs를 쓰지 않고 삼중 포문을 활용해 각 도시별 최단 거리를 구할 수 있다.

import java.util.*;
import java.io.*;

public class Main {
	
	public static int N, M, adj[][], dist[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = adj[b][a] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				
				if(adj[i][j]!=0) dist[i][j] = adj[i][j];
				else dist[i][j] = 1000000;
			}
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int ans1 = Integer.MAX_VALUE;
		int ans2 = Integer.MAX_VALUE;
		int sum = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				int tmp = solve(i, j);
				if(sum > tmp) {
					ans1 = i; ans2 = j;
					sum = tmp;
				}
			}
		}
		
		System.out.printf("%d %d %d", ans1, ans2, sum*2);
	}
	
	public static int solve(int i, int j) {
		int distance = 0;
		for (int k = 1; k <= N; k++) {
			distance += Math.min(dist[i][k], dist[j][k]);
		}
		return distance;
	}
}

*/
