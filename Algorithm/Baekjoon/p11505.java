/*
    구간 곱 구하기
    세그먼트 트리
*/
import java.io.*;
import java.util.*;
public class p11505 {
    static class SegmentTree {
        long[] tree;
        int s;
        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            this.s = (int) Math.pow(2, h + 1);
            // tree의 사이즈는 s로 잡아도 되지만 넉넉하게 곱하기 4로 잡는것도 좋다.
            tree = new long[n * 4];
        }

        static long init(long[] tree, int node, int start, int end) {
            if (start == end) return tree[node] = arr[start];
            int mid = (start + end) / 2;
            return tree[node] = init(tree, node * 2, start, mid) * init(tree, node * 2 + 1, mid + 1, end) % MOD;
        }

        static long update(long[] tree, int node, int start, int end, int idx, long val) {
            // idx의 범위가 tree의 범위에 벗어나면 종료조건
            if (start > idx || idx > end) return tree[node];
            //tree[node] = val;
            if(start == end) {
                return tree[node] = val;
            }
            int mid = (start + end) / 2;
            return tree[node] = update(tree, node * 2, start, mid, idx, val) * update(tree, node * 2 + 1, mid + 1, end, idx, val) % MOD;
        }

        // [start, end] : SegmentTree의 처음과 끝 인덱스 (재귀를 할 때마다 start, end가 변경)
        // [left, right] : 연산을 수행할 인덱스의 범위 (b, c는 그대로 동일)
        static long multi(long[] tree, int node, int start, int end, int left, int right) {
            // start, end가 범위(left, right)에 벗어나면 종료
            if (left > end || right < start) return 1;
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return (multi(tree, node * 2, start, mid, left, right)) 
                * (multi(tree, node * 2 + 1, mid + 1, end, left, right)) % MOD;
        }
    }
    static final int MOD = 1000000007;
    static int n, m, k;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree sgTree = new SegmentTree(n);
        sgTree.init(sgTree.tree, 1, 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch(command) {
            case 1:
                // b번째 수를 c로 바꾼다.
                arr[b] = c;
                sgTree.update(sgTree.tree, 1, 1, n, b, c);
                break;
            case 2:
                // b번째 수부터 c번째 수 까지 곱을 구하여 출력한다.
                long ret = sgTree.multi(sgTree.tree, 1, 1, n, b, c);
                sb.append(ret % MOD).append("\n");
                break;
            }
        }
        System.out.println(sb.toString());
    }
}