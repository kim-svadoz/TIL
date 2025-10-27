import java.io.*;
import java.util.*;

public class p3680 {
    static int t, max;
    static int[][] board;
    static int[][] power;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            board = new int[11][11];
            power = new int[11][11];
            visit = new boolean[11];
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            max = 0;
            dfs(0, 0, 0);
            System.out.println(max);
        }
    }
    
    static void dfs(int curr, int index, int sum) {
        if (index == 11) {
            max = Math.max(sum, max);
            return;
        }
        for (int position = 0; position < 11; position++) {
            if (!visit[position] && board[curr][position] > 0) {
                visit[position] = true;
                dfs(curr + 1, index + 1, sum + board[curr][position]);
                visit[position] = false;
            }
        }   
    }

}