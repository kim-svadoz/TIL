import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC103_BinaryTreeZigZagTraversal {

	public static void main(String[] args) {

	}
	// ���� �� ���� ���
	// �÷��� ���� ��� -> Ž�������� ����
	public static List<List<Integer>> solution(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		if(root==null) return ret;
		
		boolean flag = true;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		
		while(!s.isEmpty()) {
			int size = s.size();
			Stack<TreeNode> newStack = new Stack<>();
			List<Integer> level = new ArrayList<>();
			for(int i=0; i<size; i++) {
				// ���� ���� ����, �� �ڼյ� ó��
				TreeNode node = s.pop();
				level.add(node.val);
				if(flag) {
					if(node.left!=null) newStack.push(node.left);
					if(node.right!=null) newStack.push(node.right);
				}else {
					if(node.right!=null) newStack.push(node.right);
					if(node.left!=null)  newStack.push(node.left);
				}
			}
			ret.add(level);
			s = newStack;
			flag = !flag;
		}
		return ret;
	}
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}

}
