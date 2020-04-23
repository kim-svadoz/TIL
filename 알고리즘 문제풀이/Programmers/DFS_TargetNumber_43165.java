/*
			재귀함수 이용해서 DFS(Depth-First-Search)구현
			재귀함수 ; 노드에 방문 데이터 출력 - 자식들을 순서대로 재귀 호출
			자식들이 호출받으면 마찬가지로 자기를 출력하고 자식들을 재귀호출
			반환하기전에 자식들을 먼저 호출하기 때문에 재귀호출로 깊이우선 검색이 가능
			
			호출되면 자기자신을 먼저 출력하고
			dfsR(0)
				dfsR(1)
					dfsR(2)
						dfsR(4)
							dfsR(3)
								dfsR(5)
									dfsR(6)
										dfsR(8)
											dfsR(7) - 종료
					
			여기서 스택과 다른점은 자식이 하나 이상인 경우에 
			- 스택은 쌓고 나서 호출을 하기 때문에 자식중 맨 마지막에 들어가는 애가 먼저 출력이 되지만
			- 재귀호출은 정방향으로 호출하기 때문에 위가 먼저 출력된다.
 */
package programmers;

public class DFS_TargetNumber_43165 {
	public static void main(String[] args) {
		int[] numbers= {1,1,1,1,1};
		int end = numbers.length;
		dfs(0, end, numbers, 0, 3);
			
	}

	public static int dfs(int now, int end, int[] numbers, int num, int target) {
		if(now == end) {
			if(num==target) {
				return 1;
			}else {
				return 0;
			}
		}
		return dfs(now+1, end, numbers, num+numbers[now], target) + 
				dfs(now+1, end, numbers, num-numbers[now], target);
		// now?? end?? n 번째 연산자?
		// 현재 채우고 있는 연산자 순서가 now, 마지막 연산자 순서가 end
		// now가 end까지 가면 함수를 종료.
		
	}
}



