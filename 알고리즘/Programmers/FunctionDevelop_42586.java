package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionDevelop_42586 {

	public static void main(String[] args) {
		
	}
	
	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> function = new LinkedList<Integer>();
		Queue<Integer> speed = new LinkedList<Integer>();
		for(int i=0; i<progresses.length; i++){
			function.add(progresses[i]);
			speed.add(speeds[i]);
		}

		ArrayList<Integer> answerList = new ArrayList<>();
		while(!function.isEmpty()){
			int size = function.size();

			for(int i=0; i<size; i++){
				int curFunction = function.poll();
				int curSpeed = speed.poll();

				curFunction += curSpeed;

				function.add(curFunction);
				speed.add(curSpeed);
			}

			int tempAnswer = 0;
			for(int i=0; i<size; i++){
				if(function.peek() >= 100){
					tempAnswer++;
					function.poll();
					speed.poll();
				} else {
					break;
				}
			}

			if(tempAnswer != 0){
				answerList.add(tempAnswer);
			}
		}

		int[] answer = new int[answerList.size()];
		for(int i=0; i<answerList.size(); i++){
			answer[i] = answerList.get(i);
		}

		return answer;
    }

}
