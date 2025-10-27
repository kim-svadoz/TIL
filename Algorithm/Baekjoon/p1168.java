import java.io.*;
import java.util.*;
public class p1168 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = k - 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(list.get(index));
            list.remove(index);
            if (list.size() == 0) break;
            sb.append(", ");
            index += k - 1;

            index %= list.size();            
        }
        System.out.println("<" + sb + ">");
        bw.flush();
        bw.close();;
        br.close();
    }
}