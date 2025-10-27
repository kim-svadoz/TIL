import java.io.*;
import java.util.*;

public class p11399 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, answer[];
    static PriorityQueue<Person> pq;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        answer = new int[N];
        pq = new PriorityQueue<>();
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            pq.add(new Person(i, Integer.parseInt(st.nextToken())));
        }

        int[] min = new int[N];
        while (!pq.isEmpty()) {
            list.add(pq.poll().cost);
        }
        int sum = list.get(0);
        min[0] = list.get(0);
        for(int i = 1; i < N; ++i) {
            sum += (min[i] = min[i - 1] + list.get(i));
        }
        System.out.println(sum);
        
    }

    static class Person implements Comparable<Person>{
        int num, cost;
        Person (int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        @Override
        public int compareTo(Person o) {
            return cost - o.cost;
        }
    }
}
