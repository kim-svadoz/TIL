import java.io.IOException;
import java.util.*;

public class LINE_2021L_05 {
    public static void main(String[] args) throws IOException {
        String[] nicks = {
            "imhero111", "moneyman", "hero111", "imher1111", "hro111", "mmoneyman", "moneymannnn"
        };
        String[] emails = {
            "superman5@abcd.com", "batman432@korea.co.kr", "superman@abcd.com", "supertman5@abcd.com", "superman@erty.net", "batman42@korea.co.kr", "batman432@usa.com"
        };
        System.out.println(solution(nicks, emails));
    }
    

    public static int solution(String[] nicks, String[] emails) {
        int answer = -1;
        for (int i = 0; i < nicks.length; i++) {
            String nick = nicks[i];
            for (int j = i + 1; j < nicks.length - 1; j++) {

            }
        }
        return answer;
    }
}
