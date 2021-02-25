package Exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HyundaiCard03 {
	public static void main(String[] args) {
		String[] registered_list = {"cow", "cow1", "cow2", "cow3", "cow4", "cow9", "cow8", "cow7", "cow6", "cow5"};
		String new_id = "cow";
		solution(registered_list, new_id);
	}

	 public static String solution(String[] registered_list, String new_id) {
	        String answer = "";
	        boolean state = false;
	        boolean check = true;
	        
        	String S = new_id.split("[0-9]")[0];
        	int x =0;
        	for(int i=0; i<new_id.length(); i++) {
        		if(S.split("")[S.length()-1].equals(new_id.split("")[i])) {   
        			x = i;
        		}
        	}
        	String N = new_id.substring(x+1, new_id.length());
        	System.out.println(S); //ace
        	System.out.println(N); //15
        	//id 판별 
        	if(S.length() >= 3 && S.length() <= 6) {
        		
        		for(int i=0; i<N.length(); i++) {
        			if(!N.split("")[i].equals("0")) {
        				check = true;
        				break;
        			}else {
        				check = false;
        				break;
        			}
        		}
        		check = true;
        	}
        	
        	Queue<String> q = new LinkedList<>();	
        	for(int i=0; i<registered_list.length; i++) {
        		q.add(registered_list[i]);
        	}
        	int n ;
        	if(N.equals("")) {
    			n = 0;
    		}else {
    			n = Integer.parseInt(N+"");
    		}
        	
        	while(!state) {
        		if(check==true) {
            		if(!q.contains(new_id)) { // 등록 안되어 있으므로 바로 사용할 수 있음
                		answer = new_id;
                		state = true;
                	}else { // 등록이 되어있으므로 새로운 아이디 추천
                		n++;
                		
                		N = Integer.toString(n);
                		String rec = S+N;
                		if(!q.contains(rec)) {
                			answer = rec;
                			state = true;
                		}
                		
                	}
            	}
        	}
        	
        	//System.out.println("answer===="+answer);
	        return answer;
	    }
	 
}
