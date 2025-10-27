//*********** 메모리:16000KB, 시간:156ms
import java.io.*;
import java.util.*;

public class p9046 {
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb;
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String line = br.readLine();
            sb = new StringBuilder();
            int[] alpha = new int[26];
            for (char c : line.replaceAll(" ", "").toCharArray()) {
                alpha[c-'a'] ++;
            }

            int[] copy = alpha.clone();
            Arrays.sort(copy);
            if (copy[alpha.length - 1] == copy[alpha.length - 2]) {
                sb.append("?");
            } else {
                int max = -1;
                String c = "";
                for (int j = 0; j < 26; j++) {
                    if (max < alpha[j]) {
                        max = alpha[j];
                        c = Character.toString(j + 97);
                    }
                }
                sb.append(c);
            }
            sb.append("\n");
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

/*********** 메모리:14804KB, 시간:132ms

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			int[] result = new int[26];
			char[] arr = br.readLine().toCharArray();
			for (char c : arr) {
				if (c >= 'a' && c <= 'z') {
					result[c - 'a']++;
				}
			}
			
			int max = 0;
			for (int e : result) {
				if (e > max) max = e;
			}
			
			int count = 0;
			int answer = 0;
			for (int j = 0; j < 26; j++) {
				if (result[j] == max) {
					count++;
					answer = j;
				}
			}
			
			sb.append(count == 1 ? (char)('a' + answer) : "?").append(System.lineSeparator());
		}
		
		System.out.print(sb.toString());
	}

}

*/