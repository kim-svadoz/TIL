import java.util.*;

public class DeliveryHero_2021H_03 {
    public static void main(String[] args) {
        int N = 3298;
        System.out.println(solution(N));
    }

    static int[] origin, nums;
    public static int solution(int N) {
        // write your code in Java SE 8
        int answer = 0;

        String s = Integer.toString(N);
        origin = new int[s.length()];
        nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = s.charAt(i) - '0';
        }

        boolean flag = false;

        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            //System.out.println("prev:"+prev+", cur:"+nums[i]);

            // 원본배열 보다 무조건 크다면 이제는 가장 작게 바꿔줘야한다.
            if (flag) {
                if (prev == 0) {
                    nums[i] = 1;
                } else {
                    nums[i] = 0;
                }
                prev = nums[i];
            } else { // 원본배열보다 아직 크지 않으므로 자리수의 값을 +1 해줘야 한다.
                if (nums[i] == prev) {
                    nums[i] = prev + 1;
                    if (nums[i] > origin[i]) flag = true;
                    prev = nums[i];
                    continue;
                }

                if (i == nums.length - 1) {
                    if (nums[i] == prev) {
                        nums[i] = prev + 1;
                    } else {
                        nums[i] = nums[i] + 1;
                    }

                    if (nums[i] > origin[i]) flag = true;

                    prev = nums[i];
                    continue;
                }
            }
        }

        answer = ArrToInt();
        return answer;
    }

    public static int ArrToInt() {
        int ret = 0;

        for (int i = 0; i < nums.length; i++) {
            ret *= 10;
            ret += nums[i];
        }
        return ret;
    }
}
