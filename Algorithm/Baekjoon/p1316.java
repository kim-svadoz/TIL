import java.io.*;
import java.util.*;
public class p1316 {
    static int n;
    static List<Character> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int cnt = n;
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            char[] cArr = input.toCharArray();
            list = new ArrayList<>();
            for (int j = 0; j < cArr.length; j++) {
                if (j == 0) continue;
                if (cArr[j] != cArr[j - 1]) {
                    list.add(cArr[j - 1]);
                    if (list.contains(cArr[j])) {
                        cnt--;
                        break;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}