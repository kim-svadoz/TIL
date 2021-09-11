import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_01 {
    public static void main(String[] args) throws IOException {
        String[] id_list = {
            "muzi", "frodo", "apeach", "neo"
        };

        String[] report = {

        };

        int k = 2;
        solution(id_list, report, k);
    }

    static Map<String, Integer> idxMap;
    static Map<Integer, HashMap<Integer, Integer>> map;
    public static int[] solution(String[] id_list, String[] report, int k) {
        idxMap = new HashMap<>();
        map = new HashMap<>();
        int len = id_list.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            map.put(i, new HashMap<>());
            idxMap.put(id_list[i], i);
        }

        int[] arr = new int[len];

        for (String r : report) {
            int idxA = idxMap.get(r.split(" ")[0]);
            int idxB = idxMap.get(r.split(" ")[1]);

            if (map.containsKey(idxA)) {
                if (!map.get(idxA).containsKey(idxB)) {
                    arr[idxB]++;
                    map.get(idxA).put(idxB, 1);
                }
            }
        }

        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();

            Iterator<Integer> sub_it = map.get(key).keySet().iterator();
            while (sub_it.hasNext())  {
                int subKey = sub_it.next();
                if (arr[subKey] >= k) {
                    answer[key]++;
                }
            }
        }
        
        return answer;
    }
}
