import java.io.*;
import java.util.*;
public class TMAX_2021H_01 {
    public static void main(String[] args) throws IOException {
        int[][] atoms = {
            {80, 35},
            {70, 38},
            {100, 41},
            {75, 30},
            {160, 80},
            {77, 29},
            {181, 68},
            {151, 76}
        };
        solution(atoms);
    }

    public static int solution(int[][] atoms) {
        int answer = 0;

        boolean bad;
        boolean too_bad;

        // 총 몇일 사용했는지 카운트
        int cnt = 0;
        for (int[] today : atoms) {
            cnt++;

            bad = false;
            too_bad = false;
            int mise = today[0];
            int cho = today[1];
            if (mise >= 151 && cho >= 76) { // 둘다 매우나쁨
                too_bad = true;
                if (cnt == 1) answer++;
            } else if ((81 <= mise) || (36 <= cho)) { // 둘중 하나가 나쁨 이라면
                bad = true;
                if (cnt == 1) answer++;
            } else {
                cnt--;
            }

            if (too_bad || cnt >= 2) {
                cnt = 0;
            }
        }
        System.out.println(answer);
        return answer;
    }
}
