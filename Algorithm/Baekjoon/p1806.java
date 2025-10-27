import java.io.*;
import java.util.*;
public class p1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0, hi = 0, sum = 0, min = 100001;
        while (true) {
            if (sum >=  s) {
                sum -= arr[lo++];
                min = Math.min(min, (hi - lo) + 1);
            } else if (hi >= n) {
                break;
            } else {
                sum += arr[hi++];
            }
        }
        if(min == 100001) {
			bw.write(0+"\n");
		}else {
			bw.write(min+"\n");
		}
        bw.flush();
		br.close();
		bw.close();
    }
}
