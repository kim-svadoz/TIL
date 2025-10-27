import java.io.*;
import java.util.*;

public class p11651 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] point = new int[N][2];
        for (int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(point, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
            
        });
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < 2; j++) {
                System.out.print(point[i][j]+" ");
            }
            System.out.println();
        }
    }

}

