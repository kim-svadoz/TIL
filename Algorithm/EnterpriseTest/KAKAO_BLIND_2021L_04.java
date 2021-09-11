import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_04 {
    public static void main(String[] args) throws IOException {
        int n = 5;
        int[] info = {
            2,1,1,1,0,0,0,0,0,0,0
        };
        solution(n, info);
    }

    static int[] Ryan = new int[11];
    static boolean[] shooted = new boolean[11];
    public static int[] solution(int n, int[] info) {
        

        int[] answer = {};
        return answer;
    }

    // curIdx : 현재 몇점 짜리에 맞출 것이냐
    // reamin : 남은 화살의 갯수
    static void dfs(int[] info, int curIdx, int remain) {

        for (int i = 0; i < 11; i++) {
            if (shooted[i]) continue;
            shooted[i] = true;
            shooted[i] = false;
        }
    }
}
