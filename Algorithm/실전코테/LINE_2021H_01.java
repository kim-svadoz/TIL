import java.util.*;

public class LINE_2021H_01 {
    public static void main(String[] args) {
        int n = 0;
        String[] table = {
            "SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"
        };
        String[] languages = {
            "PYTHON", "C++", "SQL"
        };
        int[] preference = {
            7, 5, 5
        };
        solution(table, languages, preference);
    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";

        Map<String, String> jobMap = new HashMap<>();

        int len = 0;
        Map<Map<String, String>, Integer> map = new HashMap<>();
        for (String input : table) {
            String[] line = input.split(" ");
            String job = line[0];
            len = line.length - 1;
            map.put(new HashMap<>(job, line[1]), 5);
            map.put(new HashMap<>(job, line[2]), 4);
            map.put(new HashMap<>(job, line[3]), 3);
            map.put(new HashMap<>(job, line[4]), 2);
            map.put(new HashMap<>(job, line[5]), 1);
        }

        int[] score = new int[len];

        // 개발자 언어 선호도
        Map<String, Integer> preTable = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            preTable.put(languages[i], preference[i]);
        }


        // 개발자가 선호아는 언어의 직업군 언어 점수
        for (int i = 0; i < len; i++) {
            
        }



        return answer;
    }
}