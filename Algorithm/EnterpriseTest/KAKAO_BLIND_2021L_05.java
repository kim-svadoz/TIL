import java.io.*;
import java.util.*;

public class KAKAO_BLIND_2021L_05 {
    public static void main(String[] args) throws IOException {
        int[] info = {
            0,1,0,1,1,0,1,0,0,1,0
        };
        int[][] edges = {
            {0, 1},
            {0, 2},
            {1, 3},
            {1, 4},
            {2, 5},
            {2, 6},
            {3, 7},
            {4, 8},
            {6, 9},
            {9, 10},
        };
        System.out.println(solution(info, edges));
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {
            this.val = x;
        }
    }
    static int[] Info;
    static List<List<Integer>> tree;
    static int save; // save : 현재 까지 양이 늑대보다 몇마리 많이 가지고 있는가
    static int max;
    static int[][] dp; // dp[i] : i번째 노드에서 가질 수 있는 양의 최대 크기
    static boolean[] visited;
    public static int solution(int[] info, int[][] edges) {
        Info = info;
        int len = info.length;
        tree = new ArrayList<>();
        dp = new int[info.length][2];
        visited = new boolean[info.length];
        for (int i = 0; i < len; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int answer = save;
        return answer;
    }

}
