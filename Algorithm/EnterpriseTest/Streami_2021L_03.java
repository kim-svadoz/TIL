import java.util.*;

public class Streami_2021L_03 {
    public static void main(String[] args)  {
        int[] A = {3, 2, 1, 6, 5};
        int[] B = {4, 2, 1, 3, 3};
        solution(A, B);
    }

    public static int solution(int[] A, int[] B) {
        List<Integer> list = new ArrayList<>();
        int len = A.length;
        int max = 0;
        for(int i = 0; i < len; i++) {
            if (A[i] > B[i]) {
                if (!list.contains(B[i])) {
                    list.add(B[i]);
                }
            } else if (A[i] < B[i]) {
                if (!list.contains(A[i])) {
                    list.add(A[i]);
                }
            } else {
                max = Math.max(max, A[i]);
            }
        }
        if (list.size() == 0) return max + 1;

        Collections.sort(list);
        
        int answer = list.get(0);
        System.out.println(answer);
        return answer;
    }
}
