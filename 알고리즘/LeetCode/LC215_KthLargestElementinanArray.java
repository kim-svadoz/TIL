package LeetCode;

import java.util.PriorityQueue;

public class LC215_KthLargestElementinanArray {

	//k번째로 큰 숫자를 찾아라!
	//우선순위 큐 : offer poll peek
	//자바같은 경우 : natural ordering, integer같은 경우 오름차순 정렬이 기본
	//offer : 3 2 1 5 4 -> poll : 1 2 3 4 5
	//시간복잡도 offer/poll O(logn)
	public static void main(String[] args) {
		int[] input = {3, 2, 1, 5, 6, 4};
		int k = 2;
		solution(input, k);
	}
	//문제풀이전략
	//큐에는 k개의 원소를 보관
	//이제껏 본 원소중 가장 큰 k개
	//큐의 맨 앞에는 k개중 가장 작은 것이 있다.
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
