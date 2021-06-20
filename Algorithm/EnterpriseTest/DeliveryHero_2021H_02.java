import java.util.*;

public class DeliveryHero_2021H_02 {
    public static void main(String[] args) {
        String S = "  root r-x delete-this.xls\nroot r-- bug-report.pdf\nroot r-- podcast.flac\nalice r-- system.xls\nroot --x invoices.pdf\nadmin rwx SETUP.PY\nalice r-- root.pdf";
        System.out.println(solution(S));
    }

    public static String solution(String S) {
        // write your code in Java SE 8
        StringBuilder answer = new StringBuilder();
        int min = Integer.MAX_VALUE;
        String arr[] = S.split("\n");
        for (String s : arr) {
            String rootArr[] = s.split("root");
            int size = rootArr.length;
            if (size == 1) continue;
            String subArr[] = rootArr[1].split(" ");
            if (subArr.length != 3) continue;

            if (subArr[1].charAt(0) == 'r' && subArr[1].charAt(1) == '-') {
                if (subArr[2].split("\\.")[0].indexOf("root") > 0 && size == 1) continue;

                String extension = subArr[2].split("\\.")[1];
                if (extension.equals("xls") || extension.equals("doc") || extension.equals("pdf")) {
                    min = Math.min(min, subArr[2].length());
                }
            }
        }

        answer.append(min);
        return answer.toString();
    }
}
