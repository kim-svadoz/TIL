/*
    SCC : Strongly Conneceted Component

    SCC를 구하기 위해
    1. 코사라주 알고리즘 과
    2. 타잔 알고리즘 을 이용한다.
*/
import java.io.*;
import java.util.*;
public class p2150 {
    static List<List<Integer>> graph;
    static List<List<Integer>> rgraph;
    static List<List<Integer>> res;
    static boolean[] visited;
    static Stack<Integer> stack;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<>();
        rgraph = new ArrayList<>();
        res = new ArrayList<>();
        
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
            res.add(new ArrayList<>());
        }
        
        // 단방향 인접리스트 구현 (방향 , 역방향 그래프)
        for (int i =  0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            rgraph.get(v).add(u);
        }
        
        visited = new boolean[V + 1];
        stack = new Stack<>();
        
        // 방향 그래프에 대해 dfs 수행
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        
        Arrays.fill(visited, false);
        int groupNum = 0;
        // 역방향 그래프에 대해 dfs 수행
        while (!stack.isEmpty()) {
            int start = stack.pop();
            
            // 스택에서 하나씩 꺼내면서!
            // 이 때 방문 체크가 된 것은 start가 하나의 SCC그룹에 속해있다는 뜻!
            if (visited[start]) {
                continue;
            }
            
            // 같은 그룹끼리(groupNum으로 분류) SCC를 분류한다.
            redfs(start, groupNum);
            groupNum++;
        }
        StringBuilder sb = new StringBuilder();
        // SCC 그룹 개수
        sb.append(groupNum + "\n");	
        
        // key를 기준으로 오름차순 정렬하기 위한 TreeMap 선언
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < groupNum; i++) {
            // 각각의 SCC 그룹에 대해 오름차순 정렬한다.
            Collections.sort(res.get(i));
            // key : SCC그룹의 첫째 항
            // value : index
            tm.put(res.get(i).get(0), i);
        }
        
        // map의 value를 이용하여 첫번째 항이 작은 순서대로 출력 (오름차순)
        tm.keySet().forEach(key -> {
            int value = tm.get(key);
            
            for (int now : res.get(value)) {
                sb.append(now + " ");
            }
            sb.append("-1\n"); // 문제조건에 따라 끝에 -1 붙이기
        });
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // 끝나는 점에 대해서 stack에 push
    static void dfs(int start) {
        visited[start] = true;
        
        for (int cur : graph.get(start)) {
            if (!visited[cur]) {
                dfs(cur);
            }
        }
        stack.push(start);
    }
    
    // 같은 SCC 그룹은 groupNum으로 분류한다.
    // 결과값을 담는 res 코드가 추가된다.
    static void redfs(int start, int groupNum) {
        visited[start] = true;
        res.get(groupNum).add(start);
        
        for (int cur : rgraph.get(start)) {
            if (!visited[cur]) {
                redfs(cur, groupNum);
            }
        }
    }
}

/* 타잔 알고리즘

import java.io.*;
import java.util.*;
public class p2150_ver2 {
    static List<List<Integer>> graph;
    static List<List<Integer>> res;
    static int cnt = 0, groupNum = 0;
    static int[] dfsn, sn;
    static boolean[] finished; // SCC가 확정된 정점 판별
    static Stack<Integer> stack;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        dfsn = new int[V + 1];
        sn = new int[V + 1];
        graph = new ArrayList<>();
        res = new ArrayList<>();
        
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
            res.add(new ArrayList<>());
        }
        
        // 단방향 인접리스트 구현 (방향 , 역방향 그래프)
        for (int i =  0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }
        
        finished = new boolean[V + 1];
        stack = new Stack<>();
        
        // 방향 그래프에 대해 dfs 수행
        for (int i = 1; i <= V; i++) {
            if (dfsn[i] == 0) {
                dfs(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        // SCC 그룹 개수
        sb.append(groupNum + "\n");	

        // key를 기준으로 오름차순 정렬하기 위한 TreeMap 선언
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < groupNum; i++) {
            // 각각의 SCC 그룹에 대해 오름차순 정렬한다.
            Collections.sort(res.get(i));
            // key : SCC그룹의 첫째 항
            // value : index
            tm.put(res.get(i).get(0), i);
        }

        // map의 value를 이용하여 첫번째 항이 작은 순서대로 출력 (오름차순)
        tm.keySet().forEach(key -> {
            int value = tm.get(key);
            
            for (int now : res.get(value)) {
                sb.append(now + " ");
            }
            sb.append("-1\n"); // 문제조건에 따라 끝에 -1 붙이기
        });
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // 각 정점에 대해 dfs 수행
    static int dfs(int start) {
        dfsn[start] = ++cnt; // 노드 마다 고유한 SCC 번호를 할당한다.
        stack.push(start); // 스택에 자기 자신을 삽입
        
        // 자신의 dfs, 자식들의 결과나 dfsn 중 가장 작은 번호를 result에 저장
        int parent = dfsn[start];
        for (int next : graph.get(start)) {
            // 아직 방문하지 않은 이웃에 대하여
            if (dfsn[next] == 0) {
                parent = Math.min(parent, dfs(next));
            } 
            // 방문은 했으나, 아직 SCC로 추출되지 않은 이웃
            else if (!finished[next]) {
                parent = Math.min(parent, dfsn[next]);
            }
        }

        // 부모노드가 자기 자신일 경우
        // 자신과 자신의 자손들이 도달 가능한 제일 높은 정점이 자신일 경우 SCC 추출
        if (parent == dfsn[start]) {
            while (true) {
                int t = stack.pop();
                finished[t] = true;
                sn[t] = groupNum;
                res.get(groupNum).add(t);
                
                if (t == start) break;
            }

            groupNum++;
        }
        
        // 자신의 부모값을 반환
        return parent;
    }
}

*/