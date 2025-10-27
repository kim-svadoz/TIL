/*
    Greedy + binary search
    자료형 타입이 int면 maxLen이 넘어가서 잘못된 연산이 수행된다.

    mid가 잘못 계산되어 lo가 계속해서 올라가는 것이 반복되면, 결국 lo도 MAX를 넘어서며 오버플로우.
    때문에 lor가 hi보다 커지는 것이 아니라 오히려 음수로 가게 되어 무한 반복 -> 시간초과
    + 이와 같은 undefined behavior는 전혀 예상하지 못한 방향으로 어떤 행동으로도 무한루프에 빠지는 것이 충분히 가능하다.
*/
import java.io.*;
import java.util.*;

public class p17976 {
    static class Pair implements Comparable<Pair>{
        int a, b;
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Pair p) {
            return a - p.a;
        }
    }
    static int n;
    static Pair[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new Pair[n];
        long maxLen = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(a, a + b);
            maxLen = Math.max(maxLen, a + b);
        }
        Arrays.sort(arr);

        // 이분탐색 진행
        // left와 right 포인터의 값이 불변한 곳을 정확히 찾는게, 이분탐색에서 실수하지 않는 방법이다!
        // 나올 수 있는 최소 차이 : 0, 최대 차이 : 맨 오른쪽
        long start = 0;
        long end = maxLen;
        long answer = 0;
        while (start <= end) {  // - 최적화 문제
            long mid = (start + end) / 2;
            // 두 선분의 차이를 mid로 할 수 있는 선분위의 두 점이 존재하는가 ? - 결정 문제
            if (promising(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // 출발점을 기준 정렬
        System.out.println(answer);

    }

    static boolean promising(long val) {
        // 맨처음 위치는 실의 출발점
        long now = arr[0].a;
        for (int i = 1; i < n; i++) {
            // val와 출발점을 더한 것이 다음 실의 범위를 벗어난다면 false
            if (now + val > arr[i].b) return false;
            else {
                now = Math.max(arr[i].a, now + val);
            }
        }
        return true;
    }
}
