import java.io.*;

public class p17609 {
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
            String input = br.readLine();

            if (isPalindrome(input)) {
                sb.append(0).append("\n");
                continue;
            } else if (isSeudoPalindrome(input)) {
                sb.append(1).append("\n");
                continue;
            } else {
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static boolean isPalindrome(String str) {
        int s = 0, e = str.length() - 1;
        
        while (s <= e) {
            if (str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
        return true;
    }

    static boolean isSeudoPalindrome(String str) {
        int s = 0, e = str.length() - 1;

        while (s <= e) {
            if (str.charAt(s) != str.charAt(e)) {
                return isPalindrome(str.substring(s + 1, e + 1)) | isPalindrome(str.substring(s, e));
            }

            s++;
            e--;
        }

        return true;
    }
}
