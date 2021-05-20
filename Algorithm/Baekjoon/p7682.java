/*
	틱택토
	해당 배치가 최종적으로 끝날 수 있는 상태인지 판별

	규칙을 싹 다 찾아보자.
*/
import java.io.*;
import java.util.*;
public class p7682 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("end")) break;

            map = new char[3][3];
            for (int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = line.charAt(i);

            }

            if (test(map)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
            
        }
    }

    static boolean test(char[][] map) {
        boolean ret = false;
        int oCnt = 0, xCnt = 0, empty = 0;
        for (int i = 0; i < 9; i++) {
            if (map[i / 3][i % 3] == 'X') {
                xCnt++;
            } else if (map[i / 3][i % 3] == 'O') {
                oCnt++;
            } else {
                empty++;
            }
        }

        if (xCnt + oCnt == 9) {
            if (xCnt - 1 != oCnt || isValid(map, 'O')) {
                return false;
            }
            return true;
        } else {
            // O로 끝나야함
            if (xCnt == oCnt) {
                boolean isO = isValid(map, 'O');
                boolean isX = isValid(map, 'X');
                if (isO && !isX) {
                    return true;
                } else {
                    return false;
                }
            } 
            // X로 끝나야 함
            else if (xCnt - 1 == oCnt) {
                boolean isO = isValid(map, 'O');
                boolean isX = isValid(map, 'X');
                if (!isO && isX) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return ret;
    }

    static boolean isValid(char[][] map, char c) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) {
                return true;
            }
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) {
                return true;
            }
        }

        // 대각선
        if (map[0][0] ==  c && map[1][1] == c && map[2][2] == c) {
            return true;
        }
        if (map[0][2] ==  c && map[1][1] == c && map[2][0] == c) {
            return true;
        }

        return false;
    }
}