import java.io.IOException;
import java.util.*;

public class LINE_2021L_03 {
    public static void main(String[] args) throws IOException {
        int[][] jobs = {
            {0, 2, 3, 1}, {5, 3, 3, 1}, {10, 2, 4, 1}
        };
        solution(jobs);
    }
    static class Work implements Comparable<Work> {
        int s, time;
        int part;
        int importance;
        public Work(int s, int time, int part, int importance) {
            this.s = s;
            this.time = time;
            this.part = part;
            this.importance = importance;
        }
        public int compareTo(Work o) {
            return s - o.s;
        }
    }
    static List<Integer> list = new ArrayList<>();
    static PriorityQueue<Work> workList = new PriorityQueue<>();
    static PriorityQueue<Work> readyQueue = new PriorityQueue<>();
    static Queue<Work> workQueue = new LinkedList<>();
    public static int[] solution(int[][] jobs) {
        for (int[] job : jobs) {
            workList.add(new Work(job[0], job[1], job[2], job[3]));
        }
        int now;
        int prev = -1;
        Work first = workList.poll();
        now = first.s;
        workQueue.add(first);

        while (!workQueue.isEmpty()) {
            Work work = workQueue.poll();
            int part = work.part; // 현재 작업의 분류 번호
            now += work.time; // 현재 작업이 끝나는 시간
            System.out.print("now::"+now+", part:"+part+", ");
            
            if (prev != part) {
                list.add(part); // 정답 리스트에 저장
            }
            prev = part;

            // workList 작업들 중 prev (6초)이전에 들어온 작업들을 모두 readyQueue 넣는다.
            while (!workList.isEmpty() && workList.peek().s <= now) {
                readyQueue.add(workList.poll());
            }
            
            // readyQueue에 있는 작업들 중 중요도를 파악하여 workQueue에 넣는다.
            PriorityQueue<Work> stash = new PriorityQueue<>();
            int max = 0; // 중요도의 최댓값
            int nextPart = 102; // 어떤 분류번호를 넣을것인가
            boolean flag = false;
            int[] arr = new int[101]; // 분류 번호에 따른 중요도 합을 나타내는 배열
            while (!readyQueue.isEmpty()) {
                Work tmp = readyQueue.poll();
                if (tmp.part == part) {
                    workQueue.add(tmp); // 이전작업의 분류번호와 같다면 해당 번호의 작업을 그대로 workQueue에 넣는다.
                    flag = true;
                } else {
                    // 그게 아니라면, 중요도의 합이 높은 분류번호가 workQueue에 들어간다.
                    arr[tmp.part] += tmp.importance;
                    if (arr[tmp.part] >= max) {
                        max = arr[tmp.part];
                        nextPart = Math.min(nextPart, tmp.part);
                    }
                    stash.add(tmp); // workQueue에 들어가지 못한 나머지는 stash에 잠깐 담아 놨다가 다시 저장
                }
            }
            System.out.println("max::"+max+", nextPart:"+nextPart);
            while (!stash.isEmpty()) {
                if (!flag && stash.peek().part == nextPart) {
                    workQueue.add(stash.poll());
                } else {
                    readyQueue.add(stash.poll());
                }
            }

            if (workQueue.isEmpty() && !workList.isEmpty()) {
                workQueue.add(workList.poll());
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
            System.out.print(answer[i]+" ");
        }
        return answer;
    }
}
