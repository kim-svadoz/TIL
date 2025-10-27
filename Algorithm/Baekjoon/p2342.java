/*
	Dance Dance Revolution
	DP
	dp[idx][i][j] := idx번째 순서에서 왼쪽 발이 i, 오른쪽 발이 j에 위치하고 있을때 드는 힘
*/

// Bottom-Up (시간복잡도 더 Good)
import java.io.*;
import java.util.*;
public class p2342 {
    public static int solve(int l, int r) {
        if (l == r) return 1;
        else if (l == 0) return 2;
        else if (Math.abs(l - r) == 2) return 4;
        else return 3;
    }

    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        list.add(0);
        while (true) {
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            
            list.add(k);
        }

        int size = list.size();
        int[][][] dp = new int[size][5][5];

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[k][i][j] = INF; //[DDR단계][왼발위치][오른발위치] 일 때까지 소모한 최소의 합
                }
            }
        }
        dp[0][0][0] = 0;
        
        for (int idx = 0; idx < size - 1; idx++) {
            // 현재 i단계까지 결과를 잘 얻었다고 하자 난 i+1단계를 가고 싶다.
            // 내가 밟아야할 DDR의 번호는 i+1이다.
            // 다음 단계로 진행할 때 모든 경우를 생각해보자
            int next = list.get(idx + 1);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int now = dp[idx][i][j];

                    if (next != j) {
                        dp[idx + 1][next][j] = Math.min(dp[idx + 1][next][j], now + solve(i, next));
                    }

                    if (next != i) {
                        dp[idx + 1][i][next] = Math.min(dp[idx + 1][i][next], now + solve(j, next));
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        // i번째 단계에서 최대가 되는 것이 정답!
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                res = Math.min(res, dp[size-1][i][j]);
            }
        }
        System.out.println(res);
    }
}

/* Top-Down
import java.util.*;
import java.io.*;
public class p2342 {
    static int[][][] dp;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            
            list.add(k);
        }
        dp = new int[list.size()][5][5];
        int ans = go(0, 0, 0);
        System.out.println(ans);
    }
    
    static int go(int idx, int left, int right) {
        if (idx == list.size()) return 0;
        if (dp[idx][left][right] != 0) {
            return dp[idx][left][right];
        }
        int leftFoot = go(idx + 1, list.get(idx), right) + solve(left, list.get(idx));
        int rightFoot = go(idx + 1, left, list.get(idx)) + solve(right, list.get(idx));
        dp[idx][left][right] = Math.min(leftFoot, rightFoot);

        return dp[idx][left][right];
    }
    
    static int solve(int i, int j) {
        int ret = 0;
        if (i == 0) ret = 2;
        else if (i == j) ret = 1;
        else if (Math.abs(i - j) == 2) ret = 4;
        else ret = 3;
        
        return ret;
    }
}

*/
