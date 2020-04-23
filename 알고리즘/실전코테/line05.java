package Line;

import java.util.*;

public class line05 {

	public static void main(String[] args) {
		String[][] dataSource = {
		                         {"doc1", "t1", "t2", "t3"},
		                         {"doc2", "t0", "t2", "t3"},
		                         {"doc3", "t1", "t6", "t7"},
		                         {"doc4", "t1", "t2", "t4"},
		                         {"doc5", "t6", "t100", "t8"}
		                         };
		String[] tags = {"t1", "t2", "t3"};
		solution(dataSource, tags);
	}
	//1순위는 많이 출현한 순서
	//2순위는 글자의 이름
	public static List<String> solution(String[][] dataSource, String[] tags) {
		List<String> answer ;
		int len = dataSource.length;
		int cnt;
		System.out.println(len);
		List<String> val = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		Map<String, Integer> cntMap = new HashMap<>(); 
		
		
		for(int i=0; i<tags.length; i++) {
			for(int j=0; j<len; j++) {
				for(int k=1; k<dataSource[j].length; k++) {
					if(tags[i].equals(dataSource[j][k]) && !val.contains(dataSource[j][0])) {
						val.add(dataSource[j][0]);
					}
				}
			}
			map.put(tags[i], val);
		}
		
		for(int i=0; i<len; i++) {
			cnt=0;
			for(int j=1; j<dataSource[i].length; j++) {
				for(int k=0; k<tags.length; k++) {
					if(tags[k].equals(dataSource[i][j])) {
						cnt++;
					}
				}
			}
			cntMap.put(dataSource[i][0], cnt);
		}
		System.out.println(cntMap);
		
		int cntrank = 0;
		List<Integer> rank = new ArrayList<>();
		for(int i=0; i<len; i++) {
			for(int j=1; j<dataSource[i][j].length(); j++) {
				cntrank = cntMap.get(dataSource[i][0]);
				rank.add(cntrank);
			}
		}
		Collections.sort(rank);
		System.out.println("rank==="+rank);
		
		answer = map.get(tags[0]);
		return answer; // 1 2 4 3 
	}
}
