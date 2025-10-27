/*
	회사 문화 1
    dfs, 구현
*/
import java.io.*;
import java.util.*;
public class p14267 {
    static int n, m;
    static int[] cost;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cost = new int[n + 1];
        list = new ArrayList[n + 1];
        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 직속상사 관계 설정
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            // i의 직속 상사는 num;
            int num = Integer.parseInt(st.nextToken());
            // 직송상사num - 부하 i , 그래프 생성
            if (i != 1) list[num].add(i);
        }
        
        // 칭찬 리스트
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // parent가 칭찬을 받았다? -> parent의 직속 상사를 찾아서 해당하는 부하들을 전부 칭찬한다.
            // 부하 p가 칭찬 weight를 받았다.
            cost[p] += weight;
        }
        
        dfs(1);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(cost[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
    
    static void dfs(int start) {
        for (int next : list[start]) {
            cost[next] += cost[start];
            dfs(next);
        }
    }
}