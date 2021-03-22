import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class p1451 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] rectangle = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();
            
            for(int j = 0; j < m; j++) {
                rectangle[i][j] = temp[j] - '0';
            }
        }
        
        long max = 0;
        
        for(int i = 1; i < n; i++) {
            long a = getRectangleSum(0, i, 0, m, rectangle);
            
            max = getMaxVertical(a, rectangle, max, i, n, 0, m);    
            max = getMaxHorizontal(a, rectangle, max, i, n, 0, m);
        }
                
        for(int i = n - 1; i > 0; i--) {
            long a = getRectangleSum(i, n, 0, m, rectangle);
            
            max = getMaxVertical(a, rectangle, max, 0, i, 0, m);        
        }
        
        for(int i = 1; i < m; i++) {
            long a = getRectangleSum(0, n, 0, i, rectangle);
            
            max = getMaxHorizontal(a, rectangle, max, 0, n, i, m);
            max = getMaxVertical(a, rectangle, max, 0, n, i, m);        
        }
        
        for(int i = m - 1; i > 0; i--) {
            long a = getRectangleSum(0, n, i, m, rectangle);
            
            max = getMaxHorizontal(a, rectangle, max, 0, n, 0, i);
        }
        
        System.out.println(max);
    }
 
    private static long getMaxHorizontal(long a, int[][] rectangle, long max, int sI, int eI, int sJ, int eJ) {
        
        for(int j = sI + 1; j < eI; j++) {
            long b = getRectangleSum(sI, j, sJ, eJ, rectangle);
            long c = getRectangleSum(j, eI, sJ, eJ, rectangle);
            
            long tmp = a * b * c;
            
            if(max < tmp) {
                max = tmp;
            }
        }
        
        return max;
    }
 
    private static long getMaxVertical(long a, int[][] rectangle, long max, int sI, int eI, int sJ, int eJ) {
        
        for(int j = sJ + 1; j < eJ; j++) {
            long b = getRectangleSum(sI, eI, sJ, j, rectangle);
            long c = getRectangleSum(sI, eI, j, eJ, rectangle);
            
            long tmp = a * b * c;
            
            if(max < tmp) {
                max = tmp;
            }
        }
        
        return max;
    }
    
    private static long getRectangleSum(int sI, int eI, int sJ, int eJ, int[][] rectangle) {
        long sum = 0;
        
        for(int i = sI; i < eI; i++) {
            for(int j = sJ; j < eJ; j++) {
                sum += rectangle[i][j];
            }
        }
        
        return sum;
    }
}
