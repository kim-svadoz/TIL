/**
 * S배열의 길이는 30000 (N)
 * 각 문자열은 2000의 길이 (M)
 * 각 배열의 원소는 모두 소문자
 * N * M = 30,000
 * 
 * N^2 * M
 */
import java.util.*;

public class Eleven11_2021H_02 {
    public static void main(String[] args) {
        String[] S = {
            "bdafg",
            "ceagi",
        };
        solution(S);
    }

    static class Item implements Comparable<Item> {
        String s;
        int idx;
        public Item(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }

        public int compareTo(Item o) {
            return s.compareTo(o.s);
        }
    }
    static int n;
    static int len;
    static Item[] item;
    public static int[] solution(String[] S) {
        n = S.length;
        len = S[0].length();
        item = new Item[n];
        for (int i = 0; i < n; i++) {
            item[i] = new Item(S[i], i);
        }

        Arrays.sort(item);

        for (int i = 0; i < n - 1; i++) {
            String pivot = item[i].s;
            for (int j = i + 1; j < n; j++) {
                String target = item[j].s;

                for (int k = 0; k < len; k++) {
                    if (pivot.charAt(k) == target.charAt(k)) {
                        System.out.println(item[i].idx + " " + item[j].idx + " " +k);
                        return new int[]{item[i].idx, item[j].idx, k};
                    }
                }
            }
        }
        return new int[]{};
    }
}
