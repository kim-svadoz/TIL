/*
	고냥이
	연속된 문자열 -> 투포인터!!!
*/
import java.io.*;
import java.util.*;
public class p16472 {
    static int n;
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = br.readLine();
        
        int[] check = new int[26];
        int lo = 0, hi = 0, max = 0, cnt = 0;
        check[s.charAt(0) - 'a']++;
        cnt++;

        while (true) {
        	hi++;

        	// 종료조건 : 오른쪽 포인터가 문자 길이를 초과 했을 경우
        	if (hi == s.length()) break;

        	int num = s.charAt(hi) - 'a';
        	check[num]++;

        	// 새로운 문자가 추가
        	if (check[num] == 1) {
        		cnt++;
        	}

        	while (cnt > n) {
        		int num2 = s.charAt(lo) - 'a';
        		check[num2]--;

        		// 해당 문자는 더이상 존재하지 않음
        		if (check[num2] == 0) {
        			cnt--;
        		}

        		lo++;
        	}

        	max = Math.max(max, hi - lo + 1);
        }
        System.out.println(max);
    }
}