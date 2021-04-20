/*
    구현 문제에서는 문제의 조건을 잘 읽고 예외처리를 확실히 하는 습관을 기르자.
*/
import java.io.*;
import java.util.*;
public class p1244 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        arr[0] = -1;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int stu = Integer.parseInt(br.readLine());
        for (int i = 0; i < stu; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            
            if (sex == 1) man(num);
            else woman(num);
        }
        
        show();
    }
    
    static void man(int num) {
        List<Integer> list= new ArrayList<>();
        for (int i = num; i <= n; i++) {
            if (i % num == 0) {
                list.add(i);
            }
        }
        for (int i : list) {
            if (arr[i] == 0) arr[i] = 1;
            else arr[i] = 0;
        }
    }
    
    static void woman(int num) {
        List<Integer> list = new ArrayList<>();
        int left = num - 1, right = num + 1;
        list.add(num);
        while (left > 0 && right <= n) {
            if (arr[left] == arr[right]) {
                list.add(left);
                list.add(right);
                left--;
                right++;
                continue;
            }
            break;
        }
        for (int i : list) {
            if (arr[i] == 0) arr[i] = 1;
            else arr[i] = 0;
        }
    }
    
    static void show() {
        StringBuilder sb = new StringBuilder();
        int col = n / 20;
        int row;
        for (int i = 0; i <= col; i++) {
            if (i != col) row = 20;
            else row = n % 20;
            for (int j = 1; j <= row; j++) {
                sb.append(arr[20 * i + j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}