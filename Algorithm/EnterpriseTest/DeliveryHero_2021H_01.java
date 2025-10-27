import java.util.*;

public class DeliveryHero_2021H_01 {
    public static void main(String[] args) {
        String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String C = "Example";
        System.out.println(solution(S, C));
    }

    public static String solution(String S, String C) {
        // write your code in Java SE 8
        StringBuilder answer = new StringBuilder();
        S = S.toLowerCase();
        S = S.replaceAll("[-]", "");
        C = C.toLowerCase();

        Map<String, Map<String, Integer>> name = new HashMap<>();
        String[] arr = S.split("; ");

        int len = arr.length;
        int i = 0;
        for (String s : arr) {
            i++;
            //System.out.println(s);
            StringBuilder email = new StringBuilder();
            StringBuilder l = new StringBuilder();
            String[] subArr = s.split(" ");
            int size = subArr.length;
            //System.out.println("size: "+size);

            String first = "";
            String last = "";
            if (size == 2) {
                first = subArr[0];
                last = subArr[1];
            } else if (size == 3) {
                first = subArr[0];
                last = subArr[2];
            }

            if (!name.containsKey(first)) {
                name.put(first, new HashMap<>());    
            }

            if (!name.get(first).containsKey(last)) {
                name.get(first).put(last, 1);
            } else {
                name.get(first).put(last, name.get(first).get(last) + 1);
            }

            int k = name.get(first).get(last);

            email.append(first).append(".");
            l.append(last);
            if (l.length() > 8) {
                l.setLength(8);
            }

            if (k > 1) {
                if (l.length() == 8) {
                    l.deleteCharAt(l.length() - 1);
                }
                l.append(k);
            }

            email.append(l);
            email.append("@").append(C).append(".com");

            answer.append(email);

            if (i < len) {
                answer.append("; ");
            }
        }

        return answer.toString();
    }
}
