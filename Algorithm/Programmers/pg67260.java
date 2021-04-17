import java.util.*;
class pg67260 {
    public List<Integer>[] list;
    public int n;
    public int[] before, after;
    public boolean[] visit;
    public boolean solution(int n, int[][] path, int[][] order) {
        this.n = n;
        
        before = new int[n];
        after = new int[n];
        visit = new boolean[n];
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 그래프
        for (int i = 0; i < path.length; i++) {
            int u = path[i][0];
            int v = path[i][1];
            list[u].add(v);
            list[v].add(u);
        }
        
        // 탐험 순서
        for (int i = 0; i < order.length; i++) {
            before[order[i][1]] = order[i][0];
        }
        
        // 0을 먼저 들려야 하는 order가 있다면 false
        if (before[0] != 0) return false;
        visit[0] = true;
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < list[0].size(); i++) {
            q.offer(list[0].get(i));
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (visit[cur]) continue;
            // 먼저 들려야 하는데 아직 들리지 않았다면
            if (!visit[before[cur]]) {
                after[before[cur]] = cur;
                continue;
            }
            
            visit[cur] = true;
            for (int i : list[cur]) {
                q.offer(i);
            }
            q.offer(after[cur]);
        }
        
        for (int i = 0; i < n; i++) {
            if (!visit[i]) return false;
        }
        
        return true;
    }

}