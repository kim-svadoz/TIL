import java.io.*;
import java.util.*;

public class p15683 {
    static BufferedReader br;
    static StringTokenizer st;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int n, m, map[][], copyMap[][];
    static boolean[][] visit;
    static List<Pair> cctv;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copyMap = new int[n][m];
        visit = new boolean[n][m];
        cctv = new ArrayList<>();

        int min = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv.add(new Pair(i, j));
                }
                // 빈 칸의 개수
                if (map[i][j] == 0) {
                    min ++;
                }
            }
        } 

        /*
            시프트 연산자는 2의 거듭제곱인 숫자를 빠르게 구할 때 유용하다.
            unsigned char num1 = 1;    //   1: 0000 0001

            printf("%u\n", num1 << 1);    //   2: 0000 0010: 2
            printf("%u\n", num1 << 2);    //   4: 0000 0100: 2^2
            printf("%u\n", num1 << 3);    //   8: 0000 1000: 2^3
            printf("%u\n", num1 << 4);    //  16: 0001 0000: 2^4
            printf("%u\n", num1 << 5);    //  32: 0010 0000: 2^5
            printf("%u\n", num1 << 6);    //  64: 0100 0000: 2^6
            printf("%u\n", num1 << 7);    // 128: 1000 0000: 2^7
        */
        
        // cctv가 k개일때는 0부터 4^k - 1 까지에 대해 방향을 정한다
        // 밑이 2의 지수일 때에 한해 특별하게 right shift를 이용할 수 있다.
        // 1<<(2*cctv.size()) 는 4의 cctv.size() 승이다.
        for (int tmp = 0; tmp < 1<<(2 * cctv.size()); tmp++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }
            // tmp를 4진법으로 해체하는 부분
            int brute = tmp;
            for (int i = 0; i < cctv.size(); i++) {
                int dir = brute % 4;
                brute /= 4;
                int x = cctv.get(i).x;
                int y = cctv.get(i).y;
                if (map[x][y] == 1) {
                    upd(x, y, dir);
                } else if (map[x][y] == 2) {
                    upd(x, y, dir);
                    upd(x, y, dir + 2);
                } else if (map[x][y] == 3) {
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                } else if (map[x][y] == 4) {
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                    upd(x, y, dir + 2);
                } else {
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                    upd(x, y, dir + 2);
                    upd(x, y, dir + 3);
                }
            }

            // 아직도 빈칸이라면 사각지대
            int val = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 0) {
                        val ++;
                    }
                }
            }
            min = Math.min(min, val);
        }
        System.out.println(min);
    }

    // 벽을 만날 때까지 지나치는 모든 빈 칸을 7로 만든다.
    static void upd(int x, int y, int dir) {
        dir %= 4;
        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(OOB(x, y) || copyMap[x][y] == 6) return;
            if(copyMap[x][y] != 0) continue;
            copyMap[x][y]= 7;
        }
    }
    
    // Out of Bounds
    static boolean OOB(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m);
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}