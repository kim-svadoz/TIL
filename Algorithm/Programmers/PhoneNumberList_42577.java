package programmers;

public class PhoneNumberList_42577 {

	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		solution(phone_book);
	}
	
	 public static boolean solution(String[] phone_book) {
        boolean answer = true;
        //접두어가 존재하면 false, 존재하지 않으면 true 리턴
        for(int i=0; i<phone_book.length-1; i++) {
        	for(int j=i+1; j<phone_book.length; j++) {
        		if(phone_book[i].startsWith(phone_book[j])) return false;
        		if(phone_book[j].startsWith(phone_book[i])) return false;
        	}
        }
        
        return answer;
    }

}
