import java.io.*;
public class p10610 {
    static BufferedReader br;
    static String N;
    static int[] arr;
    static long len;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
       
        len = N.length();
        arr = new int[10];
        long total = 0;
        
        for (int i = 0; i < len; ++i) {
            int tNum = Integer.parseInt(N.substring(i, i + 1));
            arr[tNum] += 1;
            total += tNum;
        }

        if (!N.contains("0") || total % 3 != 0) {
            System.out.println("-1");
            return;
        }

        for(int i = 9; i >= 0; i--) {
            while(arr[i] > 0) {
                System.out.print(i);
                arr[i]--;
            }
        }
    }
}
