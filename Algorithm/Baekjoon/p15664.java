/*
    이전 문제와 다른 점은
    비내림차순이어야 한다.

    *** 비내림차순은 start를 체크해주고 다음 i = start로 시작해서
    다음 dfs를 i로 돌면된다.
*/
import java.io.*;
import java.util.*;
public class p15664 {
    static int n, m;
    static int[] arr, result;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static LinkedHashSet<String> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        visit = new boolean[n];
        ans = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0, 0);
        ans.forEach(System.out::println);
    }
    static void dfs(int start, int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int p : result) {
                sb.append(p).append(" ");
            }
            ans.add(sb.toString());
            return;
        }
        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];
                dfs(i, depth + 1);
                visit[i] = false;
            }
        }
    }
}