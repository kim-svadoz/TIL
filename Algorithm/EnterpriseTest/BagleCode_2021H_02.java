/**
 * 100점
 */
import java.util.*;

public class BagleCode_2021H_02 {
    public static void main(String[] args) {
        String catalogue = "200 2 5 \n 100 150 10 \n 110 150 15";
        System.out.println(solution(catalogue));
    }

    public static final int LIMIT = 1000100;
    public static int len;
    public static int n, k, d;

    public static int[] box;
    public static int[][] map;


    public static int solution(String catalogue) {
        init(catalogue);
        pro();
        return ret();
    }

    private static void init(String catalogue) {
        String[] str = catalogue.split("\\n");
        int len = str.length;
        for (int i = 0; i < len; i++) {
            str[i] = str[i].trim();
        }
        map = new int[len][3];
        for (int i = 0; i < len; i++) {
            String[] s = str[i].split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        n = map[0][0];
        k = map[0][1];
        d = map[0][2];
    }

    private static void pro() {
        box = new int[LIMIT];
        for (int i = 1; i <= k; i++) {
            int a = 0;
            // A번 보관함부터 B번 보관함까지 C의 간격으로
            for (int j = map[i][0]; j <= map[i][1]; j += map[i][2]) {
                if (a == d) {
                    break;
                }
                box[j]++;
                a++;
            }
        }
    }

    private static int ret() {
        int temp = d;
        for (int i = 0; i < LIMIT; i++) {
            if (box[i] == 0) continue;

            temp -= box[i];
            if (temp <= 0) {
                return i;
            }
        }
        return 0;
    }
    
}