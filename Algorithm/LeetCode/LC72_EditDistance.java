public class LC72_EditDistance {

	public static void main(String[] args) {

	}
	
	public static int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		//d[i][j]
		//wrod1�� i������ �κй��ڿ��� word2�� j������ �κй��ڿ��� ��ü�ϱ� ���� �ּ� �۾� �� 
		//abc, def d[1][1] : a->d 1
		int[][] d = new int[len1+1][len2+1];
		//************* �ʱ�ȭ
		//i : word1�� �κй��ڿ�
		//j : ""
		for(int i=0; i<=len1; i++) {
			d[i][0] = i;
		}
		for(int i=0; i<=len2; i++) {
			d[0][i] = i;
		}
		/*
		 * 			a
		 * 		0	1
		 * b	1	2
		 *
		*/		
		//************ ����
		for(int i=1; i<=len1; i++) {
			for(int j=1; j<=len2; j++) {
				if(word1.charAt(i-1)==word2.charAt(j-1)) {
					d[i][j] = d[i-1][j-1];
				}else {
					d[i][j] = Math.min(d[i-1][j-1], Math.min(d[i-1][j], d[i][j-1]))+1;
				}
			}
		}
		return d[len1][len2];
	}

}
