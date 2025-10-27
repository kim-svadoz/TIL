/*
    A를 B로
    Greedy + 구현
    str1의 맨 뒤에서부터 한글자 씩 str2의 맨 앞에서부터 차례차례 탐색한다.
*/
import java.io.*;
public class p13019 {
    static String str1, str2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        int size = str1.length(), cnt = 0;
        for (int i = 0; i < size; i++) {
            arr1[str1.charAt(i) - 'A']++;
            arr2[str2.charAt(i) - 'A']++;
        }


        for (int i = size - 1; i >= 0; i--) {
            if (str1.charAt(i) == str2.charAt(size - 1 - cnt)) {
                cnt++;
            }
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                flag = true;
                break;
            }
        }
        System.out.println(flag ? -1 : size - cnt);
        
    }
}