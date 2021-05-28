/*
    택배
    Greedy

    단순히 출발지점과 도착지점을 오름차순으로 정렬한 후 구현하였더니 문제가 안풀리더라.
    
    문제 접근을 바꾸어야 한다.
    만약 1번마을에서 4번마을로 40을 보내려고 싣는다면, 2,3번마을에서는 아무것도 실을 수 없기 때문에
    받는 마을을 기준으로 오름차순, 받는마을이 같을경우 보내는 마을을 기준으로 오름차순 해야 한다.
*/
import java.io.*;
import java.util.*;
public class p8980 {
    static class Delivery implements Comparable<Delivery> {
        int u, v, weight;
        public Delivery (int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Delivery o) {
            if (v == o.v) {
                return u - o.u;
            }
            return v - o.v;
        }
    }
    static int n, c, m;
    static int[] truck;
    static Delivery[] del;
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        del = new Delivery[m];
        truck = new int[n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            del[i] = new Delivery(s, e, cnt);
        }
        Arrays.sort(del);
        // 트럭에 현재 담을 수 있는 최대용량을 미리 설정한다.
        Arrays.fill(truck, c);

        int answer = 0;
        for (Delivery cur : del) {
            int s = cur.u;
            int e = cur.v;
            int cnt = cur.weight;
            //System.out.print("출발: "+s+",  도착 :"+e+", 보내고 싶은 개수:"+cnt);

            // 현재 범위 내에서 실을 수 있는 가장 적은 값을 실어야 한다.
            // 하나라도 크다면 실을 수 없기 때문에
            int min = Integer.MAX_VALUE;
            for (int i = s; i < e; i++) {
                min = Math.min(min, truck[i]);
            }
            int load = cnt;
            load = Math.min(load, min);
            //System.out.println(",  실제로 실어보낸 값: "+load);

            answer += load;

            for (int i = s; i < e; i++) {
                truck[i] -= load;
            }
        }

        System.out.println(answer);
    }
}