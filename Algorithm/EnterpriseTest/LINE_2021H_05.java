import java.util.*;
public class LINE_2021H_05 {
    static List<String> flagNameList;
    static Map<String, String> flagArgumentMap;
    public static void main(String[] args) {
        String program = "line";
        String[] flag_rules = {
            "-s STRING", "-n NUMBER", "-e NULL"
        };
        String[] commands = {
            "line -s 123 -n HI", "line fun"
        };
        solution(program, flag_rules, commands);

    }

    public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = {};
        answer = new boolean[commands.length];

        for (int tc = 0; tc < commands.length; tc++) {
            String command = commands[tc];
            boolean flag = false;
            String userTypeProgram = command.split(" ")[0];
            int index = userTypeProgram.length();
            String userTypeFlagRules = command.substring(index + 1);
            

            if (checkProgram(program, userTypeProgram)) answer[tc] = true;
            else {
                answer[tc] = false;
                continue;   
            }

            registerFlagRules(flag_rules);

            if (checkFlagRules(userTypeFlagRules)) answer[tc] = true;
            else answer[tc] = false;
        }

        return answer;
    }

    private static boolean checkProgram(String program, String userTypeProgram) {
        for (int i = 0; i < userTypeProgram.length(); i++) {
            char c = userTypeProgram.charAt(i);

            if (c != program.charAt(i)) return false;
        }
        return true;
    }

    private static void registerFlagRules(String[] flag_rules) {
        flagNameList = new ArrayList<>();
        flagArgumentMap = new HashMap<String, String>();

        for (String s : flag_rules) {
            String flagName = s.split(" ")[0].substring(1);
            String flagArgument = s.split(" ")[1];
            
            flagNameList.add(flagName);
            flagArgumentMap.put(flagName, flagArgument);
        }

    }

    private static boolean checkFlagRules(String userTypeFlagRule) {
        String[] depth = userTypeFlagRule.split("-");
        int cnt = 0;
        for (String str : depth) {
            cnt++;
            if(cnt == 1) continue;
            
            if(str.length() == 1) {
                if(!str.equals("e")) return false;
            }

            String userTypeFlagName = str.split(" ")[0];
            String userTypeFlagArgument = str.split(" ")[1];

            if (!flagNameList.contains(userTypeFlagName)) return false;
            else {
                String flag_name = flagArgumentMap.get(userTypeFlagName);
                switch (flag_name) {
                    case "NULL":
                        if(userTypeFlagArgument.equals("") == false) return false;
                        break;

                    case "STRING":
                        if(isStringDouble(userTypeFlagArgument)) return false;
                        break;

                    case "NUMBER":
                        for (int i = 0; i < userTypeFlagArgument.length(); i++) {
                            if (!Character.isDigit(userTypeFlagArgument.charAt(i))) return false;
                        }
                        break;
                    default :
                }
            }
        }

        return true;
    }

    static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
