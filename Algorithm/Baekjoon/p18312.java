import java.io.*;
import java.util.StringTokenizer;
public class p18312 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int h = 0, m = 0, s = 0, cnt = 0;
        while (true) {
            String time = ((h < 10) ? "0" + h : "" + h) + ((m < 10) ? "0" + m : "" + m) + ((s < 10) ? "0" + s : "" + s);
            if (time.indexOf(""+k) != -1) cnt++;
            if (h == n && m == 59 && s == 59) break;
            s++;
            if (s == 60) {
                s = 0;
                m++;
            }
            if (m == 60) {
                m = 0;
                h++;
            }
        }
        System.out.println(cnt);
    }

}
