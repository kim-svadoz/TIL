/**
 * BOJ 16953 A -> B 
 * Greedy, BFS or DP
 */
import java.io.*;
import java.util.*;

public class p16953 {
    static class Node {
        long num, cnt;
        public Node(long num, long cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        bfs(a, b);
    }
    static void bfs(int num, int target) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(num, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.num == target) {
                System.out.println(cur.cnt + 1);
                return;
            }
            if (cur.num > target) continue;
            q.add(new Node(cur.num * 2, cur.cnt + 1));
            q.add(new Node(cur.num * 10 + 1, cur.cnt + 1));
            //q.add(new Node((cur.num * 2) * 10 + 1, cur.cnt + 1));
            //q.add(new Node((cur.num * 10 + 1) * 2, cur.cnt + 1));
        }
        System.out.println("-1");
    }
}
