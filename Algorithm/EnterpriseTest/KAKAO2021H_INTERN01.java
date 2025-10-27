import java.io.*;
import java.util.*;

public class KAKAO2021H_INTERN01 {
    public static void main(String[] args) throws IOException {
        solution("one4seveneight");
    }
    static Map<String, Integer> map;
    static public int solution(String s) {
        map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        for (char c : s.toCharArray()) {
            System.out.println("c:"+c+", "+tmp.toString());
            if (Character.isDigit(c)) {
                sb.append(c);
                continue;
            }
            
            tmp.append(c);
            if (map.containsKey(tmp.toString())) {
                sb.append(map.get(tmp.toString()));
                tmp.setLength(0);
            }

        }

        int answer = Integer.parseInt(sb.toString());
        return answer;
    }

    
}
