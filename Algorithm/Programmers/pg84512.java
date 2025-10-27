/*

프로그래머스 > 위클리 챌린지 > 5주차

A
AA
AAA
AAAA
AAAAA
AAAAE
AAAAI
AAAAO
AAAAU
AAAE
AAAEA
AAAEE
AAAEI
AAAEO
AAAEU
AAAI
AAAIA
AAAIE
AAAII
AAAIO
AAAIU
*/
import java.io.*;
import java.util.*;

public class pg84512 {
    public static void main(String[] args) throws IOException {

    }

    private static final String[] ALPHA = {"A", "E", "I", "O", "U"};
    private int ret = 0;
    private boolean check = false;
    public int solution(String word) {
        permutation(word, new StringBuilder());
        return ret;
    }
    
    public void permutation(String word, StringBuilder sb) {
        if (sb.length() > 5) {
            ret--;
            return;
        }
        
        if (isSame(word, sb)) {
            check = true;
            return;
        }
        
        for (int i = 0; i < ALPHA.length; i++) {
            if (check == false) {
                sb.append(ALPHA[i]);
                ret++;
                permutation(word, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    public boolean isSame(String word, StringBuilder sb) {
        return word.equals(sb.toString());
    }
}
