import java.io.*;
import java.util.*;
public class LINE_PIN_2021H_01 {
    public static void main(String[] args) throws IOException {
        String inputString = "2349101";
        solution(inputString);
    }

    public static int solution(String inputString) {
        int answer = 10;
        int cnt = 0;
        int prev = Integer.parseInt(inputString.charAt(0)+"");
        for (int i = 1; i < inputString.length(); i++) {
            int cur = Integer.parseInt(inputString.charAt(i)+"");
            if (prev > cur && cur != 0) {
                cnt++;
            } else if (prev == cur) {
                cnt++;
            }

            prev = cur;
        }
        char lastChar = inputString.charAt(inputString.length() - 1);
        
        answer = answer * cnt + Integer.parseInt(lastChar+"");

        System.out.println(answer);
        return answer;
    }
}
