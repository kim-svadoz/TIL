/*
    문자열 복사
    그리디, 구현
*/
import java.io.*;
import java.util.*;
public class p2195 {
    static char[] str1, str2;
    static int len1, len2;
    static Map<Character, List<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<>();

        str1 = br.readLine().toCharArray();
        len1 = str1.length;
        for (int i = 0; i < len1; i++) {
            char c = str1[i];
            
            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
            }
            // char가 나타나는 인덱스를 저장
            List<Integer> list = map.get(c);
            list.add(i);
            map.put(c, list);
        }

        str2 = br.readLine().toCharArray();
        len2 = str2.length;
        
        int i = 0, ans = 0;
        while (i < len2) {
            List<Integer> list = map.get(str2[i]);
            // c가 나오는 index를 하나씩 탐색
            int ret = 0;
            for (int idx : list) {
                int cnt = 0;
                int j = i;
                while (j < len2 && idx < len1) {
                    if (str2[j] == str1[idx]) {
                        j++;
                        idx++;
                        cnt++;
                        continue;
                    }
                    break;
                }
                ret = Math.max(ret, cnt);
            }
            i += ret;
            ans++;
        }
        
        System.out.println(ans);
    }
}