import java.util.Arrays;

class LC37_SudokuSolver {
	//�� Ʈ��ŷ
	//������ ã�� �� ����
	//	��� ����� ���� �����ϸ鼭, ������ ��ȿ���� ����� �ʴ��� Ȯ���ϸ鼭 Ž��
	//	���̻� ���ư����� ������
	//	��ĭ �ڷ� ����
	public void solveSudoku(char[][] board) {
		backtrack(board, 0);
	}
	
	
	// => �ڵ��̻�����. �ٽ�Ǯ��.
	public boolean backtrack(char[][] board, int idx) {
		if(idx==81) return true;
		int row = idx / 9;
		int col = idx % 9;
		char cur = board[row][col];
		if(cur != '.') {
			return backtrack(board, idx+1);
		}else {
			for(int i=1; i<=9; i++) {
				board[row][col] = (char)i;
				if(isValidSudoku(board)) {
					boolean b = backtrack(board, idx + 1);
					if(b) return b;
				}
			}
			board[row][col] = '.';
			return false;
		}
	}
	
	public static boolean isValidSudoku(char[][] board) {
		boolean[] b = new boolean[9];
		//���� ����, ����/����/���׸���
		for(int i=0; i<3; i++) {
			//������ ��Ģ
			for(int j=0; j<9; j++) {
				Arrays.fill(b, false);
				for(int k=0; k<9; k++) {
					char cur= '.';
					if(i == 0) {
						//����
						cur=board[j][k];
					}else if(i == 1) {
						//����
						cur=board[k][j];
					}else {
						//���׸���
						cur = board[j/3*3 + k/3][j%3*3 + k%3];
					}
					if(cur=='.') continue;
					int val = Character.getNumericValue(cur);
					if(b[val-1]) return false;
					b[val-1] = true;
				}
			}
		}
		return true;
	}
}
