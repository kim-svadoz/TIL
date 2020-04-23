package LeetCode;

import java.util.Arrays;

class LC37_SudokuSolver {
	//백 트래킹
	//정답을 찾을 때 까지
	//	모든 경우의 수를 전진하면서, 스도쿠 유효성을 어기지 않는지 확인하면서 탐색
	//	더이상 나아갈길이 없으면
	//	한칸 뒤로 후퇴
	public void solveSudoku(char[][] board) {
		backtrack(board, 0);
	}
	
	
	// => 코드이상있음. 다시풀기.
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
		//룰의 종류, 가로/세로/섭그리드
		for(int i=0; i<3; i++) {
			//한줄의 규칙
			for(int j=0; j<9; j++) {
				Arrays.fill(b, false);
				for(int k=0; k<9; k++) {
					char cur= '.';
					if(i == 0) {
						//가로
						cur=board[j][k];
					}else if(i == 1) {
						//세로
						cur=board[k][j];
					}else {
						//섭그리드
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
