/*
    거짓말
    분리집합, union-find
*/
import java.io.*;
import java.util.*;
public class p1043 {
    static int n, m, knowTruthCnt;
    static int[] parent;
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n + 1];
        Arrays.fill(parent, -1);
        
        st = new StringTokenizer(br.readLine());
        knowTruthCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knowTruthCnt; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            parent[tmp] = tmp;
        }
        
        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
            
            if (list.get(i).size() >= 2) {
                doUnion(i);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < m; i++) {
            if (canLieInParty(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    
    // p 파티에서 거짓말을 할 수 있는지 체크
    static boolean canLieInParty(int p) {
        for (int k : list.get(p)) {
            int tmp = find(k);
            if (parent[tmp] == tmp) {
                // 해당 파티원 중 진실을 알고 있는 사람이 있는 경우
                return false;
            }
        }
        return true;
    }
    
    // 맨 처음 값과 하나씩 모두 union하면 같은 집합이 형성된다.
    static void doUnion(int s) {
        int first = list.get(s).get(0);
        
        for (int i = 1; i < list.get(s).size(); i++) {
            union(first, list.get(s).get(i));
        }
    }
    
    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (u == parent[u]) {
                parent[v] = u;
            } else {
                parent[u] = v;
            }
        }
    }
    
    static int find(int x) {
        if (parent[x] < 0 || x == parent[x]) return x;
        
        return parent[x] = find(parent[x]);
    }
}