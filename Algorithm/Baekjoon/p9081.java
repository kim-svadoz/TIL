/*
    단어 맞추기
    next permutation
*/
import java.io.*;
import java.util.*;
public class p9081 {
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(next(br.readLine())).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    public static String next(String s){
        if (s.length() == 1) return s;
        
        int min = 0;
        int idx = 0;
        
        // 뒤에서부터 탐색하면서 오름차순이 깨지는 인덱스를 확인(idx)
        loop:
        for (idx = s.length() - 2; idx >= 0; idx--) {
            // 다시 뒤에서부터 탐색하면서 idx보다 큰 첫번째 인덱스 를 확인(min)
            for (min = s.length() - 1; min > idx; min--) {
                if (s.charAt(idx) < s.charAt(min)) {
                    break loop;
                }
            }
        }
        if (idx == -1) {
            return s;
        }
        
        // min과 idx를 스왑
        char[] arr = s.toCharArray();
        char tmp = arr[min];
        arr[min] = arr[idx];
        arr[idx] = tmp;
        
        // idx에서부터 끝까지 오름차순 정렬
        Arrays.sort(arr, idx + 1, arr.length);
        
        // String으로 return
        StringBuilder sb = new StringBuilder();
        for (char a : arr) {
            sb.append(a);
        }
        
        return sb.toString();
    }
}