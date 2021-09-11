import java.io.IOException;
import java.util.*;

public class LINE_2021L_01 {
    public static void main(String[] args) throws IOException {
        int[] student = {0, 1, 0, 0, 1, 1, 0};
        int k = 2;
        System.out.println(solution(student, k));
    }

    public static int solution(int[] student, int k) {
        int answer = 0;
        int len = student.length;
        for (int i = 0; i < len; i++) {
            List<Integer> list = new ArrayList<>();
            int tmp = k;

            for (int j = i; j < len; j++) {
                if (student[j] == 1) {
                    tmp--;
                }

                if (tmp == 0) {
                    answer++;
                    list.add(j);
                } else if (tmp < 0) {
                    break;
                }
            }
        }
        return answer;
    }
}
