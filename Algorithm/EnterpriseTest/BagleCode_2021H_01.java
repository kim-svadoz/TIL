/**
 * 43점
 */
import java.util.*;

public class BagleCode_2021H_01 {
    public static void main(String[] args) {
        String[] directory = {
            "/", 
            "/hello", 
            "/hello/tmp", 
            "/root", 
            "/root/abcd", 
            "/root/abcd/etc", 
            "/root/abcd/hello"
        };
        String[] command = {
            "mkdir /root/tmp", 
            "cp /hello /root/tmp", 
            "rm /hello"
        };
        /*
        String[] directory = {
            "/"
        };
        String[] command = {
            "mkdir /a",
            "mkdir /a/b",
            "mkdir /a/b/c",
            "cp /a/b /",
            "rm /a/b/c"
        };
        */
        solution(directory, command);
    }

    static List<String> dir;
    public static String[] solution(String[] directory, String[] command) {
        init(directory);
        pro(directory, command);
        return ret();
    }

    public static void init(String[] directory) {
        dir = new ArrayList<>();
        for (int i = 0; i < directory.length; i++) {
            dir.add(directory[i]);
        }
    }

    public static void pro(String[] directory, String[] command) {
        for (int i = 0; i < command.length; i++) {

            String[] arr = command[i].split(" ");
            if (arr[0].equals("mkdir")) {
                dir.add(arr[1]);
            } else if (arr[0].equals("cp")) {
                String source = arr[1];
                List<String> tmp = new ArrayList<>();

                String[] before = source.split("/");
                source = "/" + before[before.length - 1];

                for (String s : dir) {
                    if (s.contains(source)) {
                        int idx = s.indexOf(source);
                        tmp.add(s.substring(idx, s.length()));
                    }
                }
                
                String dest = arr[2];
                if (dest.equals("/")) dest = "";

                for (String s : tmp) {
                    if (dir.contains(dest + s)) continue;
                    dir.add(dest + s);
                }
            } else if (arr[0].equals("rm")) {
                List<String> tmp = new ArrayList<>();
                for (String d : dir) {
                    if (d.contains(arr[1])) {
                        tmp.add(d);
                    }
                }
                for (String del : tmp) {
                    if (!del.startsWith(arr[1])) continue;
                    dir.remove(del);
                }
            }
        }
    }

    public static String[] ret() {
        Collections.sort(dir);
        String[] answer = new String[dir.size()];
        for (int i = 0; i < dir.size(); i++) {
            answer[i] = dir.get(i);
            System.out.println(answer[i]);
        }
        return answer;
    }
}
