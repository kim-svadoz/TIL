package LeetCode;

public class LC53_MaximumSubarray {

	public static void main(String[] args) {

	}
	
	public int maxSubArray(int[] nums) {
		// d[i] : i번째 원소가 마지막 원소인 부분배열의 합 중 최대값
		// d[2] : max(nums[2], nums[1]+nums[2], nums[0]+nums[1]+nums[2])
		int[] d = new int[nums.length];
		d[0] = nums[0];
		
		for(int i=1; i<nums.length; i++) {
			//update d[i]
			d[i] = Math.max(d[i-1]+nums[i], nums[i]); 
		}
		
		int max = d[0];
		for(int num:d) {
			if(num>max) max = num;
		}
		return max;
	}

}
