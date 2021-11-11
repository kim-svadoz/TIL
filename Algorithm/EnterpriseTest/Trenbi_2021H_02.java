import java.util.*;

public class Trenbi_2021H_02 {
    public static void main(String[] args) {
        String P = "PM 11:59:59";
        int n = 1;
        solution(P, n);
    }

    public static String solution(String p, int n) {
        String aop = p.split(" ")[0];
        String time = p.split(" ")[1];
        int cur = stoi(aop, time);
        cur += n;

        cur %= 86400;
        String answer = itos(cur);

        System.out.println(answer);
        return answer;
    }

    public static int stoi(String AMorPM, String s) {
        int ret = 0;

        String[] arr = s.split(":");
        ret += Integer.parseInt(arr[2]); // sec;
        ret += Integer.parseInt(arr[1]) * 60; // min

        int tmp = Integer.parseInt(arr[0]);

        if (AMorPM.equals("PM")) {
            tmp = tmp < 12 ? 12 + tmp : tmp;
            ret += tmp * 60 * 60;
        } else {
            ret += (tmp % 12) * 60 * 60;
        }
        
        return ret;
    }

    public static String itos(int num) { // 24시간 단위로 변환
        int hour = num / (60 * 60);
        int min = num / 60 % 60;
        int sec = num % 60;

        String h = hour + "";
        h = h.length() == 1 ? "0" + h : h;

        String m = min + "";
        m = m.length() == 1 ? "0" + m : m;

        String s = sec + "";
        s = s.length() == 1 ? "0" + s : s;

        return h+":"+m+":"+s;
    }
}
