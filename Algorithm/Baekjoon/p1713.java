import java.util.*;
import java.io.*;

public class p1713 {
    static class Pic implements Comparable<Pic>{
        int idx, cnt, time;
        public Pic(int idx, int cnt, int time) {
            this.idx = idx;
            this.cnt = cnt;
            this.time = time;
        }
        
        public int compareTo(Pic o) {
            // 추천수가 같다면 오래된 순으로 오름차순 정렬
            if (cnt == o.cnt) {
                return time - o.time;
            } else { // 그게 아니라면 추천 순으로 오름차순 정렬
                return cnt - o.cnt;
            }
        }
    }
    static int n, total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        total = Integer.parseInt(br.readLine());
        List<Pic> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < total; i++) {
            int num = Integer.parseInt(st.nextToken());
            boolean flag = false;
            // 같은게 있는지 파악한다.
            for (Pic pic : list) {
                if (pic.idx == num) {
                    flag = true;
                    pic.cnt++;
                    break;
                }
            }
            if (!flag) {
                if (list.size() == n) {
                    // 꽉차면 맨 앞에걸 지우고
                    list.remove(0);
                    // 새로운 학생을 추천수 1로 추가한다.
                    list.add(new Pic(num, 1, i));
                } else {
                    // 꽉 안찼을 때는 그냥 지우지 않고 넣는다.
                    list.add(new Pic(num, 1, i));
                }
            }
            
            // 계속 정렬을 한다.
            Collections.sort(list);
        }
        
        // 학생 번호 순으로 정렬해야 하기 때문에 마지막으로 한번 더 정렬한다.
        Collections.sort(list, new Comparator<Pic>() {
            public int compare(Pic p1, Pic p2) {
                return p1.idx - p2.idx;
            }
        });
        for (Pic p : list) {
            System.out.print(p.idx + " ");
        }
    }
}