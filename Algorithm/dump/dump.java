public class Main{
    private static int[] parent;
    private static int[] cnt;

    public void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamRedaer(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
        HashMap<String, Integer> map;
        int testcase = Integer.parseInt(br.readLine());

        while(testcase-- > 0){
            int F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            cnt = new int[F*2 + 1];
            parent = new int[F*2 + 1];
            for(int i=1,index=1; i<=F; i++){
                st = new StringTokenizer(br.readLine());
                String[] strs = {st.nextToken(), st.nextToekn()};
                for(int j=0; j<2; j++){
                    if(!map.containsKey(strs[j])) {
                        cnt[index] = 1;
                        parent[index] = index;
                        map.put(strs[j], index++);
                    }
                }
                int u = map.get(strs[0]);
                int v = map.get(strs[1]);
                union(u, v);
                sb.append(cnt[find(u)]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static void union(int u, int v){
        u = find(u);
        v = find(v);
        if(u == v) return;
        parent[u] = v;
        cnt[v] += cnt[u];
    }
    public static void find(u){
        if(parent[u]==u) return u;
        return parent[u]=find(parent[u]);
    }
}