package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class soopoja_42840 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] answers = {1,3,2,4,2};
		solution(answers);
	}
	
	public static int[] solution(int[] answers) {
		int[] answer = null;
		
		ArrayList<Integer> man1 = new ArrayList<Integer>();
		ArrayList<Integer> man2 = new ArrayList<Integer>();
		ArrayList<Integer> man3 = new ArrayList<Integer>();
		
		for(int i=0; i<answers.length; i++) {
			if((i+1)%5==1) {man1.add(1);}
			else if((i+1)%5==2) {man1.add(2);}
			else if((i+1)%5==3) {man1.add(3);}
			else if((i+1)%5==4) {man1.add(4);}
			else if((i+1)%5==0) {man1.add(5);}
		}
		int cnt=0;
		for(int i=0; i<answers.length; i++) {
			if((i+1)%2==1) {man2.add(2); cnt++;}
			else if(cnt%4==1) {man2.add(1);}
			else if(cnt%4==2) {man2.add(3);}
			else if(cnt%4==3) {man2.add(4);}
			else if(cnt%4==0) {man2.add(5);}
		}
		for(int i=0; i<answers.length; i++) {
			if((i+1)%10==1 || (i+1)%10==2) {man3.add(3);}
			else if((i+1)%10==3 || (i+1)%10==4) {man3.add(1);}
			else if((i+1)%10==5 || (i+1)%10==6) {man3.add(2);}
			else if((i+1)%10==7 || (i+1)%10==8) {man3.add(4);}
			else if((i+1)%10==9 || (i+1)%10==0) {man3.add(5);}
		}
		
		int[] count = new int[3];
		int man1cnt=0;
		int man2cnt=0;
		int man3cnt=0;
		for(int i=0; i<answers.length; i++) {
			if(answers[i] == man1.get(i)) {man1cnt++;}
		}
		for(int i=0; i<answers.length; i++) {
			if(answers[i] == man2.get(i)) {man2cnt++;}
		}
		for(int i=0; i<answers.length; i++) {
			if(answers[i] == man3.get(i)) {man3cnt++;}
		}
		count[0] = man1cnt;
		count[1] = man2cnt;
		count[2] = man3cnt;
		
		if(count[0] == count[1] && count[0]==count[2]) {
			int maxNum=3;
			answer = new int[maxNum];
			answer[0] = 1;
			answer[1] = 2;
			answer[2] = 3;
		}else if( count[0] > count[1] && count[0] > count[2]) {
			int maxNum=1;
			answer = new int[maxNum];
			answer[0] = 1;
		}else if( count[1] > count[0] && count[1] > count[2]) {
			int maxNum=1;
			answer = new int[maxNum];
			answer[0] = 2;
		}else if( count[2] > count[0] && count[2] > count[1]) {
			int maxNum=1;
			answer = new int[maxNum];
			answer[0] = 3;
		}else if( count[0]==count[1] && count[0]>count[2]) {
			int maxNum=2;
			answer = new int[maxNum];
			answer[0] = 1; 
			answer[1] = 2;
		}else if( count[0]==count[2] && count[0]>count[1]) {
			int maxNum=2;
			answer = new int[maxNum];
			answer[0] = 1;
			answer[1] = 3;
		}else if( count[1]==count[2] && count[1]>count[0]) {
			int maxNum=2;
			answer = new int[maxNum];
			answer[0] = 2;
			answer[1] = 3;
		}
		
		return answer;
	}
}
