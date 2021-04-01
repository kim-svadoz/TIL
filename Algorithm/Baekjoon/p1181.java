import java.io.*;
import java.util.*;
public class p1181 {
    static int n;
    static List<Alpha> list;
    static class Alpha implements Comparable<Alpha>{
        int len;
        String name;
        public Alpha (int len, String name) {
            this.len = len;
            this.name = name;
        }
        
        public int compareTo(Alpha o) {
            if (len == o.len) {
                return name.compareTo(o.name);
            } else {
                return len - o.len;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        List<String> repeatCheck = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            if (repeatCheck.contains(input)) {
                continue;
            } 
            repeatCheck.add(input);
            
            Alpha a = new Alpha(input.length(), input);
            list.add(a);
        }
  
        Collections.sort(list);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).name);
        }
        
    }
}