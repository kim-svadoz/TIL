/*
    십자뒤집기
    bitmask + bfs

    3x3 크기의 보드를 1자로 펼치면 000 000 000 의 형태가 나오므로 각 칸의 상태를 bit로 나타낼 수 있다.
    각 칸을 클릭했을 때 바뀌는 좌표들을 미리 할당하여, 현재 비트와 xor을 수행하면 십자로 뒤집은 상태를 구현할 수 있다.
    
    이를 활용해 너비 우선 탐색을 사용해 0~8번째 칸을 하나씩 뒤집어 보면서 주어진 보드의 상태와 같아질 때 뒤집은 횟수를 리턴하면 된다.
*/
import java.io.*;
import java.util.*;
public class p10472 {
    static int t;
    static int[] click = {
        416, // 110 100 000
        464, // 111 010 000
        200, // 011 001 000
        308, // 100 110 100
        186, // 010 111 010
        89,  // 001 011 001
        38,  // 000 100 110
        23,  // 000 010 111
        11	 // 000 001 011
    };
    static Queue<Integer> q;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            map = new char[3][3];
            int board = 0;
            for (int i = 0; i < 3; i++) {
                String line = br.readLine();
                for (int j = 0; j < 3; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            
            for (int i = 0; i < 9; i++) {
                board |= ((map[i / 3][i % 3] == '*') ? 1 : 0) << (8 - i);
            }
            // 주어진 board의 bit랑 같게 만들어라.
            System.out.println(answer(board));
        }
    }
    
    static int answer(int n) {
        q = new LinkedList<>();
        int dist = 0;
        boolean[] visited = new boolean[1 << 9];
        q.add(0);
        visited[0] = true;
        
        while (!q.isEmpty()) {
            int qs = q.size();
            while (qs-- > 0) {
                int cur = q.poll();
                if (cur == n) return dist;
                for (int i = 0; i < 9; i++) {
                    // next : 십자가 모양으로 뒤집은(XOR) 다음의 비트
                    int next = cur ^ click[i];
                    if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
            ++dist;
        }
        return dist;
    }
}