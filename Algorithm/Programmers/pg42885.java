import java.util.*;

public class pg42885 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {70, 50, 80, 50}, 100));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int left = 0, right = people.length - 1;
        while (right >= left) {
            answer++;
            int left_val = people[left];
            int right_val = people[right--];
            if (left_val + right_val <= limit) {
                left++;
            }
        }

        return answer;
    }
}
