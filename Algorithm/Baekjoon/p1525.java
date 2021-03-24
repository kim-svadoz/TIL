import java.io.*;
import java.util.*;

public class p1525 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[] visit;
    static HashMap<Integer, Integer> map;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // 2차원 퍼즐을 1차원으로 바꾸기
        int start = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) tmp = 9;
                start = (start * 10) + tmp;
            }
        }
        
        q = new LinkedList<>();
        map = new HashMap<>();
        
        q.offer(start);
        map.put(start, 0);
        
        System.out.println(bfs());       
    }
    
    static int bfs() {
        while (!q.isEmpty()) {
            // 처음 퍼즐의 상태를 문자열로 바꿔 빈공간의 위치를 찾는다.
            int cur = q.poll();
            String cur_str = String.valueOf(cur);
            
            if (cur_str.equals("123456789")) {
                return map.get(cur);
            }
            
            // 현재 빈 좌표
            int nine = cur_str.indexOf('9');
            // 몫 연산은 행, 나머지 연산은 열
            int r = nine / 3;
            int c = nine % 3;

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                
                if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
                StringBuilder next_str = new StringBuilder(cur_str);
                // nr * 3 + nc 는 좌표의 1차원 배열에서의 위치
                char tmp = next_str.charAt(nr * 3 + nc);
                // 이전 빈칸과 다음 위치의 숫자를 바꾼다.
                next_str.setCharAt(nr * 3 + nc, '9');
                next_str.setCharAt(nine, tmp);
                
                int next = Integer.parseInt(next_str.toString());
                
                // 맵에 없는 경우(나온 적 없는 퍼즐의 상태)에는 맵에 추가한다.
                if (!map.containsKey(next)) {
                    map.put(next, map.get(cur) + 1);
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}