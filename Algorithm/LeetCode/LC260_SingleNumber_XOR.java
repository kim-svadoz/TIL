public class LC260_SingleNumber_XOR {
		//  & : ��Ʈ AND �����ڷ� ���� ��Ʈ�� ��� 1�� ���� ����� 1�̰� �׷��� ������ 0
		// | : ��Ʈ OR �����ڷ� ���� ��Ʈ �� ����ϳ��� 1�̸� ����� 1�̰� ��� 0�϶��� 0
		// ^ : ��Ʈ XOR �����ڷ� ���� ��Ʈ�� ���� �ٸ����� 1�̰� �׷��� ������ 0
		// ~ : ��� ��Ʈ���� �ݴ��(0->1 , 1->0)
	public static void main(String[] args) {
		/* Given an array of numbers nums, in which exactly two elements appear only once
		 *  and all the other elements appear exactly twice. Find the two elements that appear only once.
		 * 
		 *  + << �������⵵ �����ϱ� >>
		 *  	=> �������⵵�� �������Ѵٸ� HashMap���� �����ϰ� Ǯ�̰���
		*/
	}
	/*
	XOR
	1. a a b b c d ���δ� XOR = c^d
	2. c^d ��� �� ��Ʈ�� 1
	3. �� 1��Ʈ�ڸ����� c:0 d:1 Ȥ�� �� �ݴ�
	4. �� 1��Ʈ�ڸ��� �������� ��� ���Ҹ� �� �׷����� ���� �� ����
	5. �� �׷츶�� �����ϰ� �����ϴ� ���Ҵ� �ϳ�!
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
