/*
    스티커가 나오면 바로 DP가 떠오르지만 이 문제는 아니다!
    시간이 넉넉하고 메모리도 넉넉하다 완전탐색이 제격인 문제로 보인다.

    - 스티커가 90도 회전 가능하지만 스티커의 사이즈가 주어진 형식으로 보아 모든 스티커가 직사각형의 자리를 차지하는 것을 알 수 있다. 
        따라서 하나의 스티커는 두 가지 방식으로 붙일수 있다.

    - 그렇다면 최악의 경우에 100개의 스티커중 2개를 골라 스티커 마다 2가지 방향으로 붙일 수 있으므로 100C2 * 2 * 2로 무난하게 시간제한을 통과할 수 있다.

    - 스티커를 붙일때 마다 돌리지말고 처음부터 붙일수있는 모든 형태의 스티커를 준비하면 편하다.
    
    - 그러나 90도 회전한 스티커는 원본 스티커와 같기 때문에 두 개의 스티커는 사용되었는지 확인하여 다시 사용하는 일이 없도록 해야한다. (방문체크)

    - 아래에 구현한 코드에서는 Node Class를 스티커의 표현과 시작점의 좌표를 담는데 사용하였다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16937 {
    static class Node {
        int id;
        int h, w;
        Node (int h, int w, int id) {
            this.h = h;
            this.w = w;
            this.id = id;
        }
    }
    static boolean[][] map;
    static List<Node> list;
    static boolean[] isUsed;
    static int h, w, n, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        map = new boolean[h][w];
        ans = 0;
        list = new ArrayList<>();
        isUsed = new boolean[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.add(new Node(h, w, i));
            list.add(new Node(w, h, i));
        }

        go(0, 0);
        System.out.println(ans);
    }

    static void go(int cnt, int size) {
        if (cnt == 2) {
            ans = size > ans ? size : ans;
            return;
        }

        for (Node sticker : list) {
            if(isUsed[sticker.id]) continue;

            Node start = findStart(sticker);

            if (start == null) continue;

            attach(start, sticker, true);
            isUsed[sticker.id] = true;
            go (cnt + 1, size + sticker.h * sticker.w);
            isUsed[sticker.id] = false;
            attach(start, sticker, false);
        }
    }

    static Node findStart(Node sticker) {
        for (int r = 0; r <= h - sticker.h; ++r) {
            for (int c = 0; c <= w - sticker.w; ++c) {
                if (map[r][c]) continue;

                boolean canAttach = true;
                for (int i = r; i < r + sticker.h; ++i) {
                    for (int j = c; j < c + sticker.w; ++j) {
                        if (map[i][j]) {
                            canAttach = false;
                            break;
                        }
                    }
                    if (!canAttach) break;
                }
                if (canAttach) return new Node(r, c, -1);
            }
        }
        return null;
    }

    static void attach(Node start, Node sticker, boolean type) {
        for (int r = start.h; r < start.h + sticker.h; ++r) {
            for (int c = start.w; c < start.w + sticker.w; ++c) {
                map[r][c] = type;
            }
        }
    }
}
