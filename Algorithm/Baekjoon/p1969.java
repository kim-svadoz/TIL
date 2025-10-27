import java.util.Scanner;

public class p1969 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, n = sc.nextInt(), m = sc.nextInt(), max = 0, hd = 0;
        String []dna = new String[n];
        for(i=0;i<n;i++) dna[i] = sc.next();
        for(i=0;i<m;i++){
        	int a=0,t=0,g=0,c=0;
        	for(j=0;j<n;j++){
        		switch(dna[j].charAt(i)){
        		case 'A' : a++; break;
        		case 'T' : t++; break;
        		case 'G' : g++; break;
        		case 'C' : c++; break;
        		}
        	}
        	max = Math.max(a>c?a:c, g>t?g:t);
        	hd += (n-max);
        	System.out.print(toChar(a,t,g,c,max));
        }
        System.out.println("\n"+hd);
        sc.close();
    }
    private static char toChar(int a, int t, int g, int c, int max){
    	if(max==a) return 'A';
    	else if(max==c) return 'C';
    	else if(max==g) return 'G';
    	else return 'T';
    }
}