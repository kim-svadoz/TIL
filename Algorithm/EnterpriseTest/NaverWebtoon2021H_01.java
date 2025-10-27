/*
    2021-07-04
    네이버 웹툰 개발 챌린지 1차 코딩 테스트(Back-End) 
*/
import java.io.*;
import java.util.*;

public class NaverWebtoon2021H_01 {
    public static void main(String[] arsg) throws IOException {
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};
        solution(prices, discounts);
    }

    public static int solution(int[] prices, int[] discounts) {
        int answer = 0;
        PriorityQueue<Integer> pricePQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> discountPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int price : prices) {
            pricePQ.add(price);
        }
        for (int discount : discounts) {
            discountPQ.add(discount);
        }
        
        while (!pricePQ.isEmpty()) {
            int originPrice = pricePQ.poll();
            int discountPrice = originPrice;
            if (!discountPQ.isEmpty()) {
                discountPrice = originPrice * discountPQ.poll() / 100;
                answer += originPrice - discountPrice;
            } else {
                answer += originPrice;
            }
        }

        System.out.println(answer);
        return answer;
    }
}
