import java.io.*;
import java.util.*;

public class p4659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String input = br.readLine();
            if (input.equals("end")) break;
            
            sb.append("<").append(input).append("> is ");
            if (!isHighQuality(input)) {
                sb.append("not ");
            }
            sb.append("acceptable.").append("\n");
        }
        System.out.println(sb.toString());
    }
    static boolean isHighQuality(String s) {
        char[] cArr = s.toCharArray();
        List<Character> cList = new ArrayList<>();
        for (int i = 0; i < cArr.length; i++) {
            cList.add(cArr[i]);
        }
        // 모음을 포함하는지
        if (!haveMo(cList)) return false;
         
        // 자음 혹은 모음 연속 3개가 오는지
        for (int i = 2; i < cArr.length; i++) {
            // 모음일 때
            if (isMo(cArr[i])) {
                if (isMo(cArr[i - 1]) && isMo(cArr[i - 2])) {
                    return false;
                }
            } else {
                if (!isMo(cArr[i - 1]) && !isMo(cArr[i - 2])) {
                    return false;
                }
            }
        }
        
        // ee, oo 제외한 연속 2개가 오는지
        for (int i = 1; i < cArr.length; i++) {
            if (cArr[i] == cArr[i - 1]) {
                if (cArr[i] != 'e' && cArr[i] != 'o') {
                    return false;
                } 
            }
        }
        return true;
    }
    
    static boolean haveMo(List<Character> cList) {
        return (cList.contains('a') || cList.contains('e') || cList.contains('i') || cList.contains('o') || cList.contains('u'));
    }
    
    static boolean isMo(Character c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}