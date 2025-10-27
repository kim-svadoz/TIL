import java.io.*;
import java.util.*;

public class p1913 {
    static int n, target;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());

        int tmp = n;
        int max = n * n;
        map = new int[n][n];
        int j = 0;
        int i = -1;
        int count = 1;
        while (true) {
            // 처음에 행부터 하나씩 작아지면서 숫자를 넣어준다.
            for (int t = 1;  t <= n; t++) {
                // 열 고정 행 변화 반복문
                i += count;
                map[i][j] = max;
                max--;
            }
            // 열고정 행 변화 반복문 후 반복횟수 하나씩 적어진다.
            n--;
            // 총 사이즈가 0보다 작거나 같으면 더이상 들어갈 자리가 없음.
            if (n <= 0) break;

            // 행 고정 열 변화 반복문
            for (int t = 1; t <= n; t++) {
                j += count;
                map[i][j] = max;
                max--;
            }
            // 한 루프가 다 돌면 행열이 감소하거나 증가하는 순서로 바뀐다 따라서 더해주는 값을 1 또는 -1로 변경한다
            count *= -1;
        }
        int[] ans = new int[2];
        for (int x = 0; x < tmp; x++) {
            for (int y = 0; y < tmp; y++) {
                // n의 값과 같은 위치를 저장
                if (target == map[x][y]) {
                    ans[0] = x + 1;
                    ans[1] = y + 1;
                }
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println(ans[0]+" "+ans[1]);
    }
}
/* 더 좋고 쉬운풀이

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n][n];
        
        int cnt = n*n;
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for(int i = 0; i <= n/2; i++) {
            int x = i;
            int y = i;
            int idx = 0;
            
            map[i][i] = cnt--;
            
            while(idx < 4) {
                int xx = x + dx[idx], yy = y + dy[idx];
                
                if(xx >= n || yy >= n || xx < 0 || yy < 0) {
                    idx++;
                }else if(map[xx][yy] != 0) {
                    idx++;
                }else {
                    map[xx][yy] = cnt--;
                    x = xx;
                    y = yy;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        int tx = 0, ty = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int v = map[i][j];
                if(v == t) {
                    tx = i;
                    ty = j;
                }
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        
        sb.append(tx+1).append(" ").append(ty+1);
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }   
}

*/