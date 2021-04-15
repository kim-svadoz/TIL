/*
    종이조각문제.

    n과 m이 최대 4이고 각 칸이 가로이거나 세로인 2가지 종류이므로 모든 경우의 수는 2^16승이므로 완탐 가능
    2차원 배열을 1차원배열로 만들어 인덱스 조작에 편의를 더함.
    r행 c열은 r * m + c이다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14391 {
    static int n, m, answer = 0;
    static int[] map;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n * m];
        map = new int[n * m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i * m + j] = input.charAt(j)-'0';
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int idx) {
        if (idx == n * m) {
            check();
            return;
        }

        // 해당 좌표 선택 -> 가로선택
        visit[idx] = true;
        dfs(idx + 1);
        visit[idx] = false;
        // 해당 좌표 미선택 -> 세로선택
        dfs(idx + 1);
    }

    static void check() {
        int sum = 0, tmp = 0;

        // 가로
        for (int i = 0; i < n; i++) {
            tmp = 0;
            for (int j = 0; j < m; j++) {
                if (visit[i * m + j]) {
                    tmp *= 10;
                    tmp += map[i * m + j];
                } else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }

        // 세로 -> n과 m의 위치를 바꾸어 배열 회전과 동일한 효과를 본다. <<<< 핵심 포인트.
        for (int j = 0; j < m; j++) {
            tmp = 0;
            for (int i = 0; i < n; i++) {
                if (!visit[i * m + j]) {
                    tmp *= 10;
                    tmp += map[i * m + j];
                } else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }

        answer = Math.max(answer, sum);
    }
}

/* 비트마스킹 풀이

import java.io.*;
import java.util.StringTokenizer;
public class Main {
	static int N, M;
	static int[][] paper;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			paper = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < M; j++) {
					paper[i][j] = input.charAt(j) - '0';
				}
			}
			
			int max = get_total();
			System.out.println(max);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int get_total() {
		int max = 0;
		for(int s = 0; s < (1 << (N * M)); s++) {
			int sum = 0;
			sum += get_hori(s);
			sum += get_vert(s);
			if(sum > max) {
				max = sum;
			}
		}
		return max;		
	}
	
	public static int get_hori(int s) {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			int temp = 0;
			for(int j = 0; j < M; j++) {
				if((s & (1 << i * M + j)) != 0) {
					temp *= 10;
					temp += paper[i][j];
				}
				else {
					sum += temp;
					temp = 0;
				}
			}
			sum += temp;
		}
		return sum;
	}
	
	public static int get_vert(int s) {
		int sum = 0;
		for(int i = 0; i < M; i++) {
			int temp = 0;
			for(int j = 0; j < N; j++) {
				if((~s & (1 << j * M + i)) != 0) {
					temp *= 10;
					temp += paper[j][i];
				}
				else {
					sum += temp;
					temp = 0;
				}
			}
			sum += temp;
		}
		return sum;
	}

}

*/