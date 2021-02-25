package LeetCode;

public class LC31_NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
    1 3(a) 5 4 4(b)
    ->
    1 4 3 4 5
    
    5 4 3 2 1
    
    1. 렉시컬 소트를 아는가 ?
    2. 추가 메모리 사용하지 않고 스왑할 수 있는가
    3. 다시 디ㅗ돌아오는 예외상황을 이해하는가
    */
    public void nextPermutation(int[] nums){
        // 뒤에서부터 탐색하면서 오름차순이  깨지는 인덱스를 확인(a)
        int a = nums.length-2;
        while(a>=0 && nums[a] >= nums[a+1]) a--;
        
        if(a != -1){
            // 다시 뒤에서부터 탐색하면서 a보다 큰 첫번째 인덱스를 확인(b)
            int b = nums.length-1;               
            while(nums[a] >= nums[b]) b--;
            // a랑 b를 스왑
            swap(nums, a, b);
        }
        // a+1에서부터 끝까지 오름차순 정렬
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
