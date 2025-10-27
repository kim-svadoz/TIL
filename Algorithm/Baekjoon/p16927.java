/*
    배열돌리기2
    p16926(배열돌리기1) 과 다른점은 요구하는 반복 횟수 r이 커짐에 따라 불필요한 회전이 있으므로
    시간을 더 단축하여 효율적인 코드를 작성해야 한다.
    따라서 각 테두리 변의 크기에 따라 반복 횟수를 수정하여 불필요한 회전 수를 줄인다.
*/

import java.io.*;
import java.util.*;

public class p16927 {
    static int N, M, R;
    // 시계반대방향으로 전진해야 하므로 dr, dc의 인덱스는 오 아 왼 위 순서
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 박스 (테두리)의 개수
        int cnt = Math.min(N, M) / 2;

        int n = N, m = M;

        // 반복문 돌 때마다 박스 1개가 r만큼 전진한다.
        for (int i = 0; i < cnt; i++) {
            rotate(i, 2*n + 2*m - 4);
            // 박스 안으로 들어갈 때마다 가로, 세로 2씩 줄어든다.
            n -= 2;
            m -= 2;
        }

        print();
    }

    static void rotate(int start, int len) {
        // len은 박스의 총 칸수.
        int rr = R % len;
        for (int i = 0; i < rr; i++) { // rr칸 전진

            int startVal = map[start][start];
            int r = start;
            int c = start;
            int dir = 0;

            while (dir < 4) {
                // map[nr][nc] 는 옮길 대상이다. (-> map[r][c]의 위치로!!)
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr == start && nc == start) break;
                if (nr < N - start && nc < M - start && nr >= start && nc >= start) {
                    // 차례대로 시계 반대방향으로 옮긴다.
                    map[r][c] = map[nr][nc];
                    r = nr;
                    c = nc;
                } else {
                    // 다음에 옮길 칸이 배열 밖으로 넘어가버리면 해당 라인은 다 옮긴 것이므로 다음 라인으로 넘어간다
                    dir++;
                }
            }
            // 처음에 시작지점 빼놨던 것은 마지막 빈자리에 넣어준다.
            map[start + 1][start] = startVal;
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}