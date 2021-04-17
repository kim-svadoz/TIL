import java.io.*;
import java.util.*;

public class p20546 {
    static class Stock {
        int cash, cnt;
        public Stock(int cash, int cnt) {
            this.cash = cash;
            this.cnt = cnt;
        }

        void buyStock(int todayCost, int cnt) {
            if (this.cash < todayCost * cnt) return;
            this.cnt += cnt;
            this.cash -= todayCost * cnt;
        }

        void sellStock(int todayCost) {
            this.cash += todayCost * this.cnt;
            this.cnt = 0;
        }

    }
    static int n;
    static int[] price;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st= new StringTokenizer(br.readLine());
        price = new int[14];
        for (int i = 0; i < 14; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int j = junhyun();
        int s = sungmin();
        if (j > s) {
            System.out.println("BNP");
        } else if (j == s) {
            System.out.println("SAMESAME");
        } else {
            System.out.println("TIMING");
        }
    }
    static int junhyun() {
        Stock Stk = new Stock(n, 0);
        for (int today : price) {
            int getCash = Stk.cash;
            int buyCnt = getCash / today;

            // 전량 풀매수
            if (buyCnt > 0){
                Stk.buyStock(today, buyCnt);
            }
        }
        int ret = Stk.cash + (Stk.cnt * price[13]);
        return ret;
    }

    static int sungmin() {
        Stock Stk = new Stock(n, 0);
        for (int i = 3; i < 14; i++) {
            int today = price[i];
            int getCash = Stk.cash;
            int buyCnt = getCash / today;

            // 점점 상승 -> 풀매도
            if (price[i - 1] > price[i - 2] && price[i - 2] > price[i - 3]) {
                Stk.sellStock(today);
            } else if (price[i - 1] < price[i - 2] && price[i - 2] < price[i - 3]) { // 풀매수
                Stk.buyStock(today, buyCnt);
            }
        }
        int ret = Stk.cash + (Stk.cnt * price[13]);
        return ret;
    }
    
}
