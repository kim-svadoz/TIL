import java.util.ArrayList;
import java.util.Arrays;

public class ShuttleBus_1768 {

	public static void main(String[] args) {
		
	}
	
	public static String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
        final String firstShuttleTime = "09:00";
        int shuttleTimeM = timeToNumber(firstShuttleTime); // ù ��Ʋ���� ����ð�(��)
        int lastShuttleTimeM = timeToNumber("23:59");
        int lastRideTime = 0;

        int[] timetableM = new int[timetable.length];
        boolean[] isRided = new boolean[timetable.length];

        // ũ�� ���̺� �д��� & �������� ����
        for(int i=0; i<timetable.length; i++){
            timetableM[i] = timeToNumber(timetable[i]);
        }
        Arrays.sort(timetableM); // ������ ž���� ���� ����

        // ��Ʋ ���� ����
        for(int i=0; i<n; i++){

//          System.out.println("\n"+(i+1)+"��° ���� :" + shuttleTimeM );
            int seat = m; // �����¼�
            if(i == (n-1))
                lastShuttleTimeM = shuttleTimeM;

            // timetableM ũ��� ��Ʋ ž�½õ�
            for(int j=0; j<timetableM.length; j++){

              //��Ʋ ž��
                if(timetableM[j] <= shuttleTimeM && seat >=1 && !isRided[j]){
                    seat--;
                    isRided[j] = true;
                    lastRideTime = timetableM[j];
//                  System.out.println(timetableM[j] +" ž��");
//                  System.out.println("�ܿ��¼�:"+seat +" ������ž�½ð�:"+lastRideTime);
                }
            }

            if(i == (n-1)){// ������ ��Ʋ

                if(seat >0){
                    return answer = timeToString(lastShuttleTimeM);
                }else{
                    return answer = timeToString(lastRideTime-1);
                }
            }

            shuttleTimeM += t; // ���� ��Ʋ

        }

        return answer;
    } 

    public static int timeToNumber(String time){// �д�����
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    public static String timeToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
	}
}
