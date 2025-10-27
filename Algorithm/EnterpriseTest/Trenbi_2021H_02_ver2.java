import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trenbi_2021H_02_ver2 {
    public static void main(String[] args) {
        //String P = "PM 11:19:59";
        //String P = "AM 09:12:55";
        String P = "PM 10:00:00";
        int n = 600;
        System.out.println(solution(P, n));
    }

    private static String solution(String p, int n) {
        return stringToLocalTime24(p).plusSeconds(n).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    // String Time convert to LocalTime
    private static LocalTime stringToLocalTime24(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("a hh:mm:ss", Locale.US));
    }
}
