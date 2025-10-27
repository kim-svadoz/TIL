import java.io.*;
import java.util.*;

public class p14425 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            trie.insert(s);
        }
        for (int i = 0; i < m; i++) {
            answer = trie.find(br.readLine()) == true ? answer + 1 : answer;
        }
        System.out.println(answer);
    }

    static class TrieNode {
        TreeMap<Character, TrieNode> childNodes = new TreeMap<>();
        boolean isLastCharacter;
    }

    static class Trie {
        TrieNode rootNode;
        Trie() {
            rootNode = new TrieNode();
        }
        void insert(String word) {
            TrieNode thisNode = rootNode;
            for (int i = 0; i < word.length(); i++){
                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
            }
            thisNode.isLastCharacter = true;
        }

        boolean find(String word) {
            TrieNode thisNode = rootNode;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (thisNode.childNodes.get(c) == null) return false;
                thisNode = thisNode.childNodes.get(c);
            }
            return thisNode.isLastCharacter ? true : false;
        }
    }
}