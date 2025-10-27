import java.io.*;
import java.util.*;

public class p1991 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Tree t = new Tree();
        char data[];
        for (int i = 0; i < N; ++i) {
            data = br.readLine().replaceAll(" ", "").toCharArray();
            t.add(data[0], data[1], data[2]);
        }
        t.preorder(t.root);
        System.out.println();
        t.inorder(t.root);
        System.out.println();
        t.postorder(t.root);
        System.out.println();
    }
    
    static class Node {
        char data;
        Node left, right;
        public Node(char data) {
            this.data = data;
        }
    }

    static class Tree {
        Node root; // 루트노드. 처음엔 null 상태.
        public void add(char data, char leftData, char rightData) {
            if (root == null) { // 루노드가 비어있으면 추가해라.
                if (data != '.') root = new Node(data); // . 이 아니면 노드 생성 후 데이터 삽입

                if (leftData != '.') root.left = new Node(leftData);

                if (rightData != '.') root.right = new Node(rightData);
            }

            // 만약 최초 상태가 아니라면 -> 어디에 들어가야 하는지 찾아야 한다.
            else search(root, data, leftData, rightData);
        }

        private void search(Node root, char data, char leftData, char rightData) {
            // 재귀를 사용 하므로 도착한 노드가 null 이면 종료 => 삽입 위치를 찾지 못한 경우이다.
            if (root == null) return;
            else if (root.data == data) { // 위치를 찾았으면
                if(leftData != '.') root.left = new Node(leftData);
                if(rightData != '.') root.right = new Node(rightData);
            } else { // 아직 찾지 못햇고 탐색할 노드들이 남아있는 경우?
                search(root.left, data, leftData, rightData); // 왼쪽 재귀 탐색
                search(root.right, data, leftData, rightData); // 오른쪽 재귀 탐색
            }
        }

        /* 전위 순회 :: 중앙 -> 좌 -> 우 */
        public void preorder(Node root) {
            System.out.print(root.data); // 가운데 출력
            if(root.left != null) preorder(root.left); // 그 다음 왼쪽
            if(root.right != null) preorder(root.right); // 마지막 오른쪽
        }

        /* 중위 순회 :: 좌 -> 중앙 -> 우 */
        public void inorder(Node root) {
            if(root.left != null) inorder(root.left); // 왼쪽 먼저
            System.out.print(root.data); // 중앙
            if(root.right != null) inorder(root.right); // 마지막 오른쪽

        }

        /* 전위 순회 :: 좌 -> 우 -> 중앙 */
        public void postorder(Node root) {
            if(root.left != null) postorder(root.left); // 왼쪽 먼저
            if(root.right != null) postorder(root.right); // 마지막 오른쪽
            System.out.print(root.data); // 중앙
        }
    }
}
