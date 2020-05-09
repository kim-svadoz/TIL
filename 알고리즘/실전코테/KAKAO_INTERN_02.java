package Exam;

import java.util.ArrayList;

public class KAKAO_INTERN_02 {

	public static void main(String[] args) {
		String expression="100-200*300-500+20";
		
		solution(expression);
	}
	
	public static long solution(String expression) {
        long answer = 0;
        		
        String[] c = expression.split("[0-9]");
        String[] num = expression.split("[*\\-\\+]");
        ArrayList<String> mC = new ArrayList<>();
        
        int p = c.length;
        for(int i=0; i<c.length; i++) {
        	System.out.print(c[i]+" ");
        	if(!mC.contains(c[i])) mC.add(c[i]);
        }
        for(int i=0; i<mC.size(); i++) {
        	System.out.println(mC.get(i));
        }
        //연산자 우선순위 정하기
        
        
        
        System.out.println();
        for(int i=0; i<num.length; i++) {
        	System.out.print(num[i]+" ");
        }
        
        //연산자 우선조합 경우의 수
        
        return answer;
    }
	
	public static void comb(int[] c, int[] num, int n) {
		
	}

}
