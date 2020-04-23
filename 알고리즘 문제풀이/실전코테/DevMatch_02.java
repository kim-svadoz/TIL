package Exam;

import java.util.LinkedList;
import java.util.Queue;

public class DevMatch_02 {

	public static void main(String[] args) {
		int[][] office = { {5, -1, 4}, 
				{6, 3, -1},
				{2, -1, 1}
		};
		int r = 1;
		int c = 0;
		String[] move = {"go","go","right","go","right","go","left","go"};
		//d   => 0 0 1 0 1 0 3 1
		//현재  => 0 0 1 1 2 2 5 6
		//가공  => 0 0 1 1 2 2 1 2
		solution(office, r, c, move);
	}
	
	public static int solution(int[][] office, int r, int c, String[] move) {
		int answer = 0;
		int N = office.length;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int[] direction = new int[move.length];
		/*for(int i=0; i<move.length; i++) {
			if(move[i].equals("go")) {
				direction[i] = 0;
			}else if(move[i].equals("right")) {
				direction[i] = 1;
			}else if(move[i].equals("left")) {
				direction[i] = 3;
			}else {
				direction[i] = 2;
			}
		}*/
		
		for (int i = 0; i < move.length; i++) {
	         try {
	            if (move[i].equals("go")) {
	               direction[i] = direction[i - 1];
	            } else if (move[i].equals("right")) {
	               direction[i] = direction[i - 1] + 1;
	            } else {
	               direction[i] = direction[i - 1] - 1;
	            }
	            if (direction[i] > 3)
	               direction[i] = 0;
	            if (direction[i] < 0)
	               direction[i] = 3;
	         } catch (ArrayIndexOutOfBoundsException e) {

	         }
	         System.out.print(direction[i]);
	      }

		
		answer = answer + office[r][c];
		office[r][c] =0;
		Robot robot = new Robot(r, c, 0);
		Queue<Robot> q = new LinkedList<>();
		q.add(robot); // (1, 0, 0) 이 들어옴
		
		for(int i=0; i<move.length; i++) { // 첫 go
			Robot qRobo = q.remove(); //
			for(int j=0; j<4; j++) {
				if(direction[i]==j) { // 0북 1동 2남 3서  // direction =  0 0 1 ...
					int nx =0;
					int ny =0;
					int d =0;
					if(move[i].equals("go")) {
						nx = qRobo.x + dx[j]; // 1 + 0
						ny = qRobo.y + dy[j]; // 0 + 1
						d = (direction[i]); // 0 + 0
					}else {
						nx = qRobo.x;
						ny = qRobo.y;
						d = (direction[i]); // 0 + 0
					}
					if(nx>=0 && ny>=0 && nx<N && ny<N) {
						if(office[nx][ny]!=-1) {
							
							answer = answer + office[nx][ny];
							office[nx][ny] = 0;
							q.add(new Robot(nx, ny, d));
							break;
						}else {
							q.add(new Robot(qRobo.x, qRobo.y, d));
							break;
						}
					}else {
						System.out.println("else");
						q.add(new Robot(qRobo.x, qRobo.y, d));
						break;
					}
				}
			}
		}
		
		System.out.println("answer============"+answer);
		return answer;
	}
	
	public static class Robot{
		int x;
		int y;
		int d;
		public Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
