package Exam;

public class EstSoft_03 {
	public static void main(String[] args) {
		int[] A = {1, 2, 3, 4};
		solution(A);
	}
	//////
	public static int solution(int[] A) {
		int cnt = 0;
		int res = 0;
		
		for(int i=0; i<A.length; i++) {
			
			for(int j=i+4; j<=i; j--) {
				if(A[j]-A[j+1]<0 && A[j+1]-A[j+2]<0 && A[j+2]-A[j+3]<0) {
					res = -1;
				}else if(A[j]-A[j+1]>0 && A[j+1]-A[j+2]>0 && A[j+2]-A[j+3]>0) {
					res = -1;
				}else if(A[j]-A[j+1]>0 && A[j+1]-A[j+2]<0 && A[j+2]-A[j+3]>0) {
					res += 0;
				}else if(A[j]-A[j+1]<0 && A[j+1]-A[j+2]>0 && A[j+2]-A[j+3]<0) {
					res += 0;
				}else{
					cnt++;
				}
			}
			res += cnt;
		}
		System.out.println(res);
		return res;
    }
}
