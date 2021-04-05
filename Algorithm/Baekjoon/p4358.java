import java.util.*;
import java.io.*;
public class p4358 {
    static Trie trie;
    static class TrieNode {
        Map<Character, TrieNode> childNodes = new TreeMap<>();
        boolean isLeapNode;
        int cnt = 0;
    }
    static class Trie {
        TrieNode rootNode;
        int plantCount;
        public Trie() {
            rootNode = new TrieNode();
            plantCount = 0;
        }
        void insert(String str) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                thisNode = thisNode.childNodes.computeIfAbsent(c, key -> new TrieNode());
            }
            thisNode.isLeapNode = true;
            thisNode.cnt ++;
            this.plantCount++;
        }

        // 경로를 추적하기 위해서 처음부터 dfs로 돈다
        void dfs(TrieNode thisNode, StringBuilder sb) {
            Object[] keys = thisNode.childNodes.keySet().toArray();

            for (Object key : keys) {
                char keyCh = (char) key;
                TrieNode nextNode = thisNode.childNodes.get(keyCh);
                if (nextNode != null) {
                    sb.append(keyCh);
                    if (nextNode.isLeapNode == true) {
                        double ratio = (double)(nextNode.cnt * 100) / this.plantCount;
                        System.out.println(sb.toString() + " " + String.format("%.4f", ratio));
                    }
                    dfs(nextNode, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trie = new Trie();

        while (true) {
            String line = br.readLine();
            if (line == null || line.length() == 0) {
                break;
            }

            trie.insert(line);
        }
        // 트라이의 맨 위인 루트노드부터 dfs를 시작한다.
        trie.dfs(trie.rootNode, new StringBuilder());
        
    }
}