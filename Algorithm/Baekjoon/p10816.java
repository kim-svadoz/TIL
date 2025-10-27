/*
어떤 리스트에서 이분탐색을 이용해서 특정 값을 찾을 때, 리스트가 중복된 값을 포함하고 있을 수 있다.
그 중복값을 전부 찾거나 또한 그 중복값들을 활용해서 문제를 해결하는 문제를 위해서 바로
//////////////// upper_bound , lower_bound 가 존재한다.///////////////////
해쉬맵과 upper/lower_bound, 두 가지방법으로 풀어보겠다.
*/

import java.io.*;
import java.util.*;

public class p10816 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N, M;
    static HashMap<Integer, Integer> map;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if(!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M; ++i) {
            int key = Integer.parseInt(st.nextToken());
            if (map.containsKey(key)) {
                bw.write(map.get(key)+" ");
            } else {
                bw.write("0 ");
            }
        }

        bw.close();
    }
}

/*
import java.io.*;
import java.util.*;
public class p10816 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N, M, arr[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        int k, lo, up;
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M; ++i) {
            k = Integer.parseInt(st.nextToken());
            lo = lowerBound(k);
            up = upperBound(k);
            bw.append((up - lo) + " ");
        }
        bw.flush();
        bw.close();
    }

    // 범위 안의 원소들 중 특정 target보다 크거나 같은 첫번째 원소의 인덱스를 리턴하고 그런 원소가 없으면 end인덱스 리턴
    public static int lowerBound(int k) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
    // 범위 안의 원소들 중 특정 target보다 큰 첫 번째 원소의 인덱스를 리턴하고 그런 원소가 없으면 end인덱스 리턴
    public static int upperBound(int k) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] <= k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (arr[end] == k) end++;

        return end;
    }
}
*/

