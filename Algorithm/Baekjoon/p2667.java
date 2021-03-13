import java.io.*;
import java.util.*;
public class p2667 {
    static int N, cnt, map[][], copymap[][];
    static boolean visit[][];
    static int[] dx = {-1 , 0, 1, 0};
    static int[] dy = {0 , 1, 0, -1};
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visit = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 1; j <= N; j++) {
                map[i][j] = input[j - 1] - '0';
            }
        }

        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    sum++;
                    cnt = 0;
                    dfs(i, j);
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);

        System.out.println(sum);
        for (int n : list) {
            System.out.println(n);
        }

    }

    static void dfs(int x, int y) {
        if (visit[x][y]) {
            return;
        }
        map[x][y] = 0;
        visit[x][y] = true;
        cnt++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx > 0 && ny > 0 && nx < N + 1 && ny < N + 1) {
                if (map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }

        return;
    }
}
