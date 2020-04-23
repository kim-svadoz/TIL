package Line;

import java.util.*;

public class line01 {

	public static void main(String[] args) {
		String input = "if (Count of eggs is 4.) {Buy milk.}";
		solution(input);
	}
	
	public static int solution(String inputString) {
		int answer = -1;
		int open=0;
		int closed=0;
		String[] input = inputString.split("");
		List<String> list = new ArrayList<>();
		
		
		for(int i=0; i<input.length; i++) {
			if(input[i].equals("(") || input[i].equals("{") || input[i].equals("[") || input[i].equals("<")) {
				open++;
			}
			if(input[i].equals(")") || input[i].equals("}") || input[i].equals("]") || input[i].equals(">")) {
				closed++;
			}
		}
		if(open != closed) {
			return -1;
		}else{
			for(int i=0; i<input.length; i++) {
				if(input[i].equals(")") || input[i].equals("}") || input[i].equals("]") || input[i].equals(">")) {
					return -1;
				}else{
					for(int j=0; j<input.length; j++) {
						if(input[j].equals("(") || input[j].equals("{") || input[j].equals("[") || input[j].equals("<")){
							list.add(input[j]);
							input[j] = "x";
						}else if(input[j].equals(")") || input[j].equals("}") || input[j].equals("]") || input[j].equals(">")) {
							list.add(input[j]);
							input[j] = "x";
						}
					}
					break;
				}
			}
		}
		answer = list.size() / 2;
		System.out.println(answer);
		
		return answer;
	}

}
