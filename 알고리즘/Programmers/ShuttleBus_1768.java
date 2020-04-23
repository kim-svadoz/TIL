package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class ShuttleBus_1768 {

	public static void main(String[] args) {
		
	}
	
	public static String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
        final String firstShuttleTime = "09:00";
        int shuttleTimeM = timeToNumber(firstShuttleTime); // 첫 셔틀버스 운행시간(분)
        int lastShuttleTimeM = timeToNumber("23:59");
        int lastRideTime = 0;

        int[] timetableM = new int[timetable.length];
        boolean[] isRided = new boolean[timetable.length];

        // 크루 테이블 분단위 & 오름차순 정렬
        for(int i=0; i<timetable.length; i++){
            timetableM[i] = timeToNumber(timetable[i]);
        }
        Arrays.sort(timetableM); // 순차적 탑승을 위해 정렬

        // 셔틀 운행 시작
        for(int i=0; i<n; i++){

//          System.out.println("\n"+(i+1)+"번째 운행 :" + shuttleTimeM );
            int seat = m; // 남은좌석
            if(i == (n-1))
                lastShuttleTimeM = shuttleTimeM;

            // timetableM 크루들 셔틀 탑승시도
            for(int j=0; j<timetableM.length; j++){

              //셔틀 탑승
                if(timetableM[j] <= shuttleTimeM && seat >=1 && !isRided[j]){
                    seat--;
                    isRided[j] = true;
                    lastRideTime = timetableM[j];
//                  System.out.println(timetableM[j] +" 탑승");
//                  System.out.println("잔여좌석:"+seat +" 마지막탑승시간:"+lastRideTime);
                }
            }

            if(i == (n-1)){// 마지막 셔틀

                if(seat >0){
                    return answer = timeToString(lastShuttleTimeM);
                }else{
                    return answer = timeToString(lastRideTime-1);
                }
            }

            shuttleTimeM += t; // 다음 셔틀

        }

        return answer;
    } 

    public static int timeToNumber(String time){// 분단위로
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    public static String timeToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
	}
}
