import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_03 {
    public static void main(String[] args) throws IOException {
        int[] fees = {
            1, 461, 1, 10
        };
        String[] records = {
            "00:00 1234 IN"
        };
        solution(fees, records);
    }    

    static class Car implements Comparable<Car> {
        String carNum;
        int idx;
        int totalParkTime;
        public Car(int idx, int totalParkTime) {
            StringBuilder sb = new StringBuilder(idx);
            while (sb.length() < 4) {
                sb.append("0");
            }
            this.idx = idx;
            this.carNum = sb.reverse().toString();
            this.totalParkTime += totalParkTime;
        }
        public int compareTo(Car o) {
            return idx - o.idx;
        }
    }
    static int[] save = new int[10000];
    static int[] carArr = new int[10000];
    public static int[] solution(int[] fees, String[] records) {
        Arrays.fill(save, -1);
        for (String record : records) {
            int carNum = Integer.parseInt(record.split(" ")[1]);
            int time = convertTime2Minute(record.split(" ")[0]);
            String inOrOut = record.split(" ")[2];
            if (inOrOut.equals("IN")) {
                save[carNum] = time; // 입차 시간 저장
            } else {
                carArr[carNum] += time - save[carNum]; // 출차 - 입차 시간 저장
                save[carNum] = -1; // 저장한 것 초기화
            }
        }

        for (int i = 0; i < 10000; i++) {
            // 출차처리가 안된 자동차는 23:59를 출차로 한다.
            int time = convertTime2Minute("23:59");
            if (save[i] != -1) {
                carArr[i] += time - save[i];
                save[i] = 0;
            }
        }

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // 주차장을 이용한 차량 중에서
            if (carArr[i] != 0) {
                // 금액 계산
                int cost = fees[1];
                if (carArr[i] > fees[0]) { // 기본 시간 보다 크다면 단위시간 별로 요금을 할당한다.
                    cost += (Math.ceil((double)(carArr[i] - fees[0]) / fees[2])) * fees[3];
                }
                carList.add(new Car(i, cost));
            }
        }

        Collections.sort(carList);
        int[] answer = new int[carList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = carList.get(i).totalParkTime;
            System.out.println(answer[i]);
        }
        return answer;
    }

    public static int convertTime2Minute(String time) {
        String[] nums = time.split(":");
        return Integer.parseInt(nums[0]) * 60 +
            Integer.parseInt(nums[1]);
    }
}
