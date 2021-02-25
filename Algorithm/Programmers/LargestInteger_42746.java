package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class LargestInteger_42746 {

	public static void main(String[] args) {
		int[] numbers = {6, 10, 2};
		solution(numbers);
	}
	
	public static String solution(int[] numbers) {
        String answer = "";
        
        String[] arr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            arr[i] = (String.valueOf(numbers[i]));
        }
        Arrays.sort(arr, new Comparator<String>(){
           public int compare(String s1, String s2){
               return (s2+s1).compareTo(s1+s2);
           } 
        });
        
        if(arr[0].equals("0")) return "0";
        for(String str : arr){
            answer += str;
        }
        
        System.out.println(answer);
        return answer;
    }

}
