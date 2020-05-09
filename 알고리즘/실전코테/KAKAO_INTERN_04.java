package Exam;

public class KAKAO_INTERN_04 {
	public static int N;
	public static int a,b;
	public static int[][] mBoard;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) {
		int[][] board = {
				{0, 0 ,1, 0},
				{0, 0 ,0, 0},
				{0, 1, 0 ,1},
				{1, 0, 0 ,0}
		};
		solution(board);
	}
	
	public static int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        mBoard = new int[N][N];
        mBoard = board;
        //직선도로는 100원 코너는 500원
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		
        	}
        }
        
        dfs(0, 0, mBoard);
        return answer;
    }
	
	public static void dfs(int x, int y, int[][] mBoard) {
		if(x==N-1 && y==N-1) {
			
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && ny>=0 && nx<N && ny<N) {
				if(mBoard[nx][ny]==0) {
					
				}
			}
		}
	}
	
	public static class Pair {
		int rx;
		int ry;
		public Pair(int rx, int ry) {
			this.rx = rx;
			this.ry = ry;
		}
	}

}
