/*
			����Լ� �̿��ؼ� DFS(Depth-First-Search)����
			����Լ� ; ��忡 �湮 ������ ��� - �ڽĵ��� ������� ��� ȣ��
			�ڽĵ��� ȣ������� ���������� �ڱ⸦ ����ϰ� �ڽĵ��� ���ȣ��
			��ȯ�ϱ����� �ڽĵ��� ���� ȣ���ϱ� ������ ���ȣ��� ���̿켱 �˻��� ����
			
			ȣ��Ǹ� �ڱ��ڽ��� ���� ����ϰ�
			dfsR(0)
				dfsR(1)
					dfsR(2)
						dfsR(4)
							dfsR(3)
								dfsR(5)
									dfsR(6)
										dfsR(8)
											dfsR(7) - ����
					
			���⼭ ���ð� �ٸ����� �ڽ��� �ϳ� �̻��� ��쿡 
			- ������ �װ� ���� ȣ���� �ϱ� ������ �ڽ��� �� �������� ���� �ְ� ���� ����� ������
			- ���ȣ���� ���������� ȣ���ϱ� ������ ���� ���� ��µȴ�.
 */

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
		// now?? end?? n ��° ������?
		// ���� ä��� �ִ� ������ ������ now, ������ ������ ������ end
		// now�� end���� ���� �Լ��� ����.
		
	}
}



