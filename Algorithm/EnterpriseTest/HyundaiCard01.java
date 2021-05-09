import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HyundaiCard01 {

	public static void main(String[] args) {
		String[] purchase = {"2019/01/01 5000", "2019/01/20 15000", "2019/02/09 90000"};
		solution(purchase);
	}
	
	public static int[] solution(String[] purchase) {
		int[] answer = new int[5];
		//int[] day = new int[purchase.length];
		//int[] price = new int[purchase.length];
		List<Integer> day = new ArrayList<>();
		List<Integer> price = new ArrayList<>();
		int[] cur_day = new int[purchase.length-1];
		int cnt; 
		int num =0;
		
		for(int i=0; i<purchase.length; i++) {
			num = Integer.parseInt(purchase[i].split("/")[1]) * 30 - 30;
			int x = Integer.parseInt(purchase[i].split("/")[2].split(" ")[0]);
			day.add(x + num);
			price.add(Integer.parseInt(purchase[i].split("/")[2].split(" ")[1]));
		}
		for(int i=1; i<purchase.length; i++) {
		/*	if(Math.abs(day[i]-day[i-1]) > 30 ) { //30�� �̻� ���̳��� �� �� ��� �ʱ�ȭ
				cur_day[i-1] = day[i]-day[i-1];
			}else {
				cur_day[i-1] = day[i]; 
			}*/
		}
		
		cnt = 0; // �ϼ� �ʱⰪ
		int pay = price.get(0);// 5000
		int payday =  day.get(0);
		System.out.println("payday"+payday);
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=payday-1; i<365; i++) {
			cnt++;
			
			map.put(i, price.get(0));
			//19�� ���� ����� => day[i]-1�� ��ŭ �����
			//for()
			
			//11�� ���� ��� => 
			//��¥���� �� ���� ���� ����־����.
			
			if(pay>=0 && pay<10000) { // �����
				answer[0] += cnt;
			}else if(pay>=10000 && pay < 20000) { // �ǹ�
				//answer[1]
			}else if(pay>=20000 && pay < 50000) { // ���
				//answer[2]
			}else if(pay>=50000 && pay < 100000) { // �÷�Ƽ��
				//answer[3]
			}else { // ���̾�
				//answer[4]
			}
			
		}
		
		return answer;
	}

}
