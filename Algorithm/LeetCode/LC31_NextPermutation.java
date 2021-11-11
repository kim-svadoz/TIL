public class LC31_NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
    1 3(a) 5 4 4(b)
    ->
    1 4 3 4 5
    
    5 4 3 2 1
    
    1. ������ ��Ʈ�� �ƴ°� ?
    2. �߰� �޸� ������� �ʰ� ������ �� �ִ°�
    3. �ٽ� ��ǵ��ƿ��� ���ܻ�Ȳ�� �����ϴ°�
    */
    public void nextPermutation(int[] nums){
        // �ڿ������� Ž���ϸ鼭 ����������  ������ �ε����� Ȯ��(a)
        int a = nums.length-2;
        while(a>=0 && nums[a] >= nums[a+1]) a--;
        
        if(a != -1){
            // �ٽ� �ڿ������� Ž���ϸ鼭 a���� ū ù��° �ε����� Ȯ��(b)
            int b = nums.length-1;               
            while(nums[a] >= nums[b]) b--;
            // a�� b�� ����
            swap(nums, a, b);
        }
        // a+1�������� ������ �������� ����
        int start = a + 1;
        int end = nums.length - 1;
        while(start < end){
            swap(nums, start++, end--);
        }
    }
    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
