import java.io.*;
import java.util.*;
public class LINE_PIN_2021H_03 {
    public static void main(String[] args) throws IOException {
        int n = 2;
        String[] data = {"a1 1 5 9", "a2 1 9 5", "b1 2 3 3"};
        String limit = "0 10";
        solution(n, data, limit);
    }

    static class Algo {
        String name;
        int target, time, space;
        public Algo(String name, int target, int time, int space) {
            this.name = name;
            this.target = target;
            this.time = time;
            this.space = space;
        }
    }

    static Algo[] algo;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static Map<Integer, List<Algo>> map = new HashMap<>();
    public static String[] solution(int n, String[] data, String limit) {
        int size = data.length;
        algo = new Algo[size];
        visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            String d = data[i];
            String name = d.split("")[0];
            int target = Integer.parseInt(d.split("")[1]);
            int time = Integer.parseInt(d.split("")[2]);
            int space = Integer.parseInt(d.split("")[3]);
            // algo[i] = new Algo(name, target, time, space);

            if (!map.containsKey(target)) {
                map.put(target, new ArrayList<Algo>());
            }
            List<Algo> list = map.get(target);
            list.add(new Algo(name, target, time, space));
            map.put(target, list);
        }

        // 조합 :: size개 중에서 n개를 뽑아야 하고 현재 뽑은 개수는 0부터 시작
        

        String[] answer = {};
        return answer;
    }

    static void comb(int size, int n, int cnt) {
        if (cnt == n) {
            int total = 0;
            int t = 0, s = 0;
            for (int i = 0; i < size; i++) {
                for (Map.Entry<Integer, List<Algo>> elem : map.entrySet()) {
                    int key = elem.getKey();
                    List<Algo> list;
                    if (visited[key]) {
                        list = elem.getValue();

                        for (Algo algo : list) {
                            t += algo.time;
                            s += algo.space;
                        }
                    }
                    
                }
            }


            if (total < min) {

            }
            return;
        }

        for (int i = 0; i < size; i++) {
            // i번 문제를 풀고싶다면
            visited[i] = true;
            comb(size, n, cnt + 1);
            visited[i] = false;
        }
    }
    
}
