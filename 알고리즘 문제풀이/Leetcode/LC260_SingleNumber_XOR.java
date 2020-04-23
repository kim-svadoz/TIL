package LeetCode;

public class LC260_SingleNumber_XOR {
		//  & : 비트 AND 연산자로 양쪽 비트가 모두 1일 때만 결과가 1이고 그렇지 않으면 0
		// | : 비트 OR 연산자로 양쪽 비트 중 어느하나라도 1이면 결과가 1이고 모두 0일때만 0
		// ^ : 비트 XOR 연산자로 양쪽 비트가 서로 다를때만 1이고 그렇지 않으면 0
		// ~ : 모든 비트값을 반대로(0->1 , 1->0)
	public static void main(String[] args) {
		/* Given an array of numbers nums, in which exactly two elements appear only once
		 *  and all the other elements appear exactly twice. Find the two elements that appear only once.
		 * 
		 *  + << 공간복잡도 고려하기 >>
		 *  	=> 공간복잡도를 고려안한다면 HashMap으로 간단하게 풀이가능
		*/
	}
	/*
	XOR
	1. a a b b c d 전부다 XOR = c^d
	2. c^d 적어도 한 비트는 1
	3. 그 1비트자리에서 c:0 d:1 혹은 그 반대
	4. 그 1비트자리를 기준으로 모든 원소를 두 그룹으로 나눌 수 있음
	5. 각 그룹마다 유일하게 등장하는 원소는 하나!
	*/

	
	public static int[] singleNumber(int[] nums) {
		int xor = 0;
		for(int num:nums) xor^=num;
		
		int idx=0;
		for(int i=0; i<32; i++) {
			if(((xor >> i ) & 1) == 1) {
				idx=1;
				break;
			}
		}
		
		int xor1=0;
		int xor2=0;
		
		for(int num : nums) {
			if(((num>>idx)&1)==1) {
				xor1^=num;
			}else {
				xor2^=num;
			}
		}
		
		int[] ret = new int[2];
		ret[0] = xor1;
		ret[1] = xor2;
		return ret;
	}

}
