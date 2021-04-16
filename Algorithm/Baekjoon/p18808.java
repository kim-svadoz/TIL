import java.io.*;
import java.util.*;

public class p18808 {
    static int n, m, k, r, c;
    static int[][] note;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        // 2차원 배열의 노트북, 종이의 크기는 넉넉히 잡아준다.
        note = new int[42][42];
        for (int idx = 0; idx < k; idx++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            paper = new int[12][12];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 회전한 모든 방향에 대해서 붙일 수 있는지, 없는지 탐색한다.
            for (int rot = 0; rot < 4; rot++) {
                boolean flag = false;
                for (int x = 0; x <= n - r; x++) {
                    if (flag) break;
                    for (int y = 0; y <= m - c; y++) {
                        if (attachAble(x, y)) {
                            flag = true;
                            break;
                        }
                    }
                }
                // 붙일 수 있다면 바로 빠져나간다.
                if (flag) break;

                // 이전 스티커는 붙일 수 없으므로 회전하고 다음을 체크한다.
                rotate();
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cnt += note[i][j];
            }
        }
        System.out.println(cnt);
    }

    // 스티커가 노트북의 x, y 좌표에 붙일 수 있는지 판단하는 함수
    static boolean attachAble(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (note[x + i][y + j] == 1 && paper[i][j] == 1) {
                    return false;
                }
            }
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (paper[i][j] == 1) {
                    note[x + i][y + j] = 1;
                }
            }
        }
        return true;
    }
    
    // 스티커를 회전하는 함수
    static void rotate() {
        // 기존에 있던 paper의 값을 tmp로 옮겨담았다가 다시 paper로 옮긴다. (90도 회전 구현)
        int tmp[][] = new int[12][12];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[i][j] = paper[i][j];
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                paper[i][j] = tmp[r-1-j][i];
            }
        }
        
        /*
        함수를 실행하기 전에 paper의 크기가 r × c인데 함수를 실행하고 나면 가로 세로를 바꿔서 c × r이 되어야 하니 r과 c를 바꿔야 하고
        t = r; r = c; c= t;와 같이 직접 swap을 구현
        */
        int t = r;
        r = c;
        c = t;
    }
}