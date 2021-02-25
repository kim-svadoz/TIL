package programmers;

import java.util.ArrayList;

public class KAKAO_Intern2020_01 {
	public static void main(String[] args) {
		int[][] board = 
						{
							{0,0,0,0,0},
							{0,0,1,0,3},
							{0,2,5,0,1},
							{4,2,4,4,2},
							{3,5,1,3,1}
						};
		
		int[] moves = {1,5,3,5,1,2,1,4};
				
		solution(board, moves);		
		
	}
	
	public static int solution(int[][] board, int[] moves) {
		int result=0;
		int size = board.length;
		int size2 = moves.length;
		ArrayList<Integer> tmp = new ArrayList<>();
		ArrayList<Integer> tmp2 = new ArrayList<>();
		
		
		for(int k=0; k<size2; k++) {
			for(int i=0; i<size; i++) {
				if(board[i][moves[k]-1] != 0) {
					tmp.add(board[i][moves[k]-1]);
					board[i][moves[k]-1] = 0;
					break;
				}
			}
		}
		System.out.println(tmp);
		int tmplength = tmp.size();
		rebuild(tmp);
		result = tmplength-tmp.size();
		System.out.println(moves.length);
		System.out.println("¹Ù²ÛÈÄ======"+tmp);
		System.out.println("result===="+result);
		
		return result;
	}
	
	public static int rebuild(ArrayList<Integer> tmp) {
		int result=0;
		for(int i=0; i<tmp.size()-1; i++) {
			if(!tmp.isEmpty()) {
				if(tmp.get(i) == tmp.get(i+1)) {
					tmp.remove(i);
					tmp.remove(i);
					rebuild(tmp);
				}
			}
		}
		
		return result;
	}
	
	
}
