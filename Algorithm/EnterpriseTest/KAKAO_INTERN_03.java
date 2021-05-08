package Exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class KAKAO_INTERN_03 {

	public static void main(String[] args) {

		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		
		solution(gems);
	}
	
	public static int[] solution(String[] gems) {
        int[] answer = {};
        int len = gems.length;
        ArrayList<String> list = new ArrayList<>();
        Map<ArrayList<Integer>, String> map = new HashMap<>();
        for(String s:gems) {
        	if(!list.contains(s)) list.add(s);
        }
        int size = list.size(); // size = 4;
        
        for(int i=0; i<size; i++) {
        	System.out.println(list.get(i));
        	ArrayList<Integer> pos = new ArrayList<>();
        	for(int j=0; j<gems.length; j++) {
        		if(list.get(i)==gems[j]) {
        			pos.add(j+1);
        			map.put(pos, list.get(i));
        		}
        	}
        	System.out.println(map.get(pos));
        }
        Queue<ArrayList<String>> q = new LinkedList<>();
        int cnt=0;
        int chk = 0;
        int start = 0;
        int end = 0;
        int up=0;
        
        q.add(list);
        
        while(!q.isEmpty()) {
        	for(int i=0; i<list.size(); i++) {
        		ArrayList<String> myGems = new ArrayList<>();
        		for(int j=chk; j<size+chk+up; j++) {
        			//if(chk > len-size) break;
        			
        			myGems.add(gems[j]);
        			if(myGems.contains(q.poll())) {
        				q.remove();
        			}
        			break;
        		}
        		break;
        	}
        	start = chk;
        	end = chk+size+up;
        }
        System.out.println("start:"+start);
        System.out.println("end:"+end);
        
        return answer;
    }
	
	public static class Gem{
		String gem;
		int pos;
		public Gem(String gem, int pos) {
			this.gem = gem;
			this.pos = pos;
		}
	}
}
