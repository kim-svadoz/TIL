import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1072 {
    static long x, y, z;
    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        z = y * 100 / x;
        if (z >= 99) {
            System.out.println(-1);
        } else {
            binarySearch(1, x);
        }
    }

    static void binarySearch(long start, long end) {
        long mid = 0, ratio = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            ratio = (y + mid) * 100 / (x + mid);

            if (ratio > z) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}