package programmers;

public class LongestPalindrome_12904 {

	public static void main(String[] args) {
		String s = "abcdcba";
		solution(s);
	}
	
	public static int solution(String s) {
		int answer = 1;
		int len = s.length();
		char[] word = s.toCharArray();
		
		int[][] dp = new int[len][len];
		
		//1자리
		for(int i=0; i<len; i++) {
			dp[i][i]= 1;
		}
		
		//2자리
		for(int i=0; i<len-1; i++) {
			if(word[i] == word[i+1]) {
				dp[i][i+1] = 1;
				answer = 2;
			}
		}
		//3자리 이상
		for(int k=3; k<=len; k++) {
			for(int i=0; i<=len-k; i++) {
				int j = i + k - 1;
				if(word[i]==word[j] && dp[i+1][j-1]==1) {
					dp[i][j] = 1;
					answer = Math.max(answer, k);
				}
			}
		}
		System.out.println(answer);
		return answer;
	}

}
