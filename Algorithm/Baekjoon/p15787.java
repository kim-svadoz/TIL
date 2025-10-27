/*
    기차가 어둠을 헤치고 은하수를
    비트마스킹
    초기값은 0으로 두고.
    좌석은 20자리이지만 비트마스킹을 위해 21자리를 만든다.
       끝 좌석                                 첫 좌석
    0 / 0 0 0 0 0 / 0 0 0 0 0 / 0 0 0 0 0 / 0 0 0 0 0
*/
import java.io.*;
import java.util.*;
public class p15787 {
    static int n, m;
    static int[] train;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 1 ~ N 번째 열자, 1 ~ 20번째 자리
        // 1 << 20 : 1024 * 1024 이므로 약 백만
        train = new int[n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            
            switch (command) {
                case 1 :
                    int seat = Integer.parseInt(st.nextToken());                
                    put(num, seat);
                    break;
                case 2:
                    seat = Integer.parseInt(st.nextToken());                
                    getOff(num, seat);
                    break;
                case 3:
                    moveBack(num);
                    break;
                case 4:
                    moveForward(num);
                    break;
            }
        }
        
        // 은하수를 건널 수 있는 기차의 수는?
        boolean[] visit = new boolean[1 << 21];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (!visit[train[i]]) {
                ans++;
                visit[train[i]] = true;
            }
        }
        System.out.println(ans);
    }
    static void put(int num, int seat) {
        train[num] |= (1 << seat);
    }
    
    static void getOff(int num, int seat) {
        train[num] &= ~(1 << seat);
    }
    
    static void moveBack(int num) {
        train[num] <<= 1;
        // 21 칸을 모두 밀었으므로 21번째 칸에 범위 밖의 값이 남아있으므로 그 값을 검사해서 없애야 한다.(교집합)
        // 전체 수가 21 이므로 (1 << 21) -1
        train[num] &= ((1<<21) - 1);
    }
    
    static void moveForward(int num) {
        train[num] >>= 1;
        // 1의 반대는 0이므로
        train[num] &= ~1;
    }
}

/* hashSet을 이용해 이동가능한 기차를 구할 수도 있다.

    HashSet<Integer> trains = new HashSet<>();
    for (int i = 1; i <= n; i++) {
        trains.add(train[i]);
    }

    System.out.println(trains.size());

*/