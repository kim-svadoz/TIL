package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC103_BinaryTreeZigZagTraversal {

	public static void main(String[] args) {

	}
	// 레벨 별 스택 사용
	// 플래그 변수 사용 -> 탐색방향을 결정
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
				// 현재 레벨 노드들, 그 자손들 처리
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
