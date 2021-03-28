// 연속된 부분합 -> 투포인터
// 연속되지 않은 부분합 -> 조합 or dfs (지금 위치를 선택하느냐 마느냐)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1182 {
    static int n, s, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());            
        }
        Arrays.sort(arr);
        
        boolean visit[] = new boolean[n];
        cnt = 0;
        for (int i = 1; i <= n; i++) {
            comb(arr, visit, 0, n, i);
        }
        System.out.println(cnt);
    }

    static void comb(int[] arr, boolean[] visit, int start, int n, int depth) {
        if (depth == 0) {
            if (getSum(arr, visit, n)) {
                cnt++;
            }
        }

        for (int i = start; i < n; i++) {
            visit[i] = true;
            comb(arr, visit, i + 1, n, depth - 1);
            visit[i] = false;
        }
    }

    static boolean getSum(int[] arr, boolean[] visit, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                sum += arr[i];
            }
        }
        if (sum == s) return true;
        return false;
    }
}

/*  조합이 아닌 dfs로 푸는 경우

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s, n;
    static int[] inputSubset;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        count = 0;

        inputSubset = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++){
            inputSubset[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        // count 합이 0인 경우 공집합도 포함되므로 그 수를 하나 빼주고 출력
        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }

    public static void dfs (int index, int sum){
        // dfs로 돌며 누적시키다가 index를 끝까지 탐색했을 때
        if (index == n) {
            // 지금까지의 총합이 목표값과 같을 때
            if (sum == s) {
                count++;
            }
            return;
        }
        // 현재 index 의 값을 선택하지 않는다.
        dfs(index + 1, sum);
        // 현재 index 의 값을 선택한다.
        dfs(index + 1, sum + inputSubset[index]);
    }
}

*/
