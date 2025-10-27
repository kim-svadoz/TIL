import java.io.*;
import java.util.*;
public class p2331 {
    static BufferedReader br;
    static StringTokenizer st;
    static int A, P, dp[];
    static boolean visit[];
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        list.add(A);

        while (true) {
            int temp = list.get(list.size() - 1);

            int result = 0;

            while (temp != 0) {
                result += (int) Math.pow(temp % 10, (double) P);
                temp /= 10;
            }

            if (list.contains(result)) {
                int ans = list.indexOf(result);
                System.out.println(ans);
                break;
            }

            list.add(result);
        }


    }

}
