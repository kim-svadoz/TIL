import java.util.Arrays;

public class LC36_ValidSudoku_BackTracking {

	public static void main(String[] args) {
		//�־��� �������� ��ȿ�� ���������� Ȯ���ϴ� �޼ҵ�.
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
