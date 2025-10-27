/*
    홀짝 칵테일
*/
import java.io.*;
import java.util.*;
public class p21312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];
        boolean odd = false;
        int answer = 1;
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] % 2 != 0) {
                answer *= arr[i];
                odd = true;
            }
        }
        
        System.out.println(odd ? answer : arr[0]*arr[1]*arr[2]);
    }
}