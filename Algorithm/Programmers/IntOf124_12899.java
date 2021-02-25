package programmers;

public class IntOf124_12899 {

	public static void main(String[] args) {
		int n = 4;
		solution(n);
	}
	
	public static String solution(int n) {
		String[] numbers = {"4", "1", "2"};
		String answer = "";
		int num = n;
		while(num>0) {
			int reminder = num % 3;
			num /= 3;
			if(reminder==0) num--;

			answer = numbers[reminder] + answer;
		}
		System.out.println(answer);
		return answer;
    }

}
