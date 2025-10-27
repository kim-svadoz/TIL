/*
    카카오 2020 블라인드 6번 외벽점검
    완전탐색, 비트마스킹
*/
import java.io.*;
import java.util.*;

public class pg60062 {
    public static void main(String[] args) throws IOException {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(n, weak, dist));
    }    

    static final int INF = 987654321;
    static int N, ret;
    static int[] Weak, Dist;
    public static int solution(int n, int[] weak, int[] dist) {
        N = n;
        Weak = weak;
        Dist = dist;
        Arrays.sort(Dist);
        ret = INF;

        // 취약점을 하나씩 완전탐색 한다.
        for (int i = 0; i < Weak.length; i++) {
            find(1, i, 0);
        }
        
        if (ret == INF) return -1;
        return ret;
    }

    // pos : 어떤 취약점 부터 시작을 할지
    // visited : 취약점의 방문 여부를 비트로 활용
    static void find(int cnt, int pos, int visited) {
        // 사용할 수 있는 친구가 넘어가면
        if (cnt > Dist.length) return;
        // 현재 ret보다 같거나 높은 횟수는 셀 필요가 없다. -> 가지치기
        if (cnt >= ret) return;

        // Dist[Dist.length - Cnt]에 해당하는 친구가 어느 취약점까지 점검할 수 있는가?
        for (int i = 0; i < Weak.length; i++) {
            int nextPos = (pos + i) % Weak.length;
            int diff = Weak[nextPos] - Weak[pos];

            if (nextPos < pos) {
                diff += N;
            }

            // 방문 불가
            if (diff > Dist[Dist.length - cnt]) break;

            // 방문 가능!
            visited |= (1 << nextPos);
        }

        // 모든 취약점을 방문했다면? 모든 비트가 켜져있다는 것이므로.
        if (visited == (1 << Weak.length) - 1) {
            ret = cnt;
            return;
        }

        // 다시 모든 취약점을 탐색한다
        for (int i = 0; i < Weak.length; i++) {
            // 이미 방문했던 곳은 시작위치로 설정할 필요가 없다.
            if ((visited & (1 << i)) != 0) continue;
            find(cnt + 1, i, visited);
        }
    }
}
