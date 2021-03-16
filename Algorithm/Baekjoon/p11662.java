import java.io.*;
import java.util.*;

public class p11662 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        double aX1 = Double.parseDouble(st.nextToken());
        double aY1 = Double.parseDouble(st.nextToken());
        double aX2 = Double.parseDouble(st.nextToken());
        double aY2 = Double.parseDouble(st.nextToken());
        double cX1 = Double.parseDouble(st.nextToken());
        double cY1 = Double.parseDouble(st.nextToken());
        double cX2 = Double.parseDouble(st.nextToken());
        double cY2 = Double.parseDouble(st.nextToken());

        int interval = 1000000;

        double aDX = (aX2 - aX1) / interval;
        double aDY = (aY2 - aY1) / interval;
        double cDX = (cX2 - cX1) / interval;
        double cDY = (cY2 - cY1) / interval;

        double i = -1 * ((aX1 - cX1) * (aDX - cDX) + (aY1 - cY1) * (aDY - cDY)) / (Math.pow(aDX - cDX, 2) + Math.pow(aDY - cDY, 2));

        if (!Double.isNaN(i) && i < interval && i > 0) {
            double min = getDisatnce(aX1 + aDX * i, aY1 + aDY * i, cX1 + cDX * i, cY1 + cDY * i);
            System.out.println(min);
        } else {
            double min1 = getDisatnce(aX1, aY1, cX1, cY1);
            double min2 = getDisatnce(aX2, aY2, cX2, cY2);

            System.out.println(Math.min(min1, min2));
        }
    }

    private static double getDisatnce(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
