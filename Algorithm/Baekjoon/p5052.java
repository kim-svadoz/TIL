/*
    트라이 구현.

    1. Map을 이용
    2. 배열을 이용

    두 가지 방법이 있는데 , 해당 문제에서는 Map을 이용하였다.
    맨 아래에 배열을 이용한 풀이가 있다.
*/
import java.util.*;
import java.io.*;
public class p5052 {
    static int t;
    static Trie trie;
    static class TrieNode {
        Map<Character, TrieNode> childNodes;
        boolean isLeapNode;
        public TrieNode() {
            childNodes = new HashMap<>();
        }
    }
    static class Trie {
        TrieNode rootNode;
        public Trie() {
            rootNode = new TrieNode();
        }
        void insert(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                thisNode = thisNode.childNodes.computeIfAbsent(c, key -> new TrieNode());
            }
            thisNode.isLeapNode = true;
        }
        
        boolean find(String s) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                thisNode = thisNode.childNodes.get(c);
                if (i != s.length() - 1 && thisNode.isLeapNode) {
                    return false;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            trie = new Trie();
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
                trie.insert(arr[i]);
            }
            
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (!trie.find(arr[i])) {
                    flag = false;
                }
            }
            
            if (flag) {
                System.out.println("YES");    
            } else {
                System.out.println("NO");
            }
            
        }
    }
}

/*************** 배열
 * 
import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static boolean NO;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder str = new StringBuilder();

		while (T-- > 0) {
			TrieTree trieTree = new TrieTree();
			ArrayList<String> numbers = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			NO = false;

			for (int i = 0; i < N; i++) {
				String number = br.readLine();
				numbers.add(number);
			}
			numbers.sort(Comparator.comparingInt(String::length));
			for (String number : numbers) {
				if (NO) continue;
				trieTree.insert(number);
			}
			if (NO) str.append("NO").append("\n");
			else str.append("YES").append("\n");
		}
		System.out.print(str);
	}
	private static class Tree
	{
		Tree[] next = new Tree[10];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		Tree root = new Tree();

		private void insert(String number)
		{
			Tree current = root;
			for (int i = 0; i < number.length(); i++) {
				if (current.next[number.charAt(i) - '0'] == null) {
					current.next[number.charAt(i) - '0'] = new Tree();
				} else if (current.next[number.charAt(i) - '0'].isEnd) {
					NO = true;
					break;
				}
				current = current.next[number.charAt(i) - '0'];
			}
			current.isEnd = true;
		}
	}
}

*/