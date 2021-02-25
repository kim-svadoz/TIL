package baekjoon;

import java.io.*;
import java.util.*;
public class BarnAssign_2188{
    static int N, M;
    static boolean[] visited;
    static int[][] edges;
    static int[] aMatch, bMatch;
    
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new int[N][M];
        for(int cowNum=0; cowNum<N; cowNum++){
            st = new StringTokenizer(br.readLine());
            int barnSize = Integer.parseInt(st.nextToken());
            for(int i=0; i<barnSize; i++){
                int barnNum = Integer.parseInt(st.nextToken())-1;
                edges[cowNum][barnNum] = 1;
            }
        }
        System.out.println(bipartiteMatching());
    }
    // 집합 A의 정점 a에서 B의 매칭되지 않은 정점으로 가는 경로를 찾는 함수
    public static boolean dfs(int a){
        if(visited[a]) return false;	// 재방문 cut. 매칭 수정 시 이 한줄의 코드가 어떻게 작용하는지 유의
        visited[a] = true;
        for(int b=0; b<M; ++b){
            if(edges[a][b]==1){
                //b가 아직 매칭되지 않을 때
                if(bMatch[b] == -1){
                    aMatch[a] = b;
                    bMatch[b] = a;
                    return true;
                }
                //b가 이미 매칭되어 있다면 bMatch[b]에서부터 시작해 경로를 찾는다. 찾았을 경우 재귀 호출한 부분에서 경로를 수정한다.
                if(dfs(bMatch[b])){
                    //dfs 호출(재귀호출)에서 경로를 수정했으니 b에 연결된 정점이 없으므로 연결해준다.
                    aMatch[a] = b;
                    bMatch[b] = a;
                    return true;
                }
            }
        }
        return false;
    }
    
    public static int bipartiteMatching(){
        aMatch = new int[N];
        bMatch = new int[M];
        Arrays.fill(aMatch, -1);
        Arrays.fill(bMatch, -1);
        
        int size = 0;
        for(int start = 0; start < N; start++){
            visited = new boolean[N];
            //증가경로, 즉 매칭을 찾은 경우 1의 유량을 증가시킨다.
            //증가 경로 찾을 때마다 +1?
            if(dfs(start)) ++size;
        }
        return size;
    }
}