package LeetCode;

public class LC63_UniquePathII {

	public static void main(String[] args) {
		int[][] obstacleGird = {
				{0, 0, 0},
				{0, 1, 0},
				{0, 0, 0}
		};
		solution(obstacleGird);
	}
	
	public static int solution(int[][] obstacleGird) {
		
		/*
		 d[i][j] = 0,0 -> i,j °æ·ÎÀÇ °¹¼ö
		 return d[x][y];
		 */
		int[][] o = obstacleGird;
		int[][] d = new int[o.length][o.length];
		d[0][0] = 1;
		
		for(int i=0; i<o.length; i++) {
			for(int j=0; j<o[0].length; j++) {
				int cur = o[i][j];
				if(cur == 1) {
					d[i][j] = 0;
				}else {
					if(i>0) d[i][j]+=d[i-1][j];
					if(j>0) d[i][j]+=d[i][j-1];
				}
			}
		}
		
		System.out.println(d[o.length-1][o.length-1]);
		return d[o.length-1][o.length-1];
	}

}
