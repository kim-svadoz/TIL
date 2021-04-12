import java.io.*;
import java.util.*;
public class p2503 {
    static int n;
    static String[] game;
    static int[] strike;
    static int[] ball;
    static int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static int cnt;
    static int set[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        game = new String[n];
        strike = new int[n];
        ball = new int[n];
        
        for (int tc = 0; tc < n; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            game[tc] = st.nextToken();
            strike[tc] = Integer.parseInt(st.nextToken());
            ball[tc] = Integer.parseInt(st.nextToken());
        }
        
        set = new int[9];
        
        for (int i = 0; i < 9; i++) {
            set[i] = i;
        }
        cnt = 0;
        // 0부터 9까지의 수 중에서 3개의 숫자로 순열을 돌린다.
        perm(set, 0, 9, 3);
        System.out.println(cnt);
    }
    static void perm(int[] set, int depth, int n, int k) {
        if (depth == k) {
            String baseball = "";
            for (int i = 0; i < depth; i++) {
                baseball += Integer.toString(num[set[i]]);;
            }
            if(checking(baseball)) cnt++;
            return;
        }
        
        for (int i = depth; i < n; i++) {
            swap(set, i, depth);
            perm(set, depth + 1, n, k);
            swap(set, i, depth);
        }
    }
    
    static boolean checking(String baseball) {
        String[] chk = baseball.split("");
        
        for (int i = 0; i < n; i++) {
            String[] chk2 = game[i].split("");
            int s = 0;
            int b = 0;
            
            for (int j = 0; j < chk.length; j++) {
                if (chk[j].equals(chk2[j])) {
                    s++;
                }
            }
            
            for (int j = 0; j < chk.length; j++) {
                for (int z = 0; z < chk2.length; z++) {
                    if (j == z) continue;
                    else {
                        if (chk[j].equals(chk2[z])) {
                            b++;
                        }
                    }
                }
            }

            // 틀린게 있으면 해당하지 않는다
            if (strike[i] != s || ball[i] != b) {
                return false;
            }
        }
        return true;
    }
    
    static void swap(int[] set, int i, int index) {
        int temp = set[i];
        set[i] = set[index];
        set[index] = temp;
    }
}

/* 완전탐색 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] MH = new String[N];
		
		int[][] MH_info = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			MH[i] = st.nextToken();
			MH_info[i][0] = Integer.parseInt(st.nextToken());
			MH_info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;

		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) { 
				if (i != j) {
					for (int k = 1; k <= 9; k++) { 
						if (k != i && k != j) {
							boolean flag = true;
							for (int s = 0; s < N; s++) { 
								int strike = 0;
								int ball = 0;
								String str = MH[s];
								if (str.charAt(0) - '0' == i)
									strike++;
								if (str.charAt(1) - '0' == j)
									strike++;
								if (str.charAt(2) - '0' == k)
									strike++;
								if (str.charAt(0) - '0' == j || str.charAt(0) - '0' == k)
									ball++;
								if (str.charAt(1) - '0' == i || str.charAt(1) - '0' == k)
									ball++;
								if (str.charAt(2) - '0' == i || str.charAt(2) - '0' == j)
									ball++;
								if (MH_info[s][0] != strike || MH_info[s][1] != ball) {
									flag = false;
									break;
								}
							}
							if (flag)
								ans++;
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
}


*/