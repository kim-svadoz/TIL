package LeetCode;

public class LC300_LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] input = {10, 9, 2, 5, 3, 7, 10, 18};
		solution(input);
	}
	// 증가하는 순열이 가장 많은 개수
	public static int solution(int[] nums) {
		if(nums==null || nums.length==0) return 0;
		//점화식
		int[] d = new int[nums.length];
		d[0] = 1;
		
		for(int i=1; i<nums.length; i++) {
			int max = 0;
			for(int j=0; j<i; j++) {
				//nums[[i], nums[j]
				if(nums[j]  < nums[i] && d[j] > max) {
					max = d[j];
				}
			}
			d[i] = max+1;
		}
		
		int max = d[0];
		for (int i = 1; i < nums.length; i++) {
			if(d[i]>max) max = d[i];
		}
		return max;
	}
}
