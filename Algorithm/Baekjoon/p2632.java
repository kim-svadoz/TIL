import java.io.*;
import java.util.*;

public class p2632 {
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[m];
        int[] arr2 = new int[n];

        for (int i = 0; i < m; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // 투포인터 활용해서 부분합 리스트 생성
        int lo = 0;
        int hi = 0;
        int sum = 0;
        while (lo < arr1.length) {
            sum += arr1[hi++];

            if (sum <= k) {
                list1.add(sum);
            } else {
                lo++;
                hi = lo;
                sum = 0;
            }

            hi = hi % m;

            if ((lo == 0 && hi== 0) || hi + 1 == lo) {
                lo++;
                hi = lo;
                sum = 0;
            }
        }

        lo = 0;
        hi = 0;
        sum = 0;
        while (lo < arr2.length) {
            sum += arr2[hi++];

            if (sum <= k) {
                list2.add(sum);
            } else {
                lo++;
                hi = lo;
                sum = 0;
            }

            hi = hi % n;

            if ((lo == 0 && hi== 0) || hi + 1 == lo) {
                lo++;
                hi = lo;
                sum = 0;
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int result = 0;
        int left = 0;
        int right = list2.size() - 1;

        // 모든 부분합의 경우의 수를 다 돌면서 확인한다.
        while (left < list1.size() && right >= 0) {
            int left_val = list1.get(left);
            int right_val = list2.get(right);

            // 목표값 위치
            if (left_val + right_val == k) {
                int left_cnt = 0;
                int right_cnt = 0;

                while (left < list1.size() && list1.get(left) == left_val) {
                    left_cnt++;
                    left++;
                }

                while (right >= 0 && list2.get(right) == right_val) {
                    right_cnt++;
                    right--;
                }
                result += left_cnt * right_cnt;
            }

            // 목표값을 향해서 포인터 한칸씩 이동
            if (left_val + right_val < k) {
                left++;
            }
            if (left_val + right_val > k) {
                right--;
            }
        }

        // left or right 한쪽 포인터가 범위 밖으로 나갔을 경우
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) == k) {
                result++;
            }
        }

        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i) == k) {
                result++;
            }
        }

        System.out.println(result);
    }
}
