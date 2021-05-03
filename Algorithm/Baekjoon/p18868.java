/*
    멀티버스1
    구현
    중복되지 않게 이중 포문을 돌리고 arr1과 arr2를 비교하여 같은 흐름인지 체크한다.
    같은 흐름일 때마다 answer++
*/
import java.io.*;
import java.util.*;
public class p18868 {
    static int m, n;
    static int[][] uni;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        uni = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                uni[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int i = 0; i < m-1; i++) {
            for (int j = i+1; j < m; j++) {
                if (check(uni[i], uni[j])) {
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }
    
    static boolean check(int[] arr1, int[] arr2) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr1[i] > arr1[j]) {
                    if (arr2[i] <= arr2[j]) return false;
                } else if (arr1[i] < arr1[j]) {
                    if (arr2[i] >= arr2[j]) return false;
                } else {
                    if (arr2[i] != arr2[j]) return false;
                }
            }
        }
        return true;
    }
}