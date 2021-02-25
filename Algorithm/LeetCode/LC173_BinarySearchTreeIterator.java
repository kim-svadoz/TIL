package LeetCode;

import java.util.Stack;

import javax.swing.tree.TreeNode;
/*
���̳ʸ� ��ġƮ���� �ο�����ȸ���ϸ� �װ� �ᱹ �������� ���ĵ� ���� ��ȯ�ϰ� �ȴ�.
left self right
Binary Search Tree : ���� ��Ʈ���� ������ �� �۰�, ������ ��Ʈ���� ������ �� ũ��.

�����Ȳ���� BST�� ������ �˾ƺ���.
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
