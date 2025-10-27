/*
	문서검색
	String + substring
*/
import java.io.*;
public class p1543 {
    static int cnt = 0;
    static String O, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        O = br.readLine();
        P = br.readLine();
        for (int i = 0; i < O.length() - P.length() + 1; i++) {
            if (O.substring(i, i + P.length()).equals(P)) {
                cnt++;
                i += P.length();
                i --;
            } else {
                continue;
            }
        }
        System.out.println(cnt);
    }
}


/* StringBuilder + indexOf;

import java.io.*;
public class p1543 {
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append(br.readLine());
        String ptn = br.readLine();

        int startIdx = 0;
        int len = ptn.length();
        while ((startIdx = sb.toString().indexOf(ptn)) != -1) {
            sb.delete(0, startIdx + len);
            cnt++;
        }
        System.out.println(cnt);
    }
}

*/