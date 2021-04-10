import java.util.*;

public class pg12910 {
    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 10};
        int divisor = 5;
        solution(arr, divisor);
    }

    public static int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        for (int n : arr) {
            if (n % divisor == 0) {
                list.add(n);
            }
        }
        Collections.sort(list);

        if (list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
