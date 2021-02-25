package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SumOfNumbers_1789 {

	public static long S;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Long.parseLong(br.readLine());
      
        System.out.println(solve(S));
    }
    public static int solve(long n){
        long sum = 0;
        int addNum = 0;
        while(n >= sum){
            sum += (++addNum);
        }
        return sum==n ? addNum : addNum-1;
    }
	
	

}
