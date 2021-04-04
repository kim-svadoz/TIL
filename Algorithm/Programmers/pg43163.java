import java.util.*;

public class pg43163 {
    static int n;
    static boolean[] visit;
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String words[] = {"hot", "dot", "dog", "lot", "log", "cog"};
        solution(begin, target, words);
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        n = begin.length();
        visit = new boolean[words.length];

        answer = bfs(begin, target, words, visit);
        System.out.println(answer);
        return answer;
    }

    static int bfs(String begin, String target, String[] words, boolean[] visit) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(begin, 0));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            String w = cur.w;
            int cnt = cur.cnt;

            if (w.equals(target)) {
                return cnt;
            }

            for (int i = 0; i < words.length; i++) {
                if(!visit[i] && isChangable(w, words[i])) {
                    q.add(new Pair(words[i], cnt + 1));
                    visit[i] = true;
                }
            }
        }
        
        return 0;
    }

    static boolean isChangable(String cur, String word) {
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (cur.charAt(i) != word.charAt(i)) {
                diff++;
            }
            if (diff > 1) return false;
        }
        return true;
    }

    static class Pair {
        String w;
        int cnt;
        public Pair(String w, int cnt) {
            this.w = w;
            this.cnt = cnt;
        }
    }

}