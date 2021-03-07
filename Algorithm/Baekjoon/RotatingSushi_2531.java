import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RotatingSushi_2531 {
	static int N, d, k, c;
	static int[] arr, visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 8, ������ ��
		d = Integer.parseInt(st.nextToken());	// 30, �ʹ� ������
		k = Integer.parseInt(st.nextToken());	// 4, �����ؼ� �Դ� ���� ��
		c = Integer.parseInt(st.nextToken());	// 30, ���� ��ȣ
		
		/*
		 * 7 9 7 30 2 7 9 25
		 */
		visit = new int[d+1];
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(slide());
	}
	
	private static int slide() {
		// sliding window ����
		int total = 0, max = 0;
		// ó�� �ʱ� 4���� ������ �ʱ�ȭ �۾�
		for(int i=0; i<k; i++) {
			if(visit[arr[i]] == 0) total ++;
			visit[arr[i]]++;
		}
		max = total; // 3
		for(int i=1; i<N; i++) {
			if(max <= total) {
				// ���������� �߿� ������ ���ٸ� +1 �߰����ְ�
				if(visit[c]==0) max = total + 1;
				// ���������� �̹� �Ծ��ٸ� �״��
				else max = total;
			}
			
			// �� ���ʿ��� ������ �ϳ� ����
			visit[arr[i-1]]--;
			// �׷��� ��ü ���� ������ ����
			if(visit[arr[i-1]] == 0) total--;
			
			// �״��� ������ ���� ���� ������ �Ծ��ٰ� �߰����ְ�
			if(visit[arr[(i+k-1) % N]] == 0) total++;
			visit[arr[(i+k-1) % N]]++;
		}
		
		return max;
	}
	

}
