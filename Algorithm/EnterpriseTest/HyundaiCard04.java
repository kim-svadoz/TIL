package Exam;

public class HyundaiCard04 {
	public static int[][] map;
	public static void main(String[] args) {
		int[][] macaron= {
				{1,1},
				{2,1},
				{1,2},
				{3,3},
				{6,4},
				{3,1},
				{3,3},
				{3,3},
				{3,4},
				{2,1}
		};
		solution(macaron);
	}
	/* 1 : ºÐÈ«
	 * 2 : ³ë¶û
	 * 3 : º¸¶ó
	 * 4 : ÃÊ·Ï
	*/
	public static String[] solution(int[][] macaron) {
		String[] answer= {};
		map = new int[6][6];
		
		for(int i=0; i<macaron.length; i++) {
			for(int j=0; j<2; j++) {
				int x = macaron[i][j];
				//macaron[i][0]
			}
		}
		
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				if(macaron[i][0]==i) {
					map[i][0] = 1;
				}
			}
		}
		
		return answer;
	}

}
