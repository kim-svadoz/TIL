import java.io.*;
import java.util.*;

public class p10814 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        List<BOJ> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            BOJ member = new BOJ(Integer.parseInt(st.nextToken()), st.nextToken());
            list.add(member);
        }

        Collections.sort(list);

        for(int i = 0; i < N; i++) {
            System.out.println(list.get(i).age+" "+list.get(i).name);
        }
    }

    public static class BOJ implements Comparable<BOJ>{
        int age;
        String name;
        public BOJ(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public int compareTo(BOJ other) {
            return age - other.age;
        }
    }
}
