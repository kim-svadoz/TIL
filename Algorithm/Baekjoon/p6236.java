/*
    용돈관리
    이분탐색, 문제이해력
*/
import java.io.*;
import java.util.*;
public class p6236 {
    static int totalDayCnt, totalWithdrawCnt;
    static int HighestPrice;
    static int[] costArr;
    public static void main(String[] args) throws IOException {
        initValue();
        minWithdrawPrice();
    }

    static void initValue() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalDayCnt = Integer.parseInt(st.nextToken());
        totalWithdrawCnt = Integer.parseInt(st.nextToken());
        costArr = new int[totalDayCnt];
        for (int i = 0; i < totalDayCnt; i++) {
            costArr[i] = Integer.parseInt(br.readLine());
            HighestPrice = Math.max(HighestPrice, costArr[i]);
        }
    }

    // 이분탐색 실행 함수
    static void minWithdrawPrice() {
        int answer = 0;

        // 최소 금액: 1 (원)
        // 최대 금액: 가장 높은 금액 x 총 일수 (원)
        int lo = 1;
        int hi = HighestPrice * totalWithdrawCnt;
        answer = hi;
        while (lo <= hi) {
            int testPrice = (lo + hi) / 2;
            // testPrice 금액을 추출하면서 모든 조건을 만족하며 totalDayCnt동안 버틸 수 있는 가?
            // 버틸 수 있다면 금액을 점차적으로 줄여보면서 테스트한다.
            if (isPossible(testPrice)) {
                hi = testPrice - 1;
                answer = Math.min(answer, testPrice);
            } else { // 버틸 수 없다면 금액을 점차적으로 늘려보면서 테스트한다.
                lo = testPrice + 1;
            }
        }
        System.out.println(answer);
    }
    
    static boolean isPossible(int mid) {
        // 남아있는 돈의 초기값은 처음 가지고 있던 돈.
        int remain = mid;
        int cnt = 1;
        
        for (int i = 0; i < totalDayCnt; i++) {
            // 해당 금액으로 하루라도 만족하지 못하면 false
            if (mid < costArr[i]) return false;
            
            if (remain - costArr[i] < 0) {
                remain = mid;
                cnt++;
            }
            remain -= costArr[i];
        }
            
        return cnt <= totalWithdrawCnt;
    }
}