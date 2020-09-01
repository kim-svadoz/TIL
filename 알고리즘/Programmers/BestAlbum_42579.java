package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BestAlbum_42579 {

	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		solution(genres, plays);
	}
	
	public static int[] solution(String[] genres, int[] plays) {
        Map<String, Object> genresMap = new HashMap<>();		// <장르, 곡정보>
        Map<String, Integer> playMap = new HashMap<>();		// <장르, 총 장르 재생수>
        ArrayList<Integer> resultAL = new ArrayList<>();
        for(int i=0; i<genres.length; i++) {
        	String key = genres[i];
        	HashMap<Integer, Integer> infoMap; 				// 곡정보: <곡 고유번호, 재생횟수>
        	
        	if(genresMap.containsKey(key)) {
        		infoMap = (HashMap<Integer, Integer>)genresMap.get(key);
        	} else {
        		infoMap = new HashMap<Integer, Integer>();
        	}
        	
        	infoMap.put(i, plays[i]);
        	genresMap.put(key, infoMap);
        	
        	// 재생 수 
        	if(playMap.containsKey(key)) {
        		playMap.put(key, playMap.get(key) + plays[i]);
        	} else {
        		playMap.put(key, plays[i]);
        	}
        }
        int mCnt = 0;
        Iterator it = sortByValue(playMap).iterator();
        
        while(it.hasNext()) {
        	String key = (String)it.next();
        	Iterator indexIt = sortByValue((HashMap<Integer, Integer>)genresMap.get(key)).iterator();
        	int playsCnt = 0;
        	
        	while(indexIt.hasNext()) {
        		resultAL.add((int)indexIt.next());
        		mCnt++;
        		playsCnt++;
        		if(playsCnt > 1) break;
        	}
        }
        
        int[] answer = new int[resultAL.size()];
        
        for(int i=0; i<resultAL.size(); i++) {
        	answer[i] = resultAL.get(i).intValue();
        }
        
        return answer;
    }
	
	private static ArrayList sortByValue(final Map map) {
		ArrayList<Object> keyList = new ArrayList();
		keyList.addAll(map.keySet());
		
		Collections.sort(keyList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable) v2).compareTo(v1);
			}
			
		});
		
		return keyList;
	}

}
