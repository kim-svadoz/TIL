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
    // ���� A�� ���� a���� B�� ��Ī���� ���� �������� ���� ��θ� ã�� �Լ�
    public static boolean dfs(int a){
        if(visited[a]) return false;	// ��湮 cut. ��Ī ���� �� �� ������ �ڵ尡 ��� �ۿ��ϴ��� ����
        visited[a] = true;
        for(int b=0; b<M; ++b){
            if(edges[a][b]==1){
                //b�� ���� ��Ī���� ���� ��
                if(bMatch[b] == -1){
                    aMatch[a] = b;
                    bMatch[b] = a;
                    return true;
                }
                //b�� �̹� ��Ī�Ǿ� �ִٸ� bMatch[b]�������� ������ ��θ� ã�´�. ã���� ��� ��� ȣ���� �κп��� ��θ� �����Ѵ�.
                if(dfs(bMatch[b])){
                    //dfs ȣ��(���ȣ��)���� ��θ� ���������� b�� ����� ������ �����Ƿ� �������ش�.
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
            //�������, �� ��Ī�� ã�� ��� 1�� ������ ������Ų��.
            //���� ��� ã�� ������ +1?
            if(dfs(start)) ++size;
        }
        return size;
    }
}