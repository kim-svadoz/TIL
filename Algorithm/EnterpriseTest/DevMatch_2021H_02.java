import java.util.*;
public class DevMatch_2021H_02 {
    static int[][] copymap;
    public static void main(String[] args) throws Exception {
        int rows = 6;
        int columns = 6;
        int[][] queries = {
            {2,2,5,4},{3,3,6,6},{5,1,6,3}
        };
        solution(rows, columns, queries);

    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        copymap = new int[rows + 1][columns + 1];

        // 기본 맵 셋팅
        int q = 1;
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= columns; j++) {
                if (i == 0 || j == 0) {
                    copymap[i][j] = 0;
                    continue;
                }
                copymap[i][j] = q++;
            }
        }

        // 쿼리 등장 횟수만큼 rotate
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];

            answer[i] = rotate(rows, columns, x1, y1, x2, y2);
            System.out.println(answer[i]);
        }
        return answer;
    }

    static int rotate(int r, int c, int x1, int y1, int x2, int y2) {
        int[][] map = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] = copymap[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                // 왼쪽 위 꼭짓점
                if (i == x1 && j == y1) {
                    copymap[i][j] = map[i + 1][j];
                    min = Math.min(map[i][j], min);
                } else if (i == x1 && j == y2) {
                    copymap[i][j] = map[i][j - 1];
                    min = Math.min(map[i][j], min);
                } else if (i == x2 && j == y2) {
                    copymap[i][j] = map[i - 1][j];
                    min = Math.min(map[i][j], min);
                } else if (i == x2 && j == y1) {
                    copymap[i][j] = map[i][j + 1];
                    min = Math.min(map[i][j], min);
                } else if (i == x1 && y1 <= j && j <= y2) { // 가로로 오른쪽 하나 이동
                    copymap[i][j] = map[i][j - 1];
                    min = Math.min(map[i][j], min);
                } else if (j == y2 && x1 <= i && i <= x2) { // 세로로 아래로 이동
                    copymap[i][j] = map[i + 1][j];
                    min = Math.min(map[i][j], min);
                } else if (i == x2 && y1 <= j && j <= y2) { // 가로로 왼쪽 하나 이동
                    copymap[i][j] = map[i][j + 1];
                    min = Math.min(map[i][j], min);
                } else if (j == y1 && x1 <= i && i <= x2) { // 세로로 위로 하나 이동
                    copymap[i][j] = map[i + 1][j];
                    min = Math.min(map[i][j], min);
                }
            }
        }
        return min;
    }
}
