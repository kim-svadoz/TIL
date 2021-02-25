package Exam;

import java.util.ArrayList;

public class KAKAO_INTERN_01 {

	public static void main(String[] args) {
		int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		String hand = "left";
		
		solution(numbers, hand);
	}
	
	public static String solution(int[] numbers, String hand) {
        String answer = "";
        String[][] pad = new String[4][3];
        int length = numbers.length;
        ArrayList<Pair> leftHand = new ArrayList<>();
        ArrayList<Pair> rightHand = new ArrayList<>();
        int cnt=0;
        pad[3][0]="*";
        pad[3][1]="0";
        pad[3][2]="#";
        for(int i=0; i<3; i++) {
        	for(int j=0; j<3; j++) {
        		cnt++;
    			pad[i][j] = cnt+"";
        	}
        }
        for(int i=0; i<4; i++) {
        	for(int j=0; j<3; j++) {
        		System.out.print(pad[i][j]);
        	}
        	System.out.println();
        }
        
        leftHand.add(new Pair(0, 3));
        rightHand.add(new Pair(2, 3));
        
        for(int n:numbers) {
        	if(n==1 || n==4 || n==7) {
        		answer += "L";
        		if(n==1) {
        			leftHand.add(new Pair(0, 0));
        		}else if(n==4) {
        			leftHand.add(new Pair(0, 1));
        		}else {
        			leftHand.add(new Pair(0, 2));
        		}
        		
        	}else if(n==3 || n==6 || n==9) {
        		answer += "R";
        		if(n==3) {
        			rightHand.add(new Pair(2, 0));
        		}else if(n==6) {
        			rightHand.add(new Pair(2, 1));
        		}else {
        			rightHand.add(new Pair(2, 2));
        		}
        	}else {
        		//거리계산
        		if(dist(leftHand, rightHand, n) == "L") {
        			answer += "L";
        			if(n==2) {
        				leftHand.add(new Pair(1, 0));
        			}else if(n==5) {
        				leftHand.add(new Pair(1, 1));
        			}else if(n==8) {
        				leftHand.add(new Pair(1, 2));
        			}else {
        				leftHand.add(new Pair(1, 3));
        			}
        		}else if(dist(leftHand, rightHand, n) == "R") {
        			answer += "R";
        			if(n==2) {
        				rightHand.add(new Pair(1, 0));
        			}else if(n==5) {
        				rightHand.add(new Pair(1, 1));
        			}else if(n==8) {
        				rightHand.add(new Pair(1, 2));
        			}else {
        				rightHand.add(new Pair(1, 3));
        			}
        		}else if(dist(leftHand, rightHand, n) == "X"){
        			if(hand.equals("right")) {
        				answer += "R";
        				if(n==2) {
            				rightHand.add(new Pair(1, 0));
            			}else if(n==5) {
            				rightHand.add(new Pair(1, 1));
            			}else if(n==8) {
            				rightHand.add(new Pair(1, 2));
            			}else {
            				rightHand.add(new Pair(1, 3));
            			}
        			}else {
        				answer += "L";
        				if(n==2) {
        					leftHand.add(new Pair(1, 0));
            			}else if(n==5) {
            				leftHand.add(new Pair(1, 1));
            			}else if(n==8) {
            				leftHand.add(new Pair(1, 2));
            			}else {
            				leftHand.add(new Pair(1, 3));
            			}
        			}
        		}
        	}
        }
        System.out.println(answer);
        return answer;
    }
	
	//왼손과 오른손의 현재 위치를 담을 배열이 필요해보임..?!ㄴ
	
	//거리계산
	public static String dist(ArrayList<Pair> leftHand, ArrayList<Pair> rightHand, int n) {
		ArrayList<Pair> mP = new ArrayList<>();
		
		int leftX = leftHand.get(leftHand.size()-1).x;
		int leftY = leftHand.get(leftHand.size()-1).y;
		int rightX = rightHand.get(rightHand.size()-1).x;
		int rightY = rightHand.get(rightHand.size()-1).y;
		System.out.println(n+"번째"+" 왼손 위치:"+leftX+","+leftY);
		System.out.println(n+"번째"+" 오른손 위치:"+rightX+","+rightY);
		System.out.println();
		
		if(n==2) {
			Pair disPair = new Pair(1, 0);
			mP.add(disPair);
		}else if(n ==5) {
			Pair disPair = new Pair(1, 1);
			mP.add(disPair);
		}else if(n ==8) {
			Pair disPair = new Pair(1, 2);
			mP.add(disPair);
		}else {
			Pair disPair = new Pair(1, 3);
			mP.add(disPair);
		}
		int Leftdistance = Math.abs(mP.get(mP.size()-1).x-leftX)+Math.abs(mP.get(mP.size()-1).y-leftY);
		int Rightdistance = Math.abs(mP.get(mP.size()-1).x-rightX)+Math.abs(mP.get(mP.size()-1).y-rightY);
		
		return (Leftdistance > Rightdistance) ? "R" : ((Leftdistance==Rightdistance)? "X" : "L" );
	}
	
	public static class Pair {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
