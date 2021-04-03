import java.io.*;
import java.util.*;

public class LINE_2021H_02 {
    public static void main(String[] args) throws Exception{
        String inp_str = "AaTa+!12-3";
        solution(inp_str);
    }

    public static int[] solution(String inp_str) {
        int[] answer = {};
        List<Integer> answer_list = new ArrayList<>();

        int length = inp_str.length();

        if (length < 8 || length > 15) {
            answer_list.add(1);
        }
        
        char[] charArr = inp_str.toCharArray();
        List<Integer> category = new ArrayList<>();
        for (char c : charArr) {
            if (c >= '0' && c <= '9') {
                if (!category.contains(1)) {
                    category.add(1);
                }
                continue;
            } else if (c >= 'a' && c <= 'z') {
                if (!category.contains(2)) {
                    category.add(2);
                }
                continue;
            }else if (c >= 'A' && c <= 'Z') {
                if (!category.contains(3)) {
                    category.add(3);
                }
                continue;
            } else if (c == '~' || c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*') {
                if (!category.contains(4)) {
                    category.add(4);
                }
                continue;
            } else {
                if(!answer_list.contains(2)) {
                    answer_list.add(2);
                }
            }
            
        }
        if (category.size() < 3) answer_list.add(3);
        
        char c = charArr[0];
        int contiCnt = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == c) {
                contiCnt ++;
            } else {
                contiCnt = 1;
                c = charArr[i];
            }

            if(contiCnt == 4) {
                answer_list.add(4);
                break;
            }
        }

        Arrays.sort(charArr);
        int sameCnt = 0;
        char ch = charArr[0];
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == ch) {
                sameCnt ++;
            } else {
                ch = charArr[i];
                sameCnt = 1;
            }

            if(sameCnt == 5) {
                answer_list.add(5);
                break;
            }
        }

        if (answer_list.size() == 0) {
            answer = new int[1];
            answer[0]  = 0;
        } else {
            answer = new int[answer_list.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = answer_list.get(i);
            }
        }

        return answer;
    }
}