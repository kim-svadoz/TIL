// 조합 , 백트래킹
import java.io.*;
import java.util.*;

public class p1759 {
    static BufferedReader br;
    static StringTokenizer st;
    static int l, c;
    static char[] arr;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visit = new boolean[c];
        arr = new char[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0, 0, 0);
    }

    // 백트래킹 사용
    // 시작점, 선택된 문자갯수, 자음갯수, 모음갯수
    static void dfs(int start, int depth, int ja, int mo){
        for(int i=start; i < c; i++){
            visit[i] = true;
            // 자음과 모음 갯수를 파악해서 다음으로 넘겨준다.
            dfs(i + 1, depth + 1, ja + (check(arr[i]) ? 1 : 0), mo + (check(arr[i]) ? 0 : 1));
            visit[i] = false;
        }

         // 문자갯수가 N개이며 자음과 모음의 갯수가 규칙에 맞을때만 출력한다.
        if (depth == l && ja >= 2 && mo >= 1) {
            print();
        }
    }

    // 자음 모음 파악
    static boolean check(char a) {
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') return false;
        else return true;
    }

    // 배열 출력
    static void print() {
        for (int i = 0; i < c; i++) {
            // false 라면 선택되지 않았기 때문에 넘긴다.
            if (visit[i]) {
                System.out.print(arr[i]);
            }
        }
        System.out.println();
    }
}
