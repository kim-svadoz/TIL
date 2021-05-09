import java.io.*;
import java.util.*;

public class SUMMER_2021H_INTERN_02 {
    public static void main(String[] args) throws IOException {
        int[] t = {0, 1, 3, 0};
        int[] r = {0, 1, 2, 3};
        solution(t, r);
    }

    static class Person implements Comparable<Person>{
        int id, arrive, level;

        public Person(int id, int level) {
            this.id = id;
            this.level = level;
        }

        public Person(int id, int arrive, int level) {
            this.id = id;
            this.arrive = arrive;
            this.level = level;
        }
        public int compareTo(Person o) {
            if (arrive == o.arrive) {
                if (level == o.level) {
                    return id = o.id;
                }
                return level - o.level;
            }
            return arrive - o.arrive;
        }
        void latency() {
            this.arrive = this.arrive + 1;
        }
    }
    static int size;
    static int[] answer;
    static boolean[] visit;
    static List<List<Person>> list;
    static public int[] solution(int[] t, int[] r) {
        size = t.length;
        list = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < size; i++) {
            list.get(t[i]).add(new Person(i, t[i], r[i]));
        }
        
        for (int i = 0; i < size; i++) {
            Collections.sort(list.get(t[i]));
        }

        visit = new boolean[size];
        go();
        
        return answer;
    }

    static void go() {
        answer = new int[size];
        int idx = 0;
        for (int time = 0; time <= 10000; time ++) {
            // time 초에 도착한 사람이 있으면 한명씩 체크한다.
            int curSize = list.get(time).size();
            for (int i = 0; i < curSize; i++) {
                Person p = list.get(time).get(i);
                //System.out.println("몇명이 같이 들어왔어요? "+list.get(time).size()+", 누가요?"+p.id);

                if (i == 0) {
                    visit[p.id] = true;
                    System.out.println("time:"+time+", id:"+p.id+", arrive:"+p.arrive+", level:"+p.level);
                    answer[idx++] = p.id;
                } else {
                    if (visit[p.id]) continue;
                    list.get(time + 1).add(new Person(p.id, p.arrive + 1, p.level));
                }
            }

            if (time < 9999) Collections.sort(list.get(time + 1));
        }
    }
}
