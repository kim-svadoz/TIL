/*
    그리디 + 정렬
*/
import java.io.*;
import java.util.*;

public class p1092 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        List<Integer> crane = new ArrayList<>();
        List<Integer> box = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            crane.add(a);
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(st.nextToken());
            box.add(a);
        }

        // 내림차순
        Collections.sort(crane, new Desc());
        Collections.sort(box, new Desc());
        int time = 0;
        // 박스의 최대 무게 > 크레인 최대 중량일 경우 -1
        if (box.get(0) > crane.get(0)) {
            System.out.println(-1);
        } else { // 그 외 그리디 구현
            while (box.size() != 0) {
                int idx = 0;
                int tmp = 0;
                while (idx < n) {
                    if (tmp == box.size()) {
                        break;
                    }

                    if (box.get(tmp) <= crane.get(idx)) {
                        box.remove(tmp);
                        idx++;
                    } else {
                        tmp++;
                    }
                }
                time++;
            }
            System.out.println(time);
        }

    }
}
class Desc implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
    
}
