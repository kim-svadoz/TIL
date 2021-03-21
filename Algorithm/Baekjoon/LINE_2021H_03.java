import java.io.*;
import java.util.*;

public class LINE_2021H_03 {
    public static void main(String[] args) throws Exception {
        int[] enter = {1, 3, 2};
        int[] leave = {1, 2, 3};
        solution(enter, leave);
    }

    public static int[] solution(int[] enter, int[] leave) {
        int[] answer = {};
        int len = enter.length;
        answer = new int[len];

        // 먼저 들어간 사람이 늦게 나오면 반드시 만난다.
        for (int i = 0; i < len; i++) {
            int meetable = 0;
            // i번 보다 먼저 들어간 사람
            List<Integer> prevList = new ArrayList<>();

            // i 번이 들어간 index
            for (int j = 0; j < len; j++) {
                if(enter[j] == i + 1) {
                    break;
                }
                prevList.add(enter[j]);
            }

            // i 번보다 늦게 나간 사람
            List<Integer> postList = new ArrayList<>();
            for (int j = len - 1; j > 0; j--) {
                postList.add(j);
                if(leave[j] == enter[i]) {
                    break;
                }
            }

            for (int k = 0; k < postList.size(); k++) {
                for (int j = 0; j < prevList.size(); j++) {
                    int x = prevList.get(j);
                    if(postList.contains(x)) {
                        meetable++;
                        continue;
                    }
                    answer[x] = meetable;
                }
                
            }
            answer[i] = meetable;

        }

        return answer;
    }
    
}