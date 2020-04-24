package programmers;
import java.util.Arrays;
import java.util.Comparator;
public class SortStrings {

	public static void main(String[] args) {
		String[] strings = { "sun", "bed", "car"};
		int n = 1;
		solution(strings, n);
	}
	
	public static String[] solution(String[] strings, int n) {
		Arrays.sort(strings, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.charAt(n) > s2.charAt(n)) return 1;
				else if(s1.charAt(n) == s2.charAt(n)) return s1.compareTo(s2);
				else if(s1.charAt(n) < s2.charAt(n)) return -1;
				else return 0;
			}
		});
		return strings;
	}

}
