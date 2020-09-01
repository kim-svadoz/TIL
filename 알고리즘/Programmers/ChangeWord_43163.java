package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ChangeWord_43163 {

	public static int[][] conn;
	public static int[] dist;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int solution(String begin, String target, String[] words) {
		int answer = 0;
		int target_index = -1;
		int words_length = words.length;
		conn = new int[words.length][words.length];
		dist = new int[words.length];
		
		for(int w1 = 0; w1 < words_length; w1++) {
			dist[w1] = Integer.MAX_VALUE;
			
			if(words[w1].equals(target)) {
				target_index = w1;
			}
			
			for(int w2 = w1 + 1; w2 <words_length; w2++) {
				if(is_change_able(words[w1], words[w2])) {
					conn[w1][w2] = 1;
					conn[w2][w1] = 1;
				}
			}
		}
		
		// 목표단어가 없어서 변환할 수 없는 경우
		if(target_index == -1) return 0;
		
		// BFS 시작
		Queue<int[]> q = new LinkedList<>();
		
		for(int w1 = 0; w1 < words_length; w1++) {
			// 변경할 수 있는 경우
			if(is_change_able(begin, words[w1])) {
				q.add(new int[] {w1, 1});
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// target에 도달한 경우
			if(cur[0] == target_index) {
				answer = cur[1];
				break;
			} else {
				for(int i=0; i<words_length; i++) {
					if(conn[cur[0]][i] == 1 && dist[i] > cur[1] + 1) {
						dist[i] = cur[1] + 1;
						q.add(new int[] {i, cur[1] + 1});
					}
				}
			}
		}
		
		return answer;
	}
	
	public static boolean is_change_able(String a, String b) {
		int diff = 0;
		
		for(int i=0; i<a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) {
				diff++;
			}
			
			if(diff >= 2) return false;
		}
		
		return true;
	}

}
