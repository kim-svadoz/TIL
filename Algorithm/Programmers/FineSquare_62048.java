package programmers;

public class FineSquare_62048 {

	public static void main(String[] args) {

	}
	
	public long solution(int w, int h) {
        long answer = 1;
        
        long w1 = Long.valueOf(w);
        long h1 = Long.valueOf(h);
        
        return (w1 * h1) - (w1 + h1 - gcd(w1, h1));
    }
    
    public long gcd(long w, long h) {
        long big, small;
        
        if(h == 0)
           return w;
        else
           return gcd(h, w % h); 
    }

}
