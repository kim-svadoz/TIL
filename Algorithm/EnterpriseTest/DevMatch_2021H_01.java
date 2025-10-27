import java.util.*;
public class DevMatch_2021H_01 {
    public static void main(String[] args) throws Exception {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        solution(lottos, win_nums);

    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int n = 6;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (lottos[i] != 0) {
                list.add(lottos[i]);
            }
        }

        int min = 0;
        for (int i = 0; i < n; i++) {
            if (list.contains(win_nums[i])) {
                min++;
            }
        }

        int zeroCnt = n - list.size(); // 0의 개수 -> 다 맞다고 가정하므로 +해준다.
        int max = min + zeroCnt;

        for (int i = 0; i < answer.length; i++) {
            int x = 0;
            if (i == 0) {
                x = max;
            } else {
                x = min;
            }
            switch(x) {
                case 0:
                    answer[i] = 6;
                    break;
                case 1:
                    answer[i] = 6;
                    break;
                case 2:
                    answer[i] = 5;
                    break;
                case 3:
                    answer[i] = 4;
                    break;
                case 4:
                    answer[i] = 3;
                    break;
                case 5:
                    answer[i] = 2;
                    break;
                case 6:
                    answer[i] = 1;
                    break;
            }
        }
        return answer;
    }
}
