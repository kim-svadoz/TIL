package programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChickenDelivery_15686 {
   public static int[][] map;
   public static int N, M;
   public static int answer = Integer.MAX_VALUE;
   public static int[] dx = {-1, 0, 1, 0};
   public static int[] dy = {0, 1, 0, -1};  // 북 동 남 서
   public static ArrayList<City> storeList;
   public static ArrayList<City> personList;
   public static class City{
      int x;
      int y;
      public City(int x, int y) {
         this.x = x;
         this.y = y;
      }
   }
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][N];
      storeList = new ArrayList<City>();
      personList = new ArrayList<City>();
      ArrayList<City> answerList = new ArrayList<City>();
      
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
            if(map[i][j] == 2) {
            	storeList.add(new City(i, j));
            }else if(map[i][j] == 1) {
            	personList.add(new City(i, j));
            }
         }
      }
      
      boolean[] check = new boolean[storeList.size()];
      dfs(0,0, answerList, check);
      System.out.println(answer);
   }	
   
   public static void dfs(int start, int depth, ArrayList<City> answerList, boolean[] check) {
	   // M개의 치킨집을 다 고르면 ( 기저사례 )
	   if(depth==M) {
		   int sum = calAnswer(answerList);
		   answer = Math.min(answer, sum);
		   return;
	   }
	   for(int i=start; i<storeList.size(); i++) {
		   if(check[i]) continue; // 이미 체크되어 있으면 다음 i 증가
		   check[i] = true;
		   answerList.add(storeList.get(i));
		   dfs(i+1, depth+1, answerList, check);
		   answerList.remove(answerList.size()-1);
		   check[i] = false;
	   }
   }
   
   public static int calAnswer(ArrayList<City> answerList) {
	   int sum = 0;
	   for(int i=0; i<personList.size(); i++) {
		   City c1 = personList.get(i);
		   int len = Integer.MAX_VALUE;
		   for(int j=0; j<answerList.size(); j++) {
			   City c2 = answerList.get(j);
			   int temp = Math.abs(c1.x-c2.x)+Math.abs(c1.y-c2.y);
			   len = Math.min(len, temp);
		   }
		   sum += len;
	   }
	   return sum;
   }
}