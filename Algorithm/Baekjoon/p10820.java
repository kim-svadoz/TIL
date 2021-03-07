import java.io.*;

public class p10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while ((s = br.readLine()) != null) {
            int[] res = new int[4];

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c >= 'A' && c <= 'Z') { // 대문자
                    res[1]++;
                } else if (c >= 'a' && c <= 'z') { // 소문자
                    res[0]++;
                } else if (c >= '0' && c <= '9') { // 숫자
                    res[2]++;
                } else {
                    res[3]++;
                }
            }

            for(int i = 0; i < 4; i++) {
                System.out.print(res[i]+" ");
            }
        }
    }
}
