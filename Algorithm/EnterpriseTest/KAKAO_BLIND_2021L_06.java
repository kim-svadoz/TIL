import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_06 {
    public static void main(String[] args) throws IOException {

    }    

    static int[][] map;
    static int n, m;
    public static int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        //map = new int[n + 1][m + 1];
        map = board;

        for (int[] s : skill) {
            int type = s[0];
            if (type == 1) { // 공격
                attack(s[1], s[2], s[3], s[4], s[5]);
            } else if (type == 2) { // 회복
                defense(s[1], s[2], s[3], s[4], s[5]);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static void attack(int r1, int c1, int r2, int c2, int power) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                map[i][j] -= power;
            }
        }
    }

    static void defense(int r1, int c1, int r2, int c2, int power) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                map[i][j] += power;
            }
        }
    }

}
