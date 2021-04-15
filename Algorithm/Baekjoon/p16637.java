/*
    브루트포스 + DFS
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16637 {
    static int n, max = Integer.MIN_VALUE;
    static char[] chArr;
    static boolean[] visited;
    static List<Character> ops;
    static List<Integer> nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        chArr = br.readLine().toCharArray();

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = chArr[i];
            if(!Character.isDigit(c)) {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        dfs(0, nums.get(0));
        System.out.println(max);
    }

    // 백트래킹 이용한 dfs
    static void dfs(int opIdx, int sum) {
        // 주어진 연산자의 개수를 초과 시 base 케이스
        if (opIdx >= ops.size()) {
            max = Math.max(max, sum);
            return;
        }

        // 괄호 안치고 진행하기
        int val = calc(ops.get(opIdx), sum, nums.get(opIdx + 1));
        dfs(opIdx + 1, val);

        // 괄호 치고 진행하기
        if (opIdx + 1 < ops.size()) { // 인덱스 범위 오류 방지
            // sum의 오른쪽에 있는 값을 연산한다.
            int val2 = calc(ops.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2));

            // 현재 sum과 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘긴다.
            dfs(opIdx + 2, calc(ops.get(opIdx), sum, val2));
        }
    }

    // 연산 메소드
    static int calc(char op, int sum, int num) {
        switch (op) {
        case '+':
            return sum + num;
        case '-':
            return sum - num;
        case '*' :
            return sum * num;
        }
        return 1;
    }
}
