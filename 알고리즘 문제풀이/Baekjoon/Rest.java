package baekjoon;

import java.util.Scanner;

public class Rest {
	public static void main(String[] args) {
		int count = 0;
		Scanner key = new Scanner(System.in);
		int[] myarr = new int[10];
		int[] tmp = new int[42];
		for(int i=0; i<myarr.length; i++) {
			myarr[i] = key.nextInt();
		}
		for(int i=0; i<myarr.length;i++) {
			for(int j=0; j<tmp.length;j++) {
				if(j == myarr[i]%42) {	
					tmp[j] ++;
				}
			}
		}
		for(int j=0; j<tmp.length;j++) {
			if(tmp[j]!=0) {
				count++;
			}
		}
		System.out.println(count);
	}
}
