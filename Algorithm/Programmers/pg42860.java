import java.util.*;

public class pg42860 {
    public static void main(String[] args) {
        System.out.println(solution("JAN"));
    }

    public static int solution(String name) {
        int answer = 0;
        int n = name.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = name.charAt(i) - 'A';
        }

        int move = n - 1;
        for (int i = 0; i < n; i++) {
            int smaller = arr[i] > 26 - arr[i] ? 26 - arr[i] : arr[i];
            answer += smaller;
            System.out.println("smaller::: "+smaller);

            int next = i + 1;
            while (next < n && arr[next] == 0) {
                next++;
            }
            move = Math.min(move, i + n - next);
        }
        answer += move;
        return answer;
    }
}
