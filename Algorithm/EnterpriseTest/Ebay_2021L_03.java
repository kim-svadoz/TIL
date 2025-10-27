import java.util.*;

public class Ebay_2021L_03 {
    public static void main(String[] args) {
        int[] prices1 = {
            1, 2, 3, 4
        };
        int[] prices2 = {
            1, 2, 4, 1, 2, 3, 5
        };
        int[] prices3 = {
            4, 3, 2, 1, 3
        };
        int[] prices4 = {
            5, 4, 3, 2, 1
        };
        System.out.println(solution(prices2));
    }

    static int n, answer;
    static boolean[] visited;
    static int[] stock;
    static public int solution(int[] prices) {
        // 사고 팔고 사고 팔고 재귀로 모든 경우를 다 돌아보자
        n = prices.length;
        visited = new boolean[n];
        answer = 0;

        stock = new int[2];
        stock[0] = 2; // 매수 cnt
        stock[1] = 2; // 매도 cnt

        recur(prices, 0, 0);
            
        return answer;
    }

    /*
        prices : 배열
        cur : 현재 index
        price : 현재까지 이득
    */
    static void recur(int[] prices, int cur, int price) {
        if (cur == n) {
            // 최대값을 계산해준다.
            answer = Math.max(answer, price);
            return;
        }

        for (int i = cur; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            // 매수
            if (stock[0] > 0) {
                stock[0]--;
                recur(prices, i + 1, price - prices[i]);
                stock[0]++;
            }

            // 매도
            if (stock[0] < 2 && stock[1] > stock[0] && stock[1] > 0) {
                stock[1]--;
                recur(prices, i + 1, price + prices[i]);
                stock[1]++;
            }
            visited[i] = false;
            recur(prices, i + 1, price);
        }
    }
}
