import java.io.*;
import java.util.*;
public class p1931 {
    static int N, cnt;
    static BufferedReader br;
    static StringTokenizer st;
    static Pair[] arr;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cnt = 0;
        arr = new Pair[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.end == o2.end) {
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        int prevEndTime = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i].start >= prevEndTime) {
                cnt++;
                prevEndTime = arr[i].end;
            }
        }
        System.out.println(cnt);
    }

    static class Pair {
        int start, end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
