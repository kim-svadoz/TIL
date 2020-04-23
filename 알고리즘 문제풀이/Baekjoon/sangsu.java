package baekjoon;

import java.util.Scanner;

public class sangsu {
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int[] num1 = new int[3];
		int[] num2 = new int[3];

		int a = key.nextInt();
		int b = key.nextInt();

		num1[2] = a % 100 % 10;  
		num1[1] = a % 100 / 10;
		num1[0] = a / 100;

		num2[2] = b % 100 % 10;  
		num2[1] = b % 100 / 10;
		num2[0] = b / 100;

		int[] tmp1 = new int[3];
		int[] tmp2 = new int[3];

			tmp1[0] = num1[2];
			num1[2] = num1[0];
			num1[0] = tmp1[0];

			tmp2[0] = num2[2];
			num2[2] = num2[0];
			num2[0] = tmp2[0];

		if(num1[0] > num2[0]) {
			System.out.print(num1[0]*100+num1[1]*10+num1[2]);
		}else if(num1[0] < num2[0]) {
			System.out.print(num2[0]*100+num2[1]*10+num2[2]);
		}else if(num1[0] == num2[0]) {
			if(num1[1] > num2[2]) {
				System.out.print(num1[0]*100+num1[1]*10+num1[2]);
			}else if(num1[1] < num2[1]) {
				System.out.print(num2[0]*100+num2[1]*10+num2[2]);
			}
		}
	}
}