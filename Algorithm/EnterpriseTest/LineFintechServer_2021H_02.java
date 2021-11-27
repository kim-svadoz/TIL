import java.util.*;

public class LineFintechServer_2021H_02 {
    public static void main(String[] args) {
        int[] arr = {
            1, 2, 3, 2, 1
        };
        System.out.println(solution(arr));
    }

    static final int MOD = 1000000000 + 7;
    public static int solution(int[] arr) {
        int len = arr.length;
        int answer = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < len - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                list.add(i);
            }
        }
        for (int i : list) {
            System.out.println("idx :: "+i);
        }

        int prevIdx = 0;
        for  (int i = 0; i < list.size(); i++) {
            int curIdx = list.get(i);
            int nextIdx = i + 1 < list.size() ? list.get(i + 1) : len;
            
            int left = 0;
            for (int j = curIdx; j > prevIdx; j--) {
                if (arr[j] > arr[j - 1]) {
                    left ++;
                    continue;
                }
                break;
            }

            int right = 0;
            for (int j = curIdx; j < nextIdx - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    right++;
                    continue;
                }
                break;
            }
            if (left == 1 && right == 1) {
                answer += 1;
            } else if (left == 1) {
                answer += right;
            } else if (right == 1) {
                answer += left;
            } else {
                answer += left + right;
            }
            
            prevIdx = curIdx;
            
            answer %= MOD;
        }

        return answer % MOD;
    }
}
