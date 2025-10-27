import java.util.PriorityQueue;

public class LC215_KthLargestElementinanArray {

	//k��°�� ū ���ڸ� ã�ƶ�!
	//�켱���� ť : offer poll peek
	//�ڹٰ��� ��� : natural ordering, integer���� ��� �������� ������ �⺻
	//offer : 3 2 1 5 4 -> poll : 1 2 3 4 5
	//�ð����⵵ offer/poll O(logn)
	public static void main(String[] args) {
		int[] input = {3, 2, 1, 5, 6, 4};
		int k = 2;
		solution(input, k);
	}
	//����Ǯ������
	//ť���� k���� ���Ҹ� ����
	//������ �� ������ ���� ū k��
	//ť�� �� �տ��� k���� ���� ���� ���� �ִ�.
	public static int solution(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int num: nums) {
			if(pq.size() < k) {
				pq.offer(num);
			}else {
				if(num > pq.peek()) {
					pq.poll();
					pq.offer(num);
				}
			}
		}
		
		System.out.println(pq.peek());
		return pq.peek();
	}
	
	

}
