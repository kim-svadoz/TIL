import java.util.*;

public class LineFintechServer_2021H_03 {
    public static void main(String[] args) {
        int n = 5;
        int k = 12;
        solution(n, k);
    }

    static int map[][];
    static int distance[][];
    public static int[] solution(int n, int k) {
        map = new int[n][n];
        distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], 0);
        }
        map[0][0] = 1;
        coloring(0, 0);
        map[n - 1][n - 1] = 2;
        
        for (int i = 1; i <= k; i++) {
            int[] seat = find();
            map[seat[0]][seat[1]] = i + 1; // i + 1번이 앉는다.
            if (i == k) {
                return new int[]{seat[0], seat[1]};
            }
        }

        int[] answer = {};
        return answer;
    }

    static void coloring(int r, int c) {
        distance[r][c] = 0;

        for (int i = 0; i < r; i++) {
            int d = Math.abs(r - i);
            for (int j = 0; j < c; j++) {
                d += Math.abs(c - j);
                distance[i][j] = d;
            }
        }
    }

    static int[] find() {
        int r = 0;
        int c = 0;

        return new int[]{r, c};
    }
}
