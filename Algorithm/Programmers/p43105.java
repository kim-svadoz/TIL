import java.util.*;
public class p43105 {
    public static void main(String[] args) {
        int[][] triangle = {
            {7},
            {3, 8},
            {8, 1, 0},
            {2, 7, 4, 4},
            {4, 5, 2, 6, 5}
        };
        solution(triangle);
    }

    public static int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);    
            }
        }

        for (int i = 0; i < size; i++) {
            list.add(triangle[size-1][i]);
        }

        answer = list.isEmpty() ? - 1 : Collections.max(list);
        System.out.println(answer);
        return answer;
    }
}
