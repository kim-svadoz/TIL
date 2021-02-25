package programmers;

import java.util.HashMap;

public class NoFullRacing_42576 {

	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		solution(participant, completion);
	}
	
	public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (String player : participant) {
        	hash.put(player, hash.getOrDefault(player, 0) + 1);
        }
        for (String player : completion) {
        	hash.put(player, hash.get(player) -1);
        }
        
        for (String key : hash.keySet()) {
        	if(hash.get(key) != 0){
        		answer = key;
        	}
        }
        return answer;
    }

}
