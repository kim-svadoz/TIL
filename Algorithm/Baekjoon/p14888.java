/*
    처음엔 swtich를 사용해서 틀린 줄 알았지만 문제의 조건이 min, max가 각각 10억이므로
    int 타입이 아닌 long타입을 사용해야 한다.
*/
import java.io.*;
import java.util.*;

public class p14888 {
    static int n, arr[], bag[], result[];
    static long min, max;
    static boolean[] visit;
    static List<Integer> optionList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visit = new boolean[n];
        result = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        bag = new int[n - 1];
        optionList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j < cnt; j++) {
                optionList.add(i+1);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            bag[i] = optionList.get(i);
        }

        max = -1000000000;
        min = 1000000000;
        dfs(0);
        
        System.out.println(max);
        System.out.println(min);
    }

    // ex. 11234 의 모든 조합을 활용해서 연산자에 끼워넣자.
    // 단순히 모든 수열을 나열한 후 최대/최솟값을 구하면 된다.
    static void dfs(int depth) {
        if (depth == n - 1) {
            long sum = arr[0];
            for (int i = 0; i < n - 1; i++) {
                int x = result[i];
                if (x == 1) {
                    sum = sum + arr[i + 1];
                } else if (x == 2) {
                    sum = sum - arr[i + 1];
                } else if (x == 3) {
                    sum = sum * arr[i + 1];
                } else if (x == 4) {
                    if (sum < 0) {
                        sum = Math.abs(sum);
                        sum = -(sum / arr[i + 1]);
                    } else {
                        sum = sum / arr[i + 1];
                    }
                }
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = bag[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}
/************ 더 좋은 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int num[];
    static long minN = 100000000, maxN = -100000000;

    public static void main(String[] args) throws IOException {
        int op[];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n];

        for (int i = 0; i < n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        op = new int[4];

        for (int i = 0; i < 4; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        back(op, num[0], 0);
        System.out.println(maxN);
        System.out.println(minN);
    }

    public static void back(int[] op, long val, int cnt){
        if(cnt == n-1) { //남은 숫자가 없을때 가장 작은수와, 큰수 확인
            minN = Math.min(minN, val);
            maxN = Math.max(maxN, val);
            return;
        }

        if(op[0] > 0){
            op[0]--;
            back(op, val + num[cnt+1], cnt+1);
            op[0]++;
        }
        if(op[1] > 0){
            op[1]--;
            back(op, val - num[cnt+1], cnt+1);
            op[1]++;
        }
        if(op[2] > 0){
            op[2]--;
            back(op, val * num[cnt+1], cnt+1);
            op[2]++;
        }
        if(op[3] > 0){
            op[3]--;
            back(op, val / num[cnt+1], cnt+1);
            op[3]++;
        }

        return;
    }

}

*/