package programmers;

public class getMiddleNumber_12903 {

	public static void main(String[] args) {
		String s = "abcde";
		solution(s);
	}
	
	public static String solution(String s) {
		String answer = "";
	    int size = s.length();
	    String[] myString = new String[size];
	    
	    for(int i=0; i<size; i++) {
	    	myString[i] = s.split("")[i];
	    }
	    
     	int half = size/2;
     
     	if(size%2 == 0){ // Â¦¼ö
     		return myString[half-1]+myString[half];
     	}else if(size%2 ==1 ){ //È¦¼ö
            return myString[half];
     	}
	      
	      
	    return null;
	}

}
