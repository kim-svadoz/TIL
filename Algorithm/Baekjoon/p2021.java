/*
    최소환승경로
    BFS
*/
import java.io.*;
import java.util.*;

public class p2021 {
    static int n, l;
    static boolean[] visitedStat;
    static boolean[] visitedLine;
    static List<List<Integer>> stat; // 각 역에 어떤 노선을 포함하는지 담을 리스트
    static List<List<Integer>> line; // 노선에 어떤 역이 들어있는지 담을 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        visitedStat = new boolean[n + 1];
        visitedLine = new boolean[l + 1];

        stat = new ArrayList<>();
        line = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            stat.add(new ArrayList<>());
        }

        for (int i = 0; i <= l; i++) {
            line.add(new ArrayList<>());
        }

        for (int i = 1; i <= l; i++) {
            String[] s = br.readLine().split(" ");
            for (String station : s) {
                int num = Integer.parseInt(station);
                if (num != -1) {
                    line.get(i).add(num);
                    stat.get(num).add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = go(start, end);
        System.out.println(answer);
    }

    static int go(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visitedStat[start] = true; // 출발역은 한번 도착했으므로 visit 처리
        for (int line : stat.get(start)) { // 출발역이 포함하고 있는 노선(line)들을 pq에 넣어준다. (ex. 1호선, 2호선 환승지점)
            pq.add(new Node(line, start, 0));
            visitedLine[line] = true; // 해당 노선들은 visit 처리
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.curStat == end) {
                return cur.cnt;
            }
            for (int nextStation : line.get(cur.curLine)) { // nextStation : 현재 노선에 포함되어 있는 다음 역들
                if (!visitedStat[nextStation]) {
                    visitedStat[nextStation] = true;
                    pq.add(new Node(cur.curLine, nextStation, cur.cnt));

                    for (int nextLine : stat.get(nextStation)) { // nextLine: 다음 역이 포함하고 있는 노선들
                        if (!visitedLine[nextLine]) {
                            visitedLine[nextLine] = true;
                            pq.add(new Node(nextLine, nextStation, cur.cnt + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node>{
        int curLine, curStat, cnt;

        public Node(int curLine, int curStat, int cnt) {
            this.curLine = curLine;
            this.curStat = curStat;
            this.cnt = cnt;
        }

        public int compareTo(Node o) {
            return cnt - o.cnt;
        }
    }
}
