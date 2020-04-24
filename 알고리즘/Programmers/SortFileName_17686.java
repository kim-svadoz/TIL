package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class SortFileName_17686 {

	public static void main(String[] args) {
		String[] files= {"img01.png", "img12.png", "img1.png", "IMG2.png", "img10.JPG"};
		solution(files);
	}
	
	public static String[] solution(String[] files) {
		return sort(files);
	}
	
	public static String[] sort(String[] input) {
		Arrays.sort(input, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				String head1 = s1.split("[0-9]")[0];
				s1 = s1.replace(head1, "");
				head1 = head1.toUpperCase();
				
				String tempNum ="";
				for(char c:s1.toCharArray()) {
					if(Character.isDigit(c) && tempNum.length()<5) {
						tempNum +=c;
					}else {
						break;
					}
				}
				int num1 = Integer.parseInt(tempNum);
				
				String head2 = s2.split("[0-9]")[0];
				s2 = s2.replace(head2, "");
				head2 = head2.toUpperCase();
				
				tempNum ="";
				for(char c:s2.toCharArray()) {
					if(Character.isDigit(c) && tempNum.length()<5) {
						tempNum +=c;
					}else {
						break;
					}
				}
				int num2 = Integer.parseInt(tempNum);
				
				
				return head1.equals(head2) ? num1 - num2 : head1.compareTo(head2);
			}
			
		});
		
		return input;
	}

}
