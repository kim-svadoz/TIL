/*
    감소하는수 (Gold 5)

    1 : 1 10
    2 : 20 21 210 ...
    3 : 30 32 31 310 320 321 ...
    4 : ...

    집합 {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} 에서 공집합이 아닌 임의의 부분집합 ex) {1, 3, 5, 9}일 때
    이 집합에 대응되는 감소하는 수는 무조건 1개로 고정이다. 

    수를 감소하게 둬야하기 때문.
    따라서, 감소하는 수는 2^10 - 1 = 1023개밖에 없다.
*/
import java.io.*;
import java.util.*;

public class p1038 {
    static int n;
    static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        if (n <= 10) {
            System.out.println(n);
        } else if (n > 1022) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < 10; i++) {
                solution(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    // ex) n: 4
    // list.add(0), list.add(1), list.add(2), list.add(3), .... , list.add(9)
    // list.add(10)
    // list.add(20), list.add(21)
    // list.add(30), list.add(31), list.add(32)
    // ...

    static void solution(long num, int idx) {
        if (idx > 10) {
            return;
        }
        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            solution((num * 10) + i, idx + 1);
        }
    }
}
