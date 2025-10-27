/*
    8진수를 10진수로 바꾼 뒤 2진수로 변환해보자.
*/
import java.io.*;

public class p1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] binaryOctalArary = {"000", "001", "010", "011", "100", "101", "110", "111"};

        String s = br.readLine();
        int len = s.length();
        if (s.equals("0") && len == 1) {
            sb.append(0);
        } else {
            for (int i = 0; i < len; i++) {
                int n = Character.getNumericValue(s.charAt(i));

                if (i == 0 && n < 4) {
                    switch (n) {
                    case 1:
                        sb.append("1");
                        break;
                    case 2:
                        sb.append("10");
                        break;
                    case 3:
                        sb.append("11");
                        break;
                    }
                } else {
                    sb.append(binaryOctalArary[n]);
                }
            }
        }

        System.out.println(sb.toString());
    }
    
}
