package programmers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Camouflage_42578 {

	public static void main(String[] args) {
		String[][] clothes = {
			{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}
		};
		solution(clothes);
	}
	
	public static int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> hash = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
        	String key = clothes[i][1];
        	if(!hash.containsKey(key)) hash.put(key, 1);
        	else hash.put(key, hash.get(key) + 1);
        }
        Iterator<Integer> it = hash.values().iterator();
        while(it.hasNext()) {
        	answer *= it.next().intValue()+1;
        }
        
        return answer-1;
    }

}
