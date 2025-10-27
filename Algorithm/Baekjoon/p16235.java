/*
    구현, 시뮬레이션
*/
import java.io.*;
import java.util.*;

public class p16235 {
    static class Tree implements Comparable<Tree>{
        int r, c, age;
        public Tree (int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        public int compareTo(Tree o) {
            return age - o.age;
        }
    }
    static int n, m, k;
    static int[][] map, A;
    static Queue<Tree> trees;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        trees = new LinkedList<>();
        map = new int[n + 1][n + 1];
        A = new int[n + 1][n + 1];
        // 초기 양분 5 세팅
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(r, c, age));
        }

        Collections.sort((List<Tree>) trees);

        // 사계절 시작
        int answer = process();
        System.out.println(answer);
    }

    static int process() {
        while (k-- > 0) {
            springToSummer();
            fall();
            winter();
        }
        return trees.size();
    }

    // 봄에는 어린 나무부터 자신의 나이만큼 양분을 먹고, 나이 1 증가
    static void springToSummer() {
        List<Tree> dieTree = new ArrayList<>();

        int size = trees.size();
        while (size-- > 0) {
            Tree cur = trees.poll();

            // 자신의 나이보다 많은 양분을 가지고 있을 때
            if (map[cur.r][cur.c] >= cur.age) {
                map[cur.r][cur.c] -= cur.age;
                trees.add(new Tree(cur.r, cur.c, cur.age + 1));
            } else { // 땅에 양분이 부족해 죽는다.
                dieTree.add(new Tree(cur.r, cur.c, cur.age / 2));
            }
        }

        for (Tree t : dieTree) {
            map[t.r][t.c] += t.age;
        }
    }
    
    // 가을 - 나무 번식
    static void fall() { 
        List<Tree> parents = new ArrayList<>();
        int size = trees.size();
        while (size-- > 0) {
            Tree cur = trees.poll();
            if (cur.age % 5 == 0) {
                for (int dir = 0; dir < 8; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];
                    if (OOB(nr, nc)) continue;

                    trees.add(new Tree(nr, nc, 1));
                }
            }
            parents.add(cur);
        }
        // 순서 상 아기 나무들이 먼저 Queue에 들어간 후 부모나무를 Queue에 추가한다.
        for (Tree t : parents) {
            trees.add(t);
        }
    }

    // 겨울 - 양분 추가    
    static void winter() {
        // 겨울 - 양분 추가한다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n ; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r < 1 || c < 1 || r > n || c > n;
    }
}
