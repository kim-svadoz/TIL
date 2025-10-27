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
        N = Integer.parseInt(st.nextToken()); // ���� ����
        M = Integer.parseInt(st.nextToken()); // ���� ���ϴ� ����
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
        
        // ť�� indegree�� 0�� ��� ���
        for(int i=1; i<=N; i++){
            if(indegree[i]==0){
                pq.offer(i);
            }
        }
        
        /*
        1. ť���� ���� ������ �ش� ��尡 ����Ű��(c2) ����� indegree�� 1�� ����
        2. ���� indegree�� 0�� �ȴٸ� q�� �ֱ�
        3. ť�� �� ������ �ݺ�
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