package programmers;

import java.util.ArrayList;

public class News_Clustering_17677 {
	public static void main(String[] args) {
		solution("FRANCE", "FRENCH");//Å×½ºÆ®
	}
	public static int solution(String str1, String str2) {
        ArrayList<String> str1Array = new ArrayList<String>();
        ArrayList<String> str2Array = new ArrayList<String>();

        for(int i = 0; i < str1.length()-1; i++) {
            String temp = str1.substring(i, i+2).toLowerCase();
            if(temp.matches("^[a-z]*$")) {
                str1Array.add(temp);    
            }

        }

        for(int i = 0; i < str2.length()-1; i++) {
            String temp = str2.substring(i, i+2).toLowerCase();
            if(temp.matches("^[a-z]*$")) {
                str2Array.add(temp);    
            }
        }


        ArrayList<String> sum = new ArrayList<String>();
        ArrayList<String> con = new ArrayList<String>();

        for(int i = 0; i < str1Array.size(); i++) {
            for(int j = 0; j < str2Array.size(); j++) {
                if(str1Array.get(i).equals(str2Array.get(j))) {
                    con.add(str1Array.get(i));
                    str1Array.remove(i);
                    str2Array.remove(j);
                    i--;
                    j = -1;
                    break;
                }
            }
        }

        sum.addAll(str1Array);
        sum.addAll(str2Array);
        sum.addAll(con);

        float zacquard;
        if(str1Array.size()==0 & str2Array.size()==0) {
            zacquard = 65536;
        } else {
            float conNumber = con.size();
            float sumNumber = sum.size();
            zacquard = conNumber / sumNumber ;
            zacquard = zacquard * 65536;
        }

        int answer = (int) zacquard;
        return answer;
    }
}
