import java.io.*;
import java.util.*;

public class p1764 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            map.put(input, 1);
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            if (map.containsKey(input)) {
                list.add(input);
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}