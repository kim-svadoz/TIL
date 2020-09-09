package LeetCode;

public class LC152_MaximumProductSubarray {

	public static void main(String[] args) {
		int[] nums = {2, 3, -2, 4};
		//int[] nums = {-2, 1, -1};
		solution(nums);

	}
	
	public static int solution(int[] nums) {
		//d[i][0] -> +, 0~i max product
		//d[i][1] -> -, 0~i min product
		int[][] d = new int[nums.length][2];
		
		d[0][0] = nums[0];
		d[0][1] = nums[0];
		
		for(int i=1; i<nums.length; i++) {
			int c = nums[i];
			
			d[i][0] = Math.max(c, Math.max(c * d[i-1][0], c * d[i-1][1]));
			d[i][1] = Math.min(c, Math.min(c * d[i-1][0], c * d[i-1][1]));
		}
		int max = d[0][0];
		for(int i=0; i<nums.length; i++)
			if(d[i][0]>max) max = d[i][0];
		
		System.out.println(max);
		return max;
	}

}
