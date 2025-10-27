/*
    2021 카카오 블라인드 5번 광고삽입
    구현, 투포인터
*/
import java.io.IOException;
import java.util.*;
public class p72414 {
    public static void main(String[] args) throws IOException {
         
    }
    public static String solution(String play_time, String adv_time, String[] logs) {
        int playSec = convert(play_time);
        int advSec = convert(adv_time);

        int[] totalSec = new int[100 * 3600];
        for (String log : logs) {
            int start = convert(log.substring(0, 8));
            int end = convert(log.substring(9, 17));

            for (int i = start; i < end; i++) {
                totalSec[i] += 1;
            }
        }

        long currSum = 0;
        for (int i = 0; i < advSec; i++) {
            currSum += totalSec[i];
        }

        long maxSum = currSum;
        int maxIdx = 0;
        for (int i = advSec; i < playSec; i++) {
            currSum = currSum + totalSec[i] - totalSec[i - advSec];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxIdx = i - advSec + 1;
            }
        }

        return String.format("%02d:%02d:%02d", maxIdx / 3600, maxIdx / 60 % 60, maxIdx % 60);
    }

    public static int convert(String time) {
        String[] nums = time.split(":");
        return Integer.parseInt(nums[0]) * 60 * 60 +
            Integer.parseInt(nums[1]) * 60 + 
                Integer.parseInt(nums[2]);
    }
}
