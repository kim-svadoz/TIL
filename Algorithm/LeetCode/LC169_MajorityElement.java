package LeetCode;

public class LC169_MajorityElement {

	public static void main(String[] args) {

	}
	
	public int majorityElement(int[] nums){
        int x = 0, cnt = 0;
        for(int num : nums){
            if(cnt == 0) {
               x=num;
               cnt++;
            } else if (x == num){
                cnt++;
            } else {
                cnt--;
            }
        }
        return x;
    }
}
