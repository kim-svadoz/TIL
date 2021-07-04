/*
    2021-07-04
    네이버 웹툰 개발 챌린지 1차 코딩 테스트(Back-End) 
*/
import java.io.*;
import java.util.*;

public class NaverWebtoon2021H_02 {
    public static void main(String[] arsg) throws IOException {
        String s = "abcxyasdfasdfxyabc";
        solution(s);
    }

    public static String[] solution(String s) {
        Trie trie = new Trie();

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length() - i; j++) {
                trie.insert(s.substring(i, i + j + 1), i);
            }
        }
        trie.dfs(trie.rootNode, new StringBuilder());
        String[] answer = {};
        return answer;
    }

    static boolean isPalindrome(String str) {
        int s = 0, e = str.length() - 1;
        
        while (s <= e) {
            if (str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
        return true;
    }

    static class TrieNode {
        TreeMap<Character, TrieNode> childNodes = new TreeMap<>();
        int index = -1, cnt = 0;
        boolean isLastCharacter;
    }

    static class Trie {
        TrieNode rootNode;
        Trie() {
            rootNode = new TrieNode();
        }
        void insert(String word, int index) {
            TrieNode thisNode = rootNode;
            for (int i = 0; i < word.length(); i++){
                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
            }
            thisNode.index = index;
            thisNode.cnt++;
            thisNode.isLastCharacter = true;
        }

        // 경로를 추적하기 위해서 처음부터 dfs로 돈다
        void dfs(TrieNode thisNode, StringBuilder sb) {
            Object[] keys = thisNode.childNodes.keySet().toArray();

            for (Object key : keys) {
                char keyCh = (char) key;
                TrieNode nextNode = thisNode.childNodes.get(keyCh);
                if (nextNode != null) {
                    sb.append(keyCh);
                    if (nextNode.isLastCharacter == true) {
                        System.out.println(sb.toString());
                    }
                    dfs(nextNode, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
