package LeetCode;

public class LC938_RangeSumOfBST {
	// 1 2 [ 3 4 5 6 7 8 9 10 ] 11 12
	/* 0. root = null return 0;
	 * 1. root.val<L -> ������ ��Ʈ���� Ž��
	 * 2. root.val>R -> ���� ��Ʈ���� Ž��
	 * 3. L<=root.val<=R -> ���ʼ�Ʈ��+����+������ ��Ʈ��
	*/
	public int rangeSumBST(TreeNode root, int L, int R) {
		if(root==null) return 0;
		else if(root.val<L) {
			return rangeSumBST(root.right, L, R);
		}else if(root.val>R) {
			return rangeSumBST(root.left, L, R);
		}else {
			return rangeSumBST(root.left, L, R) + root.val + rangeSumBST(root.right, L, R);
		}
	}
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
}
