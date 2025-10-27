import java.util.*;

public class pg42747 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {3, 0, 6, 1, 5}));
    }

    // 0 1 3 5 6
    public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int  i = citations.length - 1; i >= 0; i--) {
            int smaller = Math.min(citations[i], citations.length - i);
            answer = Math.max(answer, smaller);
        }
        return answer;
    }
}
