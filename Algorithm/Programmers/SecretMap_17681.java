package programmers;

public class SecretMap_17681 {

	public static void main(String[] args) {
		int n = 5;
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		solution(n, arr1, arr2);
	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
	      String[] answer = new String[n];
	      String[][] map1 = new String[n][n];
	      String[][] map2 = new String[n][n];
	      String[][] ansMap = new String[n][n];
	      String oneline;
	      
	      for(int i=0; i<n; i++) {
	    	  oneline = Integer.toBinaryString(arr1[i]);
	    	  if(oneline.length()!=n) {
	    		  String add = Integer.toBinaryString(arr1[i]);
	    		  oneline = add;
	    		  while(true) {
	    			  oneline = "0" + oneline;
	    			  if(oneline.length()==n) break;
	    		  }
	    	  }
	    	  for(int j=0; j<n; j++) {
	    		  map1[i][j] = oneline.split("")[j];
	    	  }
	      }
	      for(int i=0; i<n; i++) {
	    	  oneline = Integer.toBinaryString(arr2[i]);
	    	  if(oneline.length()!=n) {
	    		  String add = Integer.toBinaryString(arr2[i]);
	    		  oneline = add;
	    		  while(true) {
	    			  oneline = "0" + oneline;
	    			  if(oneline.length()==n) break;
	    		  }
	    	  }
	    	  for(int j=0; j<n; j++) {
	    		  map2[i][j] = oneline.split("")[j];
	    	  }
	      }
	      
	      for(int i=0; i<n; i++) {
	    	  String x = "";
	    	  for(int j=0; j<n; j++) {
	    		  if(map1[i][j].equals("1") || map2[i][j].equals("1")) { // º®
	    			  ansMap[i][j] = "#";
	    			  x += ansMap[i][j];
	    		  }else {
	    			  ansMap[i][j] = " ";
	    			  x += ansMap[i][j];
	    		  }
	    	  }
	    	  answer[i] = x;
	    	  System.out.println(answer[i]);
	      }
	      
	      return answer;
	  }

}
