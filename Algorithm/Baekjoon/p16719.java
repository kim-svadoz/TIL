/*
	ZOAC
    구현, 문자열 정렬

    int compareTo(String anotherString)
    문자열을 사전순으로 비교, 

    - 두 문자열이 사전 순으로 같다면 0을 반환
    - 대상 문자열 (메서드가 호출된 문자열)이 매개변수로 받은 문자열보다 사전 순으로 앞선 경우 음수를 반환.
    - 대상 문자열 (메서드가 호출된 문자열)이 매개변수로 받은 문자열보다 사전순으로 뒤질 경우 양수를 반환. 
*/

import java.io.*;
import java.util.*;
public class p16719 {
    static class Pair implements Comparable<Pair>{
        String s;
        int idx;
        public Pair (String s, int idx) {
            this.s = s;
            this.idx = idx;
        }

        // 문자열 사전 순으로 정렬하는 부분
        public int compareTo(Pair o) {
            if (s.compareTo(o.s) > 0) {
                return 1;
            } else if (s.compareTo(o.s) < 0) {
                return -1;
            } else { // 이 else 구문이 없으면 illegalArgument Exception이 발생한다.
                return 0;
            }
        }
    }
    static String input;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        char[] arr = input.toCharArray();
        visited = new boolean[arr.length];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.setLength(0);
            List<Pair> list = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if (!visited[j]) {
                    String temp = "";
                    for (int k = 0; k < arr.length; k++) {
                        if (j == k || visited[k]) {
                            temp += arr[k]+"";
                        }
                    }
                    list.add(new Pair(temp, j));
                }
            }
            Collections.sort(list);
            sb.append(list.get(0).s);
            visited[list.get(0).idx] = true;
            System.out.println(sb.toString());
        }
    }
}

/* dfs 풀이 (시간복잡도 개선)
import java.io.*;
import java.util.*;
public class p16719 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    static String input;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        visited = new boolean[input.length()];

        zoac(0, input.length() - 1);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void zoac(int left, int right) throws IOException {
        if (left > right) return;

        int idx = left;

        // left와 right 사이에 있는 글자중 사전식 순서가 가장 낮은 글자를 찾는다.(idx)
        for (int i = left; i <= right; i++) {
            if (input.charAt(idx) > input.charAt(i)) {
                idx = i;
            }
        }
        visited[idx] = true;

        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) {
                bw.write(input.charAt(i));
            }
        }
        bw.newLine();

        // 여기도 순서가 안맞으면 틀렸습니다를 확인할 수 있다.
        // 반드시 이 dfs 순서로 재귀를 구현해야 한다.
        // 그래야 사전식 순서가 맞춰진다.
        zoac(idx + 1, right);
        zoac(left, idx  - 1);
    }
}
*/