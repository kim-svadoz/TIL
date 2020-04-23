package baekjoon;

import java.util.Scanner;

public class chicchicpokpok {
	public static void main(String[] args) {
		int val[] = new int[4];
		int count[] = new int[5];
		int max = 0;
		count[0] = 0;
		Scanner key = new Scanner(System.in);
		
		for(int i=0;i<val.length;i++) {
			int a, b;
			a = key.nextInt();
			b = key.nextInt();
			val[i] = b-a;
		}
		for (int i = 0; i < val.length; i++) {
			count[i+1] = count[i] + val[i];
			if(count[i+1] > count[i]) {
				max = count[i+1];
				if(i==val.length-1) {
					System.out.println(max);
				}
			}else {
				if(i==val.length-1) {
					System.out.println(max);
				}
			}
		}
	}
}
