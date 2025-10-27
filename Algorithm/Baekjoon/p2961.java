/*
    문제 조건을 빠트리지 맙시다.
*/
import java.util.*;
import java.io.*;
public class p2961 {
    static int n, arr[][];
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 신 sour
            arr[i][1] = Integer.parseInt(st.nextToken()); // 쓴 bitter
        }
    
        answer = Long.MAX_VALUE;
        dfs(0, 1, 0);
        System.out.println(answer);
    }
    
    static void dfs(int index, long sour, long bitter) {
        if (index == n) {
            if (answer > Math.abs(sour - bitter) && bitter != 0) {  // 여기서 bitter != 0 이라는 조건을 추가하지 않아서 계속 실패를 맞았다.(하나도 사용 안한 경우)
                answer = Math.abs(sour - bitter);
            }
            return;
        }
        // 2^63승은 18자리 조금 넘으므로 log한 값이 17자리 이상이면 계산해도 의미가 없으므로 제외한다.
        if (Math.log10(sour) > 17) {
            return;
        }
        
        // 첫번째 인덱스부터 계속 증가하면서 탐색하기 때문에 visit배열도 필요가 없다. -> 오히려 더 빨라짐을 확인
        dfs(index + 1, sour * arr[index][0], bitter + arr[index][1]);
        dfs(index + 1, sour, bitter);
    }

}

/* 비트마스킹을 이용한 풀이

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = 1000000000;
    static int[][] sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sb = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb[i][0] = Integer.parseInt(st.nextToken());
            sb[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < 1<<N; i++) {
            int S = 1, B = 0;
            for (int j = 0; j < N; j++) {
                if ((i & 1<<j) > 0) {
                    S *= sb[j][0];
                    B += sb[j][1];
                }
            }
            ans = Math.min(ans, Math.abs(S-B));
        }

        System.out.println(ans);
    }
}


*/