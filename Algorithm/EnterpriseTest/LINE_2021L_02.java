import java.io.IOException;
import java.util.*;

public class LINE_2021L_02 {
    public static void main(String[] args) throws IOException {
        String[] research = {
            "yxxy","xxyyy","yz"
        };
        int n = 2;
        int k = 1;
        System.out.println(solution(research, n, k));
    }

    static class BestIssueWord implements Comparable<BestIssueWord>{
        int idx;
        int cnt = 0; // 이슈 검색어가 된 횟수
        public BestIssueWord(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
        // 이슈 횟수 내림차순
        public int compareTo(BestIssueWord o) {
            if (cnt == o.cnt) {
                return idx - o.idx;
            }
            return o.cnt - cnt;
        }
    }
    static List<BestIssueWord> list = new ArrayList<>();
    public static String solution(String[] research, int n, int k) {
        int len = research.length;

        int[] totalBest = new int[26];
        for (int i = 0; i < len - n + 1; i++) {
            Map<Integer, Integer> map = new HashMap<>(); // 해당 key가 연속으로 몇일 들어갔는지
            int[] arr = new int[26];
            // 연속된 n일
            for (int j = i; j < i + n; j++) {
                int[] tmp = new int[26]; // 매일 최소 k번 이상 검색되는지 확인
                String s = research[j]; // ex. abaaaa
                for (int a = 0; a < s.length(); a++) {
                    int curIdx = s.charAt(a) - 'a';

                    if (!map.containsKey(curIdx)) {
                        map.put(curIdx, 1);
                    } else {
                        map.put(curIdx, map.get(curIdx) + 1);
                    }

                    tmp[curIdx]++;
                }
                
                for (int a = 0; a < 26; a++) {
                    if (tmp[a] != 0 && tmp[a] >= k) { // 매일 최소 k번 이상 검색이 된다면 해당 검색어를 추가한다.
                        arr[a]++;
                    }
                }
            }
            // 여기까지 연속된 n일에 대한 검색어 정보가 들어있다.
            for (int idx = 0; idx < 26; idx++) {
                if (arr[idx] < n) continue;

                // 연속으로 n일 나온 알파벳이 최고의 이슈 검색어 인지 확인한다.
                if (map.containsKey(idx)) {
                    if (map.get(idx) >= n * k * 2) {
                        totalBest[idx]++;
                    }
                }
            }
        }
        for (int idx = 0; idx < 26; idx++) {
            if (totalBest[idx] != 0) {
                list.add(new BestIssueWord(idx, totalBest[idx]));
            }
        }

        if (list.size() == 0) {
            return "None";
        }
        Collections.sort(list);
        BestIssueWord ret = list.get(0);
        String answer = (char)(ret.idx + 'a') + "";
        return answer;
    }
    
}
