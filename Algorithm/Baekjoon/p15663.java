/*
    이전 문제와 다른 점은
    자신을 제외한 모든 조합, 하지만 중복되는 수열은 여러 번 출력하면 안된다.

    출력값을 String으로 변경해 Set에 넣어 중복을 제거해야한다.

    💡 LinkedHashSet이 아닌 TreeSet을 사용하면 안되는 이유

    둘 다 정렬이 가능한 Set이라는 점은 동일하지만 LinkedHashSet은 입력순으로 정렬되고, TreeSet은 생성시 인자로 Comparator를 넘겨주지 않는다면 기본적으로 오름차순 정렬한다.

    따라서, TreeSet을 사용하면 String을 기준으로 오름차순 정렬하기 때문에 기존에 숫자가 작은순으로 오름차순 정렬했던 순서가 깨지게 된다.
    ex) "16", "135" → "135", "16"

*/
import java.io.*;
import java.util.*;
public class p15663 {
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
        dfs(0);
        ans.forEach(System.out::println);
    }
    static void dfs(int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int p : result) {
                sb.append(p).append(" ");
            }
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}