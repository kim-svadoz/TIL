package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class fibonacci {
	static int zero;
	static int one;
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int T = key.nextInt();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i=0; i<T; i++) {
				tmp.add(key.nextInt());
			}
		int zero[] = new int[41];
		int one[] = new int[41];
		zero[0] = 1;
		zero[1] = 0;
		one[0] = 0;
		one[1] = 1;
		for(int i=2;i<zero.length;i++) {
			zero[i] = zero[i-1] + zero[i-2];
			one[i] = one[i-1] + one[i-2];
		}
		for(int i=0; i<tmp.size(); i++) {
			int temp = tmp.get(i);
			System.out.println(zero[temp]+" "+one[temp]);
		}
	}
}
