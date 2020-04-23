package LeetCode;

import java.util.Arrays;

public class LC36_ValidSudoku_BackTracking {

	public static void main(String[] args) {
		//주어진 스도쿠가 유효한 스도쿠인지 확인하는 메소드.
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
