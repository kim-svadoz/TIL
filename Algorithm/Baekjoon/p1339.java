/*
    단어수학
    (내가 푼 풀이)
    set과 map, backtracking을 이용해 가능한 모든 조합 배치에 대해 계산하고 최대값을 구한다.
    조합이 가능한 이유는 수의 최대 길이는 8개 이기 때문에 충분하다!

    (아래 풀이)
    높은 자릿수에 높은값을 부여한다.
    GCF : 100G, 10C, 1F
    ACDEB : 10000A, 1000C, 100D, 10E, 1B
    이 값을 더하면
    10000A, 1B, 1010C, 100D, 10E, 1F, 100G가 나오게 된다.
    이 값을 26개의 int 배열에 넣어준 뒤 정렬한다.
    그리고 높은 갓부터 9~0씩 곱하면 답을 구해낼 수 있다.
*/
import java.io.*;
import java.util.*;

public class p1339 {
    static char[][] input;
    static HashMap<Character, Integer> map;
    static HashSet<Character> set;
    static List<Character> list;
    static boolean[] selected;
    static int n, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        set = new HashSet<>();
        map = new HashMap<>();
        input = new char[n][];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            input[i] = line;

            // 주어진 알파벳을 set에 넣어 중복제거
            for (char c : line) set.add(c);
        }
        list = new ArrayList<>(set);
        selected = new boolean[10];

        perm(0);
        System.out.println(max);
    }

    static void perm(int depth) {
        if (depth == list.size()) {
            int temp = calc();
            max = Math.max(max, temp);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            // 문자 - 숫자 매핑
            map.put(list.get(depth), i);
            perm(depth + 1);
            selected[i] = false;
        }
    }

    // 현재 맵핑된 문자-숫자를 바탕으로 합계 구하기
    static int calc() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (char c : input[i]) {
                temp = temp * 10 + map.get(c);
            }
            result += temp;
        }
        return result;
    }
}

/*abstract

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String arr[] = new String [N];
		int check[] = new int[26];
		int max = 0;
		
		for(int i = 0; i<N; i++) {
			arr[i] = br.readLine();
		}
		
		
		for(int i=0; i<N; i++) {
			int gi = 1;
			for(int j=arr[i].length()-1; j>=0; j--) {
				check[arr[i].charAt(j) - 'A'] = check[arr[i].charAt(j) - 'A'] + gi;
				gi *=10;
			}
		}

		Arrays.sort(check);
		
		int sum = 0;
		int gi = 9;

		for(int i=0; i<10; i++) {
			sum += check[25-i] * gi;
			gi--;
		}
		
		System.out.println(sum);
		
	}
}

*/