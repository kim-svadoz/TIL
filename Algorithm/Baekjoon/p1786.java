import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p1786 {
    static int cnt = 0;
    static List<Integer> li;
    static int[] getPi(String ptn) {
        int[] pi = new int[ptn.length()];
        int j = 0;
        for(int i=1; i<ptn.length(); i++){
            while(j>0 && ptn.charAt(i)!=ptn.charAt(j)){
                j = pi[j-1];
            }
            if(ptn.charAt(i) == ptn.charAt(j))
                pi[i] = ++j;
        }
        return pi;
    }
    
    static void KMP(String org, String ptn) {
        int pi[] = getPi(ptn);
        int j = 0;
        for(int i=0; i<org.length(); i++) {
            while(j>0 && org.charAt(i)!=ptn.charAt(j)) {
                j = pi[j-1];
            }
            if(org.charAt(i)==ptn.charAt(j)) {
                if(j==ptn.length()-1) {
                    ++cnt;
                    li.add(i-j+1);
                    j=pi[j];
                } else {
                    j++;
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String pattern = br.readLine();
        li = new ArrayList<>();
        KMP(origin, pattern);
        System.out.println(cnt);
        for(int i=0; i<cnt; i++)
            System.out.println(li.get(i));
    }
}

/*

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

//KMP알고리즘
public class Main {
	static int total;
	static String T, P;
	static int[] fail;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		fail = new int[P.length()];

		// 실패함수로 실패 배열 만들기
		failFunc();
		compare();

		System.out.println(total);
		System.out.println(sb.toString());
	}

	static void compare() {
		int j = 0;
		for (int i = 0; i < T.length(); i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j)) {
				j = fail[j - 1];
			}

			if (T.charAt(i) == P.charAt(j)) {
				if (j == P.length() - 1) {
					sb.append(i - P.length() + 2).append(" ");
					j = fail[j];
					total++;
				} else {
					j++;
				}
			}
		}
	}

	static void failFunc() {
		int j = 0;
		for (int i = 1; i < P.length(); i++) {
			while(j > 0 && P.charAt(i) != P.charAt(j)) {
				j = fail[j-1];
			}
			
			if(P.charAt(i) == P.charAt(j)) {
				fail[i] = ++j;
			}
		}
	}

}

*/