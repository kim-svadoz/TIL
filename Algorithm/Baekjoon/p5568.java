/*
    처음 풀때는 List를 이용해서 list.contains() 를 활용해 중복키를 체크하였다.
    하지만, HashSet을 이용하면 객체를 중복해서 저장할 수 없는 성질 덕분에 더 쉽게 구현할 수 있다. + 효율성이 아주 많이 개선된다.
    하지만 Set은 저장 순서가 유지되지 않기 때문에 저장 순서를 유지해야 한다면 LinkedHashSet을 사용해야 한다.
    또한 TreeMap과 마찬가지로 TreeSet은 키를 기준으로 자동으로 정렬을 해주는 성질이 있다.

    이처럼 Set의 큰 장점은 중복을 자동으로 제거해준다는 것이기 떄문에 잘 활용해보자.
*/
import java.io.*;
import java.util.*;
public class p5568 {
    static int n, k;
    static int[] arr, result;
    static boolean[] visit;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        set = new HashSet<>();
        visit = new boolean[n];
        result = new int[n];
        comb(0, 0);
        System.out.println(set.size());
    }
    static void comb(int start, int depth) {
        if (depth == k) {
            String str = "";
            for (int i = 0; i < k; i++) {
                str += result[i];
            }
            set.add(str);
            return; 
        }
        
        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            result[depth] = arr[i];
            comb(start, depth + 1);
            visit[i] = false;
        }
    }
}

/* 더 좋은 풀이 dfs를 돌 때 저장할 string을 가지고 돈다

import java.io.*;
import java.util.*;
public class Main {
	public static StringTokenizer stk;
	public static StringBuffer sb = new StringBuffer();
	public static HashSet<String> hs = new HashSet<>();
	public static boolean[] visited;
	public static String[] card;
	public static int cnt = 0, n, k;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		card = new String[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			card[i] = br.readLine();
		}
		dfs(k, 1, "");
		System.out.println(hs.size());
	}
	public static void dfs(int k, int idx, String s) {
		if (k == 0) {
			hs.add(s);
			return;
		}
		if (idx > n) {
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(k - 1, i, s + card[i]);
				visited[i] = false;
			}
		}
	}
}


*/