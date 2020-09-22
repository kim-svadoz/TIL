package baekjoon;
import java.util.*;
import java.io.*;

class SaveJoint_2042 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<List<Integer>> array;
    static int[] indegree;
    static int N, M;
        
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 갯수
        M = Integer.parseInt(st.nextToken()); // 문제 비교하는 갯수
        indegree = new int[N+1];
        array = new ArrayList<List<Integer>>();
        
        for(int i=0; i<N+1; i++){
            array.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            
            array.get(v1).add(v2);
            indegree[v2]++;
        }
        topologicalSort(indegree, array);
    }
    
    public static void topologicalSort(int[] indegree, List<List<Integer>> graph){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        // 큐에 indegree가 0인 노드 담기
        for(int i=1; i<=N; i++){
            if(indegree[i]==0){
                pq.offer(i);
            }
        }
        
        /*
        1. 큐에서 값을 꺼내며 해당 노드가 가리키는(c2) 노드의 indegree를 1씩 감소
        2. 만약 indegree가 0이 된다면 q에 넣기
        3. 큐가 빌 때까지 반복
        */
        while(!pq.isEmpty()){
            int node = pq.poll();
            for(Integer i : graph.get(node)){
                indegree[i]--;
                
                if(indegree[i]==0){
                    pq.offer(i);
                }
            }
            System.out.print(node+" ");
        }
    }
}