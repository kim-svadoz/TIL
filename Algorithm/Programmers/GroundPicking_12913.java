package programmers;

public class GroundPicking_12913 {
	public static int[][] d, map;
    public static int N;
	public static void main(String[] args) {

	}
	
	public static int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        map = new int[N+1][4];
        for(int i=1; i<=N; i++){
            map[i][0] = land[i-1][0];
            map[i][1] = land[i-1][1];
            map[i][2] = land[i-1][2];
            map[i][3] = land[i-1][3];
        }
        d = new int[N+1][4];
        
        d[1][0] = map[1][0];
        d[1][1] = map[1][1];
        d[1][2] = map[1][2];
        d[1][3] = map[1][3];
        
        for(int i=2; i<=N; i++){
            d[i][0] = map[i][0] + Math.max(d[i-1][1], Math.max(d[i-1][2], d[i-1][3]));
            d[i][1] = map[i][1] + Math.max(d[i-1][0], Math.max(d[i-1][2], d[i-1][3]));
            d[i][2] = map[i][2] + Math.max(d[i-1][0], Math.max(d[i-1][1], d[i-1][3]));
            d[i][3] = map[i][3] + Math.max(d[i-1][0], Math.max(d[i-1][1], d[i-1][2]));
        }
        
        answer = Math.max(d[N][0], Math.max(d[N][1], Math.max(d[N][2], d[N][3])));
        
        return answer;
    }

}
