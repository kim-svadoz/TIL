/*
    공항
    union-find + greedy
    
    g번 게이트는 g번 이하의 게이트에만 도킹할 수 있다.
    만약 g번이 비어있다면 해당 게이트에 도킹하는 것이 최선이다.
    g번 게이트를 도킹할 수 없다면 g-1 게이트를 도킹하는 것이 최선.
    ... 0번까지 탐색 하여, 차선책이 0번을 가리킨다면 도킹 불가능 상태로 종료
*/
import java.io.*;
import java.util.*;
public class p10775 {
    static int g, p;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        for (int i = 0; i <= g; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < p; i++) {
            int num = Integer.parseInt(br.readLine());
            int empty = find(num);
            
            if (empty == 0) break;
            
            answer++;
            // 차선책을 계속해서 연결해준다.
            union(empty, empty - 1);
        }
        System.out.println(answer);
    }
    
    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            parent[u] = v;
        }
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        
        return parent[x] = find(parent[x]);
    }
}