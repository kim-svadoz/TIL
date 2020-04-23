package baekjoon;

import java.util.Scanner;

public class ImChef {
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int[][] chef = new int[5][4];
		int[] score = new int[5];
		//int max = score[0];
		int max = -1;
		int num = 0;
		
		for (int i = 0; i < 5; i++) {
			int count = 0;
			for (int j = 0; j < 4; j++) {
				chef[i][j] = key.nextInt();
				count = count + chef[i][j];
			}
			score[i] = count;
		}
		for (int i = 0; i < score.length; i++) {
			if (max < score[i]) {
				max = score[i];
				num = i+1;
			}
		}
		System.out.println(num+" "+max);
	}
}
