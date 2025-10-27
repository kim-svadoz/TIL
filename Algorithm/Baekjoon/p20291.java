/*
    str.split(".") 은 동작하지 않는다.
    자바에서 . (dot)은 예약어이기 때문에 regex에서 점 그대로 인식시키기 위해서는 [.] 또는 \\. 을 사용해야한다!
*/
import java.io.*;
import java.util.*;

public class p20291 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String ext = br.readLine();
            int idx = ext.indexOf(".");
            ext =  ext.substring(idx + 1);

            if (!map.containsKey(ext)) {
                map.put(ext, 1);
            } else {
                map.put(ext, map.get(ext) + 1);
            }
        }

        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            int val = map.get(key);
            sb.append(key).append(" ").append(val).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}