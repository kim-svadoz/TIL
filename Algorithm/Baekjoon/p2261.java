import java.io.*;
import java.util.*;
public class p2261 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static Pair[] pair;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        pair = new Pair[n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pair[i] = new Pair(x, y);
        }
        // 최소거리 구하기 위해 x값 오름차순 정렬
        Arrays.sort(pair, (a, b) -> (a.x - b.x));
        // TreeSet에 들어가는 Pair의 y값이 같으면 x값이 오름차순, 다르다면 y값이 오름차순
        TreeSet<Pair> set = new TreeSet<>((a, b) -> ((a.y == b.y) ? a.x - b.x : a.y - b.y));

        
        set.add(pair[0]);
        set.add(pair[1]);
        // 두 점 사이의 거리를 최단거리라고 가정하고 시작
        // 기준이 되는 0과 가장 가까운 1의 거리를 구한다.
        long answer = dist(pair[0], pair[1]);
        int start = 0;
        for (int i = 2; i < n; ++i) {
            Pair cur = pair[i];
            // set에 있는 점들의 유효성 검사
            while (start < i) {
                Pair p = pair[start];
                long x = cur.x - p.x;
                if (x * x > answer) { // x 축의 차이가 최단거리보다 크다면 비교할 필요가 없기때문에 set에서 삭제
                    set.remove(p);
                    start ++;
                } else { 
                    break;
                }
            }
            // 최단거리
            int d = (int) Math.sqrt((double) answer) + 1;
            // y값의 범위를 계산
            Pair from = new Pair(-10001, cur.y - d);
            Pair to = new Pair(10001, cur.y + d);

            // subSet() 메소드는 첫 번째 매개변수로 전달된 값에 해당하는 요소부터 시작하여
            // 두 번째 매개변수로 전달된 값에 해당하는 요소의 바로 직전 요소까지를 반환
            for (Pair pair : set.subSet(from, to)) {
                long distance = dist(cur, pair);
                answer = Math.min(answer, distance);
            }
            set.add(cur);
        }
        /*
        for(int i = 0; i < n; ++i) {
            bw.write(pair[i].x + " " + pair[i].y + "\n");
        }
        */
        bw.write(answer+"\n");
        bw.flush();
        br.close();
        bw.close();
    }

    static long dist(Pair A, Pair B) {
        return (long) (Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
    }

    static class Pair{
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}