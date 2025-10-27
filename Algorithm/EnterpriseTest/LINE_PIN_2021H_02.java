import java.io.*;
import java.util.*;

public class LINE_PIN_2021H_02 {
    public static void main(String[] args) throws IOException {
        int endingTime = 40;
        // 고유번호, 입력시간, 유효시간, 작업시간
        int[][] jobs = {
            {1, 10, 20, 3},
            {2, 14, 20, 9},
            {3, 18, 24, 2},
            {4, 25, 40, 5},
            {5, 28, 40, 1}
        };
        solution(endingTime, jobs);
    }

    static class Job implements Comparable<Job> {
        int jid, inputTime, validTime, workTime;
        public Job(int jid, int inputTime, int validTime, int workTime) {
            this.jid = jid;
            this.inputTime = inputTime;
            this.validTime = validTime;
            this.workTime = workTime;
        }

        public int compareTo(Job o) {
            if (inputTime == o.inputTime) {
                return workTime - o.workTime;
            }
            return inputTime - o.inputTime;
        }
    }
    static PriorityQueue<Job> pq = new PriorityQueue<>();
    public static int[] solution(int endingTime, int[][] jobs) {
        int[] answer = {};

        for (int[] job : jobs) {
            pq.add(new Job(job[0], job[1], job[2], job[3]));
        }

        int curTime = 0;
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            Job curJob = pq.poll();

            //System.out.print("현재시간:"+curTime+", 입력시간:"+curJob.inputTime);
            if (curJob.inputTime > curTime) {
                curTime = curJob.inputTime;
            }

            int curEndTime = curTime + curJob.workTime;
            //System.out.println(", 입력 후 시간(curTime):"+curTime+", 유효시간:"+curJob.validTime+", 작업시간:"+curJob.workTime+", 작업 후 종료 시간:"+curEndTime);
            // 현재 Job의 끝나는 시간이 유효시간 내에 있다면 hit
            if (curEndTime <= curJob.validTime && curEndTime <= endingTime) {
                curTime = Math.min(curJob.validTime, curEndTime);
                list.add(curJob.jid);
            } else {
                curTime = curEndTime;
            }
        }
        
        //System.out.println("========");
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
            System.out.print(answer[i]+" ");
        }

        return answer;
    }
}
