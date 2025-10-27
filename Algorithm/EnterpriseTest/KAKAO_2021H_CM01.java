import java.util.*;

public class KAKAO_2021H_CM01 {
    static int[][] arr;
    static int n, min = Integer.MAX_VALUE;
    static boolean[] visit;
    static boolean[] test;
    public static void main(String[] args) throws Exception{
        int[] gift_cards = {5, 4, 5, 4, 5};
        int[] wants = {1, 2, 3, 5, 4};
        solution(gift_cards, wants);
    }

    public static int solution(int[] gift_cards, int[] wants) {
        int answer = 0;
        n = gift_cards.length;
        arr = new int[n + 1][2];
        visit = new boolean[n + 1];
        test = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i][0] = gift_cards[i - 1]; // 들고있는 값
            arr[i][1] = wants[i - 1]; // 원하는 값
            if (arr[i][0] == arr[i][1]) {
                visit[i] = true;
            }
        }

        int success = 0;
        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            if (visit[i] == false) {
                if (changeTry(i)) success++;
            }
            test[i] = true;
        }
        answer = n - success * 2;
        System.out.println(answer);
        return answer;
    }

    static boolean changeTry(int idx) {
        for (int i = 1; i <= n; i++) {
            if (i == idx || visit[i]) continue;
            int cnt = swap(idx, i);
            if (cnt > 0) {
                return true;
            }
        }
        return false;
    }

    static int swap(int idx, int p) {
        if (arr[p][0] == arr[p][1]) return 0;
        int tmp = arr[idx][0];
        arr[idx][0] = arr[p][0];
        arr[p][0] = tmp;
        if (arr[idx][0] == arr[idx][1]) {
            visit[idx] = true;
            if (arr[p][0] == arr[p][1]) {
                visit[p] = true;
                return 2;
            }
            return 1;
        }
        tmp = arr[idx][0];
        arr[idx][0] = arr[p][0];
        arr[p][0] =  tmp;
        return 0;
    }
}