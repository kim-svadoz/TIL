import java.util.*;
public class GREB_2021L_03 {
    public static void main(String[] args) {
        String word = "APPLE";
        String[] cards = {
            "LLZKE", "LCXEA","CVPPS","EAVSR","FXPFP"
        };
        solution(word, cards);
    }

    static boolean[] state;
    static String[][] map;
    static int N, answer;
    static HashMap<Character, Integer> hm;
    static int[] arr;
    static public int solution(String word, String[] cards) {
        answer = 0;
        N = cards.length;
        map = new String[N][N];
        hm = new HashMap<>();

        arr = new int[26];
        state = new boolean[50000];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char c = cards[i].charAt(j);
                map[i][j] = c + "";
            }
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            arr[c - 'A']++;
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.put(c, hm.get(c) + 1);
            }
        }

        recursive(word, 0, new StringBuilder());
        
        System.out.println(answer);
        return answer;
    }

    static void recursive(String word, int cur, StringBuilder sb) {
        if (cur == N) {
            String str = sb.toString();
            int[] cnt = new int[26];
            for (int i = 0; i < str.length(); i++) {
                cnt[str.charAt(i) - 'A']++;
            }
            System.out.println("str::"+str);
            for (int i = 0; i < word.length(); i++) {
                System.out.println(cnt[word.charAt(i) - 'A']);
            }
            for (Map.Entry<Character, Integer> entries : hm.entrySet()) {
                char k = entries.getKey();
                int v = entries.getValue();
                if (cnt[k - 'A'] != v) {
                    return;
                }
            }
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (state[i]) continue;
            state[i] = true;
            sb.append(map[cur][i]);
            recursive(word, cur + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            state[i] = false;
        }
    }
}
