import java.util.*;
import java.io.*;

public class p16916 {
    static String origin;
    static String pattern;
    static int cnt = 0;
    static List<Integer> li;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        origin = br.readLine();
        pattern = br.readLine();
        li = new ArrayList<>();
        KMP(origin, pattern);
        if (cnt > 0) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
        
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
}