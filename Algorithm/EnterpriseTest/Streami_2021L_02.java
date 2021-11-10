import java.math.BigInteger;
import java.util.*;

public class Streami_2021L_02 {
    public static void main(String[] args)  {
        String S = "011100";
        solution(S);
    }

    public static int solution(String S) {
        BigInteger big = new BigInteger(S, 2);
        long num = Long.parseLong(big.toString(2), 2); 
        int answer = 0;
        
        while (num != 0) {
            long n = num & 1; // odd or even
            if (n == 1) {
                num = subtractOne(num);
            } else {
                num = divideTwo(num);
            }
            answer++;
        }
        System.out.println(answer);
        return answer;
    }

    private static long subtractOne(long x) {
        long m = 1;

        while (!((x & m) > 0)) {
            x = x ^ m;
            m <<= 1;
        }
        x = x ^ m;
        return x;
    }

    private static long divideTwo(long x) {
        x = x >> 1;
        return x;
    }
}
