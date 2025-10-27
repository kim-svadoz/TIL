import java.io.*;
import java.util.*;

public class p20166 {
    static int n, m, k;
    static char[][] map;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static Trie trie;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 신이 좋아하는 문자열
        
        map = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }
        trie = new Trie();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for  (int j = 1; j <= m; j++) {
                sb.append(map[i][j]);
                trie.insert(sb.toString());
                makeTrie(i, j, sb, 1);
                sb.setLength(0);
            }
        }

        while (k-- > 0) {
            String god = br.readLine();
            sb.append(trie.find(god)).append("\n");
        }
        System.out.println(sb);
    }

    static class TrieNode {
        Map<Character, TrieNode> childNodes;
        int cnt;
        TrieNode() {
            childNodes = new HashMap<>();
            cnt = 0;
        }
    }

    static class Trie {
        TrieNode rootNode;
        Trie () {
            rootNode = new TrieNode();
        }
        void insert(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                thisNode = thisNode.childNodes.computeIfAbsent(c, key ->  new TrieNode());
            }
            thisNode.cnt = thisNode.cnt + 1;
        }

        int find(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++)  {
                char  c = s.charAt(i);
                if (!thisNode.childNodes.containsKey(c)) return 0;
                thisNode = thisNode.childNodes.get(c);
            }
            return thisNode.cnt;
        }
    }

    static void makeTrie(int x, int y, StringBuilder sb, int len) {
        if (len == 5) return;
        for (int i = 0; i < 8; i++) {
            int nx = (x + dx[i]) % n;
            int ny = (y + dy[i]) % m;
            if (nx <= 0) nx += n;
            if (ny <= 0) ny += m;

            sb.append(map[nx][ny]);
            trie.insert(sb.toString());
            makeTrie(nx, ny, sb, len + 1);
            sb.setLength(sb.length() - 1);
        }
    }
}
