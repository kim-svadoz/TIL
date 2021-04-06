/*
	수가 자연수일 때는 투 포인터로 구할 수 있다.
	하지만 이 문제는 음수와 0이 있기 때문에 구간 Map을 이용한 구간 합을 사용해야 한다.
*/
import java.io.*;
import java.util.*;
public class p2015 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] psum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            psum[i] = psum[i - 1] + arr[i];
        }
        // i		:	0	1	2	3
        // psum[i]	:	2	0	2	0
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long ret = 0;
        for(int i = 1; i <= n; i++) {
            ret += map.getOrDefault(psum[i] - k, 0);
            
            // key에 해당하는 value가 없으면 1, 있으면 value + 1
            map.put(psum[i], map.getOrDefault(psum[i], 0) + 1);
        }

        System.out.println(ret);
    }

}