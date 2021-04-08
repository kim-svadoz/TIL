// 줄어드는 수.
import java.io.*;
import java.util.*;
public class p1174 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        ArrayList<Long> arr = new ArrayList<>();
        // 2^10 만큼의 모든 경우의 수를 만든다.
        for (int i = 1; i < (1<<10); i++) {
            long sum = 0;
            // 10 자리 만큼 탐색한다.
            for (int j = 0; j < 10; j++) {
                // i가 0^2, 1^2, 2^2 .... j^2 승보다 크다면
                // -> i에 해당하는 비트가 각각 어느 위치(몇 번째 자리)에 비트를 가지고 있는지 체크
                // if문을 만족한다고 해당 자릿수가 존재한다는 것이므로 sum*10 + num[j]를 추가.
                if ((i & (1 << j)) > 0) {
                    sum = sum * 10 + num[j];
                }
            }
            arr.add(sum);
        }
        
        Collections.sort(arr);
        if (n > arr.size()) {
            System.out.println("-1");
            return;
        }
        System.out.println(arr.get(n - 1));
    }
}

/**** 원래 풀려고 했던 재귀 방식으로의 풀이

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Long> list = new ArrayList<Long>();
	static int[] num = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
		descNum(0, 0);
		list.sort(null);
		if (N > 1023)
			System.out.println(-1);
		else
			System.out.println(list.get(N - 1));
	}

	private static void descNum(long sum, int idx) {
		if (!list.contains(sum))
			list.add(sum);
		if (idx >= 10)
			return;
		descNum((sum * 10) + num[idx], idx + 1);
		descNum(sum, idx + 1);

	}

}

*/