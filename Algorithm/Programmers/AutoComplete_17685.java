package programmers;

import java.util.*;

public class AutoComplete_17685 {

	public static void main(String[] args) {
		String[] words = {"guild","gone","go"};

		solution(words);
	}
	
	public static int solution(String[] words) {
	 int res = 0;

        Arrays.sort(words);
        for(String s:words) {
        	System.out.println(s);
        }

        for (int i = 0; i < words.length; i++) {
            String bef = "";
            String aft = "";
            if (i - 1 >= 0)
                bef = words[i - 1];
            if (i + 1 < words.length)
                aft = words[i + 1];
            int len = Math.max(getStartCount(words[i], bef), getStartCount(words[i], aft));
            if (len != words[i].length())
                len += 1;
            res += len;
        }
        System.out.println(res);
        return res;
	}
	
	public static int getStartCount(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        int res = 0;
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) break;
            res = i + 1;
        }
        return res;
    }
}
