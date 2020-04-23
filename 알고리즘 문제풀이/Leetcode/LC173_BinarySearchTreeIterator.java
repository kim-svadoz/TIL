package LeetCode;

import java.util.Stack;

import javax.swing.tree.TreeNode;
/*
바이너리 서치트리를 인오더순회를하면 그게 결국 오름차순 정렬된 값을 반환하게 된다.
left self right
Binary Search Tree : 왼쪽 섭트리는 나보다 다 작고, 오른쪽 섭트리는 나보다 다 크고.

어느상황에서 BST를 쓰는지 알아보자.
*/
public class LC173_BinarySearchTreeIterator {
	Stack<TreeNode> stack = new Stack<>();
	
	public LC173_BinarySearchTreeIterator(TreeNode root) {
		while(root!=null) {
			stack.push(root);
			root=root.left;
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public int next() {
		TreeNode node = stack.pop();
		if(node.right != null) {
			TreeNode cur = node.right;
			while(cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}
		}
	}

}
