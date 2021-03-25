import java.io.*;
import java.util.*;

public class p3108 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, cnt;
    static Rectangle[] rec;
    static boolean[] visit;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        rec = new Rectangle[n + 1];
        rec[0] = new Rectangle(0, 0, 0, 0);
        visit = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());           
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            rec[i] = new Rectangle(x1, y1, x2, y2);
        }
        
        cnt = 0;
        for (int i = 0; i <= n; i++) {
            if (visit[i]) continue;
            
            visit[i] = true;
            q.add(i);
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                
                for (int j = 0; j <= n; j++) {
                    if (cur == j || !check(cur, j) || visit[j]) continue;
                    
                    visit[j] = true;
                    q.add(j);
                }
            }
            cnt++;
        }
        System.out.println(cnt - 1);
    }
	
    static boolean check(int cur, int next) {
        Rectangle c = rec[cur];
        Rectangle n = rec[next];
        
        if ((c.x1 < n.x1 && n.x2 < c.x2 && c.y1 < n.y1 && n.y2 < c.y2) || 
           (c.x1 > n.x1 && n.x2 > c.x2 && c.y1 > n.y1 && n.y2 > c.y2) ||
           c.x2 < n.x1 || c.x1 > n.x2 || c.y2 < n.y1 || c.y1 > n.y2) return false;
        
        return true;
    }
}

class Rectangle {
    int x1, y1, x2, y2;
    Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
