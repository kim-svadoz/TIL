import java.util.*;
public class p1783{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int cnt =0;
		if(n == 1)
			cnt = 1;
		else if(n == 2)
			cnt = (int)Math.min(4, (m + 1) / 2);
		else
		{
			if(m < 7)
				cnt = Math.min(4, m);
			else
				cnt = m - 2;
		}
		System.out.println(cnt);
	}
}