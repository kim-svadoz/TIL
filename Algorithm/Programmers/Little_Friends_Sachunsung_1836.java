package programmers;

/*
		 * for(int i=0;)
		 * 
		 * String[][] board = { {"B", "D", "A"}, {"C", "*", "A"}, {"C", "D", "B"} };
		 */
//ArrayList<String> tmp = new ArrayList<String>();
/* Queue<String> tmp = new LinkedList<String>();
		for(int i=0; i<board.length;i++) {
			for(int j=1; j<board.length;j++) {
				if(board[j-1][i].equals(board[j][i])) {
					if(board[j-1][i].equals(".")) {
		
						
					}else {
						for(int k=0; k<tmp.size(); k++) {
							tmp.offer(board[j-1][i]);
							board[j-1][i] = ".";
							board[j][i] = ".";
						}
					}
				}
			}
		}
		System.out.println(tmp); */

// board ют╥б : ["DBA", "C*A", "CDB"]
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	
	public String solution(int m, int n, String[] board) {
		String answer = "";
		String re = "";
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = board[i].charAt(j);
				if (c >= 65 && c <= 90 && !re.contains(c + "")) {
					re += c;
				}
			}
		}
		char[] carr = re.toCharArray();
		Arrays.sort(carr);
		re = new String(carr);

		List<int[]> directs = new ArrayList<int[]>();
		for (int z = 0; z < re.length(); z++) {
			String aim = re.charAt(z) + "";
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (aim.equals(board[i].charAt(j) + "")) {
						directs.add(new int[] { i, j });
					}
				}
			}
		}

		int cnt = re.length();
		while (cnt > 0) {
			for (int i = 0; i < directs.size(); i += 2) {
				if (checkPossible(board, directs.get(i), directs.get(i + 1), re.charAt(i / 2) + "")) {
					for (int j = 0; j < board.length; j++)
						board[j] = board[j].replaceAll(re.charAt(i / 2) + "", ".");
					answer += re.charAt(i / 2);
					directs.remove(i);
					directs.remove(i);
					re = re.replace(re.charAt(i / 2) + "", "");
					cnt--;
					break;
				}
				if (i == directs.size() - 2)
					return "IMPOSSIBLE";
			}
		}
		int a = 0;
		return answer;
	}

	private boolean checkPossible(String[] board, int[] directs1, int[] directs2, String charAt) {
		int oper1 = directs1[0] < directs2[0] ? 1 : directs1[0] > directs2[0] ? -1 : 0;
		int oper2 = directs1[1] < directs2[1] ? 1 : directs1[1] > directs2[1] ? -1 : 0;
		int curr[] = new int[] { directs1[0], directs1[1] };
		int currOper = oper1;
		int w_h = 0;
		while (true) {
			if (curr[0] == directs2[0]) {
				w_h = 1;
				currOper = oper2;
			}
			curr[w_h] += currOper;
			if (curr[0] == directs2[0] && curr[1] == directs2[1])
				return true;
			if (!(board[curr[0]].charAt(curr[1]) + "").equals("."))
				break;

		}
		oper1 = -oper1;
		oper2 = -oper2;
		w_h = 0;
		currOper = oper1;
		curr = new int[] { directs2[0], directs2[1] };
		while (true) {
			if (curr[0] == directs1[0]) {
				w_h = 1;
				currOper = oper2;
			}
			curr[w_h] += currOper;
			if (curr[0] == directs1[0] && curr[1] == directs1[1])
				return true;

			if (!(board[curr[0]].charAt(curr[1]) + "").equals("."))
				break;
		}
		return false;
	}
}
