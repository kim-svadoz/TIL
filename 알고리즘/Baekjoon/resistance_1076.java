package baekjoon;

import java.util.HashMap;
import java.util.Scanner;

public class resistance_1076 {
//단순 HashMap을 이용한 put, get 사용법
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("black", "0,1");
		map.put("brown", "1,10");
		map.put("red", "2,100");
		map.put("orange", "3,1000");
		map.put("yellow", "4,10000");
		map.put("green", "5,100000");
		map.put("blue", "6,1000000");
		map.put("violet", "7,10000000");
		map.put("grey", "8,100000000");
		map.put("white", "9,1000000000");
		
		String first = key.next();
		String second = key.next();
		String third = key.next();
		key.close();
		
		String[] get1 = map.get(first).split(",");
		String[] get2 = map.get(second).split(",");
		String[] get3 = map.get(third).split(",");
		
		Long result = Long.parseLong(get1[0]+get2[0]) * Long.parseLong(get3[1]); 
		System.out.println(result);
	}

}
