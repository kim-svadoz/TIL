import java.util.*;
import java.io.*;

public class p20437 {
    static int t;
    static List<Integer>[] alpha;
    static LinkedList<Integer> list;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String str = br.readLine();
            int cnt = Integer.parseInt(br.readLine());
            
            int step3 = game(str, cnt, 3);
            sb.append(step3);
            if (step3 != -1) {
                int step4 = game(str, cnt, 4);
                sb.append(" ").append(step4);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
	}
    
    static int game(String str, int cnt, int step) {
        // 각각 알파벳이 나온 자리의 인덱스를 저장하기 위한 리스트
        alpha = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            alpha[i] = new ArrayList<>();
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            alpha[c - 'a'].add(i);
        }
        list = new LinkedList<>();
        
        for (int i =0; i < 26; i++) {
            int size = alpha[i].size();
            // 각각 알파벳이 나온 인덱스를 저장한 리스트의 길이가 목표 보다 작으면 넘어가고
            if (size < cnt) {
                continue;
            }

            // alpha[i] 번재 인덱스에 목표와 같은 문자가 나왔다.
            for (int idx = 0; idx < size - cnt + 1; idx++) {
                list.offerLast(alpha[i].get(idx + cnt -1) - alpha[i].get(idx) + 1);
            }
        }

        if (list.size() == 0) {
            return -1;
        }
        int min = 10001;
        int max = 0;

        while(!list.isEmpty()) {
            int val = list.poll();

            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        return step == 3 ? min : max;
    }
}
