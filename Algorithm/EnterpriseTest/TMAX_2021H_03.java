import java.io.*;
import java.util.*;
public class TMAX_2021H_03 {
    public static void main(String[] args) throws IOException {
        int[][] monsters = {
            {2, 3},
            {4, 5},
            {3, -3},
            {2, -4},
            {3, -6},
            {-3, -3},
            {-5, -0},
            {-4, 4}
        };
        int[][] bullets = {
            {4, 1},
            {4, 6},
            {1, -2},
            {-4, -4},
            {-3, 0},
            {-4, 4},
        };
        solution(monsters, bullets);
    }

    static Map<Double, Integer> map = new HashMap<>();
    public static int solution(int[][] monsters, int[][] bullets) {
        int answer = 0;

        for (int[] monster : monsters) {
            double angle = Math.toDegrees(Math.atan2((double)monster[0], (double)monster[1]));
            if (map.containsKey(angle)) {
                map.put(angle, map.get(angle) + 1);
            } else {
                map.put(angle, 1);
            }
        }

        for (int[] bullet : bullets) {
            double angle = Math.toDegrees(Math.atan2((double)bullet[0], (double)bullet[1]));
            if (map.containsKey(angle)) {
                answer++;
                map.put(angle, map.get(angle) - 1);
            }
        }
        if (answer == 0) {
            answer = -1;
        }
        System.out.println(answer);

        return answer;
    }
}
