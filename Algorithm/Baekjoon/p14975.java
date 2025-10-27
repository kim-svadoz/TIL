/*
    단절점과 단절선
    그래프

    모든 간선은 달전선이다.
*/
import java.io.*;
import java.util.*;
public class p14975 {
    static class Edge {
        int u, v;
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    static int n, q;
    static List<List<Integer>> tree;
    static Edge[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        edges = new Edge[n];
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        
        
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
            edges[i] = new Edge(u, v);
        }
        
        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            boolean ret;
            if (t == 1) {
                ret = isCutVertex(k);
            } else {
                ret = true;
            }

            sb.append(ret ? "yes" : "no").append("\n");
        }
        System.out.println(sb.toString());
    }
    static boolean isCutVertex(int num) {
        int size = tree.get(num).size();
        return size > 1 ? true : false;
    }
}