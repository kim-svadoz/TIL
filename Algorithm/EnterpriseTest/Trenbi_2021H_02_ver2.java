import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trenbi_2021H_02_ver2 {
    public static void main(String[] args) {
        //String P = "PM 11:19:59";
        //String P = "AM 09:12:55";
        String P = "PM 11:00:00";
        int n = 3600;
        System.out.println(solution(P, n));
    }

    private static String solution(String p, int n) {
        String aop = p.split(" ")[0];
        String time = p.split(" ")[1];

        LocalTime inputLocaltime = stringToLocalTime24(aop, time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return inputLocaltime.plusSeconds(n).format(formatter);
    }

    // String Time convert to LocalTime
    private static LocalTime stringToLocalTime24(String AMorPM, String time) {
        String[] arr = time.split(":");
        int hour = Integer.parseInt(arr[0]); // hour
        int min = Integer.parseInt(arr[1]); // min
        int sec = Integer.parseInt(arr[2]); // sec;
        if (AMorPM.equals("PM")) {
            hour = hour < 12 ? 12 + hour : hour;
        } else {
            hour = hour % 12;
        }
        
        return LocalTime.of(hour, min, sec);
    }
}
