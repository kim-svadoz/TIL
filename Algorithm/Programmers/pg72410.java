/*
    카카오 2021 블라인드 1번 신규 아이디 추천
    문자열 구현
*/
import java.util.*;
class pg72410 {
    boolean isValid (char c) {
        if (Character.isLetterOrDigit(c)) return true;
        if (c == '-' || c== '_' || c == '.') return true;
        
        return false;
    }
    public String solution(String new_id) {
        StringBuilder answer = new StringBuilder();
        boolean lastDot = false;
        for (char ch : new_id.toCharArray()) {
            if (isValid(ch) == false) continue;
            if (ch == '.') {
                if (answer.length() == 0 || lastDot) continue;
                lastDot = true;
            } else {
                lastDot = false;
            }
            
            ch = Character.toLowerCase(ch);
            answer.append(ch);
        }
        
        if (answer.length() > 15) {
            answer.setLength(15);
        }
        
        if (answer.length() == 0) {
            answer.append('a');
        }
        
        if (answer.charAt(answer.length() - 1) == '.') {
            answer.deleteCharAt(answer.length() - 1);
        }
        
        if (answer.length() < 3) {
            char ch = answer.charAt(answer.length() - 1);
            while (answer.length() < 3) {
                answer.append(ch);
            }
        }
        
        return answer.toString();
    }
}

/*   replaceAll과 패턴을 이용한 풀이

class Solution {
    public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]","");
        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}",".");
        temp = temp.replaceAll("^[.]|[.]$","");
        System.out.println(temp.length());
        if(temp.equals(""))
            temp+="a";
        if(temp.length() >=16){
            temp = temp.substring(0,15);
            temp=temp.replaceAll("^[.]|[.]$","");
        }
        if(temp.length()<=2)
            while(temp.length()<3)
                temp+=temp.charAt(temp.length()-1);

        answer=temp;
        return answer;
    }
}

*/
