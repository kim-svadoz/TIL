package programmers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KAKAO_Intern2020_03 {

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		
		solution(user_id, banned_id);
	}
	
	public static void solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		int cnt;
		String user, banuser;
		ArrayList<String> usertmp = new ArrayList<>();
		ArrayList<String> bantmp = new ArrayList<>();
		int banSize =banned_id.length;
		int userSize =user_id.length;
		
		//banuser
		for(int i=0; i<banSize; i++) {
			user="";
			banuser = "";
			cnt=0;
			for(int j=0; j<banned_id[i].length();j++) {
				bantmp.add(banned_id[i].split("")[j]);
			}
			
			for(int j=0; j<bantmp.size();j++) {
				if(!bantmp.get(j).equals("*")) {
					banuser = banuser + bantmp.get(j);
				}
			}
			
			//validate
			for(int j=0; j<userSize; j++) {
				for(int k=0; k<user_id[j].length();k++) {
					if(banned_id[i].length()==user_id[j].length()) {
						usertmp.add(user_id[j].split("")[k]);
					}else {
						break;
					}
					
					usertmp.get(k);
				}
				System.out.println("usertmp"+usertmp);
					 
				
				
				usertmp.clear();
			}
			
			
			System.out.println(cnt);
			System.out.println("bantmp========="+bantmp);
			System.out.println("banuser========="+banuser);
			System.out.println("user========="+user);
			
			bantmp.clear();
		}
		
        return ;
	}

}
