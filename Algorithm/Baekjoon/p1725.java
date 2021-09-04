/*
    히스토그램(Platinum 5)

    [ sol1. 분할정복 : O(NlogN) ]


    [ sol2. Stack : O(N) ]

    1. index를 기준으로 히스토그램을 탐색
    2. i(0 ~ n)을 탐색할 때 현재 새로들어온 직사각형의높이(arr[i])와 이전(s.peek().h)에 위치한 높이와 비교한다.
     2-1. arr[i] >= s.peek().h // 새로들어온 직사각형의 높이가 클 경우 이전보다 지금 새로 들어온 직사각형에서 큰 직사각형의 넓이가 나올 수 있으므로 while문을 패스한다.
     2-2. arr[i] < s.peek().h // 이전에 위치한 직사각형의 높이가 더 큰 경우, 큰 직사각형이 이전에 위치할 확률이 높으므로 넓이를 구해준다.
      (arr[i]보다 작거나 같은 값이 나올 때 까지 반복)
    3. 2번의 연산이 끝나면 i와 arr[i]를 스택에 넣어준다.
    
*/
import java.io.*;
import java.util.*;

public class p1725 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution1(0, n));
        System.out.println(solution2());
    }

    static int solution1(int s, int e) {
        if (s == e) return 0; // base case : 넓이 0
        if (s + 1 == e) return arr[s]; // base case : 넓이 1

        int mid = (s + e) / 2;
        int result = Math.max(solution1(s, mid), solution1(mid, e)); // 막대의 중간부분을 설정한 후 왼쪽과 오른쪽 각각 재귀호출

        int w = 1, h = arr[mid], l = mid, r = mid;

        while (r - l + 1 < e - s) {
            // 현재 탐색 중인 l과 r이 s와 e의 범위 안에 있는 걸 전제 조건으로 둔다.
            int p = l > s ? Math.min(h, arr[l - 1]) : -1; // 왼쪽으로 확장했을 경우의 높이
            int q = r < e - 1 ? Math.min(h, arr[r + 1]) : -1; // 오른쪽으로 확장했을 경우의 높이

            // 더 높은 높이를 가진 쪽으로 확장한다. (그리디)
            if (p >= q) {
                h = p;
                l--;
            } else {
                h = q;
                r++;
            }
            // 확장을 했다고 했으니 넓이는 +1
            result = Math.max(result, ++w * h);
        }
        return result;
    }

    static class Item {
        long idx, h;
        Item(long idx, long h) {
            this.idx = idx;
            this.h = h;
        }
    }
    
    static long solution2() {
        Stack<Item> stk = new Stack<>();
        long ret = 0;
        for (int i = 0; i < n; i++) {
            // 현재 탐색하고 있는 히스토그램의 높이보다, 스택에 저장된 높이가 크면 pop한다.
            // -> pop하기 까지의 넓이를 계산해 최대 넓이와 비교한 후 최대면 저장한다.
            while (!stk.isEmpty() && stk.peek().h > arr[i]) {
                long h = stk.pop().h;
                long w = i;

                if (!stk.isEmpty()) {
                    w -= stk.peek().idx + 1;
                }

                ret = Math.max(ret, h * w);
                //System.out.println("i:"+i+", h:"+h+", w:"+w+", ret:"+ret);
            }

            // 스택에서 현재 idx와 높이들을 함께 보관한다.
            stk.push(new Item(i, arr[i]));
        }

        // stack에 남아 있는, 계산하지 못한 직사각형들을 계산한다.
        while (!stk.isEmpty()) {
            long h = stk.pop().h;
            int w = n;

            if (!stk.isEmpty()) {
                w -= stk.peek().idx + 1;
            }

            ret = Math.max(ret, h * w);
            //System.out.println("h > "+h+",  w > "+w);
        }

        return ret;
    }
}
