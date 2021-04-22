/*
    2020 카카오 블라인드 4번 가사 검색
    트라이 (Trie)
    HashMap으로 풀었는데, 효율성에서 하나가 안맞는다.
    통과하려면 맵보다는 배열을 써야할 것 같다. (하지만 귀찮아서 pass..)

    문제 키 포인트는 정방향 트라이와 역방향 트라이 두개를 만들어야 한다.
    또한, 글자의 개수마다 트라이를 만들어줘야한다.
*/
import java.util.*;
class Solution {
    class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        int cnt;
    }
    
    class Trie {
        TrieNode rootNode;
        
        public Trie() {
            rootNode = new TrieNode();
        }
        
        void insert(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                thisNode = thisNode.childNodes.computeIfAbsent(c, key -> new TrieNode());
                thisNode.cnt++;
            }
            thisNode.cnt++;
        }
        
        int search(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '?') return thisNode.cnt;
                
                if (thisNode.childNodes.get(c) == null) return 0;
                thisNode = thisNode.childNodes.get(c);
            }
            return thisNode.cnt;
        }
    }
    
    Trie[] trie = new Trie[10000];
    Trie[] R_trie = new Trie[10000];
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int ansIdx = 0;
        
        for (String s : words) {
            int idx = s.length() - 1;
            
            if (trie[idx] == null) {
                trie[idx] = new Trie();
                R_trie[idx] = new Trie();
            }
            trie[idx].insert(s);
            s = new StringBuilder(s).reverse().toString();
            R_trie[idx].insert(s);
        }
        
        for (String s : queries) {
            int idx = s.length() - 1;
            
            if (trie[idx] == null) {
                answer[ansIdx++] = 0;
                continue;
            }
            if (s.charAt(0) != '?') {
                answer[ansIdx++] = trie[idx].search(s);
            } else {
                s = new StringBuilder(s).reverse().toString();
                answer[ansIdx++] = R_trie[idx].search(s);
            }
            
        }
        
        return answer;
    }
}