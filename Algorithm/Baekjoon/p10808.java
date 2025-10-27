import java.io.*;
import java.util.*;

public class p10808 {
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        int[] alpha = new int[26];

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch(c) {
                case 'a':
                    alpha[0]++;
                    break;
                case 'b':
                    alpha[1]++;
                    break;
                case 'c':
                    alpha[2]++;
                    break;
                case 'd':
                    alpha[3]++;
                    break;
                case 'e':
                    alpha[4]++;
                    break;
                case 'f':
                    alpha[5]++;
                    break;
                case 'g':
                    alpha[6]++;
                    break;
                case 'h':
                    alpha[7]++;
                    break;
                case 'i':
                    alpha[8]++;
                    break;
                case 'j':
                    alpha[9]++;
                    break;
                case 'k':
                    alpha[10]++;
                    break;
                case 'l':
                    alpha[11]++;
                    break;
                case 'm':
                    alpha[12]++;
                    break;
                case 'n':
                    alpha[13]++;
                    break;
                case 'o':
                    alpha[14]++;
                    break;
                case 'p':
                    alpha[15]++;
                    break;
                case 'q':
                    alpha[16]++;
                    break;
                case 'r':
                    alpha[17]++;
                    break;
                case 's':
                    alpha[18]++;
                    break;
                case 't':
                    alpha[19]++;
                    break;
                case 'u':
                    alpha[20]++;
                    break;
                case 'v':
                    alpha[21]++;
                    break;
                case 'w':
                    alpha[22]++;
                    break;
                case 'x':
                    alpha[23]++;
                    break;
                case 'y':
                    alpha[24]++;
                    break;
                case 'z':
                    alpha[25]++;
                    break;
            }
        }

        for(int i = 0; i < alpha.length; i++) {
            System.out.print(alpha[i]+" ");
        }
    }
}
