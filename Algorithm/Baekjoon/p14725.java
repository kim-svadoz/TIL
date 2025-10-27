import java.io.*;
import java.util.*;

public class p14725 {
    static Scanner sc = new Scanner(System.in);
    static int N;
    public static void main(String[] args) throws IOException {
        N = sc.nextInt();

        // 루트노드 생성
        Trie t = new Trie();
        while (N-- > 0) {
            int m = sc.nextInt();
            String[] tmp = sc.nextLine().split(" ");
            String[] tokens = new String[tmp.length - 1];
            if (tmp.length - 1 >= 0) {
                System.arraycopy(tmp, 1, tokens, 0, tmp.length - 1);
            }
            // 자식 추가
            t.insert(tokens);
        }
        
        // 루트가 현재노드
        TrieNode curNode = t.root;

        // TreeMap에 넣었던 Key 가져오기
        for (String key : curNode.childNodes.keySet()) {
            System.out.println(key);
            show(curNode.childNodes.get(key), 2);
        }
    }

    static class TrieNode {
        // TreeMap을 사용하게 되면 레드-블랙 트리가 구현이 되어 키를 기준으로 자동으로 정렬이 된다.
        TreeMap<String, TrieNode> childNodes = new TreeMap<>();
    }

    static class Trie {
        TrieNode root;
        Trie() {
            // TreeMap의 구조를 가진 루트 노드 생성
            root = new TrieNode();
        }

        void insert(String[] words) {
            TrieNode thisNode = root;
            for (String word : words) {
                thisNode = thisNode.childNodes.computeIfAbsent(word, c -> new TrieNode());
            }
        }
    }

    static void show(TrieNode currentNode, int depth) {
        for (String key : currentNode.childNodes.keySet()) {
            for (int i = 1; i < depth; ++i) {
                System.out.print("--");
            }
            System.out.println(key);
            show(currentNode.childNodes.get(key), depth + 1);
        }
    }
}
