/*
    최솟값과 최댓값
    세그먼트 트리

    구간별 최소값을 저장하는 트리, 최댓값을 저장하는 트리를 각각 생성한다.
*/
import java.io.*;
import java.util.*;
public class p2357 {
    static class SegmentTree {
        long[] minTree, maxTree;
        public SegmentTree() {
            minTree = new long[n * 4];
            maxTree = new long[n * 4];
        }
        long minInit(long[] tree, int node, int start, int end) {
            if (start == end) return minTree[node] = arr[start];
            int mid = (start + end) / 2;
            return minTree[node] = Math.min(minInit(tree, node * 2, start, mid), minInit(tree, node * 2 + 1, mid + 1, end));
        }

        long maxInit(long[] tree, int node, int start, int end) {
            if (start == end) return maxTree[node] = arr[start];
            int mid = (start + end) / 2;
            return maxTree[node] = Math.max(maxInit(tree, node * 2, start, mid), maxInit(tree, node * 2 + 1, mid + 1, end));
        }

        // left ~ right 범위 내의 최솟값을 찾는다.
        long minFind(long[] tree, int node, int start, int end, int left, int right) {
            if (right < start || end < left) return Integer.MAX_VALUE;

            if (left <= start && end <= right) return minTree[node];

            int mid = (start + end) / 2;

            return Math.min(minFind(minTree, node * 2, start, mid, left, right), minFind(minTree, node * 2 + 1, mid + 1, end, left, right));
        }

        // left ~ right 범위 내의 최댓값을 찾는다.
        long maxFind(long[] tree, int node, int start, int end, int left, int right) {
            if (right < start || end < left) return 0;

            if (left <= start && end <= right) return maxTree[node];

            int mid = (start + end) / 2;

            return Math.max(maxFind(minTree, node * 2, start, mid, left, right), maxFind(minTree, node * 2 + 1, mid + 1, end, left, right));
        }
    }
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree sgTree = new SegmentTree();
        sgTree.minInit(sgTree.minTree, 1, 1, n);
        sgTree.maxInit(sgTree.maxTree, 1, 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(sgTree.minFind(sgTree.minTree, 1, 1, n, a, b)).append(" ").append(sgTree.maxFind(sgTree.maxTree, 1, 1, n, a, b));
            sb.append("\n");
        }
        System.out.println(sb.toString());
        
    }
}
