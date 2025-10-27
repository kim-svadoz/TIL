/*
    완전탐색과 그리디를 가장한 DP..
    오래 분석하고 이를 손으로 써보면서 점화식을 도출하자.
    + DP는 bottom-up이 조금 더 효율적이다.
*/
import java.io.*;
public class p17626 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;

        int min = 0;
        for (int i = 2; i <= n; i++) {
            min = Integer.MAX_VALUE;

            for (int j = 1; j * j <= i; j++) {
                int temp = i - j * j;
                min = Math.min(min, dp[temp]);
            }
            dp[i] = min + 1;
        }
        System.out.println(dp[n]);

    }

}

/* 완전탐색으로 풀기

import java.io.*;

public class Main {

	static int sol(int N) {
		if (Math.sqrt(N) % 1 == 0) return 1;

		for (int i = 0; i < Math.sqrt(N) + 1; i++) {
			for (int j = i; j < Math.sqrt(N) + 1; j++) {
				if (N == i * i + j * j)
					return 2;
			}
		}

		for (int i = 0; i < Math.sqrt(N) + 1; i++) {
			for (int j = i; j < Math.sqrt(N) + 1; j++) {
				for (int k = j; k < Math.sqrt(N) + 1; k++) {
					if (N == i * i + j * j + k * k)
						return 3;
				}
			}
		}

		return 4;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(sol(N));
	}
}

*/