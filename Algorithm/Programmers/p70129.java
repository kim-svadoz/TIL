import java.util.*;
public class p70129 {
    static ArrayList<Integer> list;
    static int count, zero_cnt;
    public static void main(String[] args) {
        String s = "110010101001";
        solution(s);
    }

    public static int[] solution(String s) {
        int[] answer = new int[2];
        
        while(!s.equals("1")) {
            count++;
            String str = s.replace("0", "");
            zero_cnt += s.length() - str.length();
            s = Integer.toBinaryString(str.length());
        }
        answer[0] = count;
        answer[1] = zero_cnt;
        return answer;
    }

}
