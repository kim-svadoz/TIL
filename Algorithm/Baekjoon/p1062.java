/*
    모든 단어는 "anta"로 시작하고 "tica"로 끝나기 때문에 우선 5개의 알파벳은 고정이다.
    따라서 k가 5보다 작으면 어느 단어도 읽을 수 없고, k가 26이라면 모든 단어를 읽을 수 있다.
    따라서 나머지 문자들에 대해서 백트래킹을 이용하여 탐색하면 된다.
*/
import java.io.*;
import java.util.*;

public class p1062 {
    static int n, k, max = 0;
    static boolean[] visit = new boolean[26];
    static String[] strArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        strArr = new String[n];

        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k == 26) {
            System.out.println(n);
            return;
        } else {
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                strArr[i] = input.substring(4, input.length() - 4);
            }
            k -= 5;
            visit['a' - 97] = true;
            visit['n' - 97] = true;
            visit['t' - 97] = true;
            visit['i' - 97] = true;
            visit['c' - 97] = true;
            bt(0, 0);
            System.out.println(max);
        }
    }
    
    static void bt(int start, int depth) {
        if (depth == k) {
            int rs = 0;
            for (int i = 0; i < n; i++) {
                boolean isTrue = true;
                for (int j = 0; j < strArr[i].length(); j++) {
                    if (!visit[strArr[i].charAt(j) - 97]) {
                        isTrue = false;
                        break;
                    }
                }
                if (isTrue) {
                    rs++;
                }
            }
            max = Math.max(max, rs);
            return;
        }

        for (int i = start; i < 26; i++) {
            if(!visit[i]) {
                visit[i] = true;
                bt(i, depth + 1);
                visit[i] = false;
            }
        }
    }
    
}

/**** 백트래킹 함수에서 이와 같이 비트마스킹으로 풀 수 도 있다.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, K;
	static int answer = 0;
	static int[] words;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] outline = br.readLine().split(" ");
		N = Integer.parseInt(outline[0]);
		K = Integer.parseInt(outline[1]);
		if(K <5) {
			System.out.println(answer);
			return;
		}else if(K == 26) {
			System.out.println(N);
			return;
		}
		
		K -=5;
		words = new int[N];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<line.length(); j++) {
				char values = line.charAt(j);
				int index = values - 'a';
				words[i] |= 1<<index;
			}
		}
		int standard = 0;
		standard |= 1<<('a' -'a');
		standard |= 1<<('n' -'a');
		standard |= 1<<('t' -'a');
		standard |= 1<<('c' -'a');
		standard |= 1<<('i' -'a');
		combination(0, 0,standard);
		System.out.println(answer);

	}
	
	public static void combination(int index,int start, int flag) {
		if(index == K) {
			int result = 0;
			for(int word : words) {
                // flag = 내가 읽을 수 있는 전체 집합 true면 새로운 단어는 없다.
				if( (word | flag) == flag)
					result ++;
			}
			answer = answer >= result ? answer : result;
			return;
		}
		
		for(int i= start; i<26; i++) {
			if((flag & 1<<i) == 0) {
				combination(index+1, i+1, flag | 1<<i);
			}
		}
	}

}


*/
