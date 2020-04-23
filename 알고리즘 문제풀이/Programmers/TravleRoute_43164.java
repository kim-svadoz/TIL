package programmers;

import java.util.*;

public class TravleRoute_43164 {
	static List<Stack<String>> result;
	static String[][] tickets;

	public static void main(String[] args) {
		String[][] tickets= {{"ICN","JFK"},
							{"HND","IAD"},
							{"JFK","HND"}};
		solution(tickets);
	}
	
	public static String[] solution(String[][] tickets) {
		result = new ArrayList<>();
		
		boolean[] visited = new boolean[tickets.length];
		Stack<String> st = new Stack<>();
		st.push("ICN");
		
		dfs(visited, st, 0);
		
		if(result.size()>1) {
			Collections.sort(result, new Comparator<Stack<String>>() {
				@Override
				public int compare(Stack<String> o1, Stack<String> o2) {
					for(int i=0; i< o1.size(); i++) {
						String s1 = o1.get(i);
						String s2 = o2.get(i);
						
						if(!s1.equals(s2)) {
							return s1.compareTo(s2);
						}
					}
					return 0;
				}
			});
		}
		
		Stack<String> res = result.remove(0);
		String[] answer = new String[res.size()];
		
		for(int i=0; i<answer.length; i++) {
			answer[i] = res.get(i);
		}
		
		return answer;
	}
	
	public static void dfs(boolean[] visited, Stack<String> st, int len) {
		if(len==tickets.length) {
			Stack<String> res = new Stack<>();
			for(String s:st) {
				res.push(s);
			}
			
			result.add(res);
			return;
		}
		
		String arrive = st.peek();
		
		for(int i=0; i<tickets.length; i++) {
			String[] tic = tickets[i];
			
			if(!visited[i] && arrive.equals(tic[0])) {
				st.push(tic[1]);
				visited[i] = true;
				
				dfs(visited, st, len+1);
				
				visited[i] = false;
				st.pop();
			}
		}
	}
}
