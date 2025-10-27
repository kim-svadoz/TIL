import java.util.*;

public class LineFintechServer_2021H_01 {
    public static void main(String[] args) {
        String[] record = {
            "P 300 6", "P 500 3", "S 1000 4", "P 600 2", "S 1200 1"
        };
        solution(record);
    }

    public static int[] solution(String[] record) {
        int FIFO = 0;
        int LIFO = 0;
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> st = new Stack<>();
        for (String s : record) {
            String[] arr = s.split(" ");
            int price = Integer.valueOf(arr[1]);
            int cnt = Integer.valueOf(arr[2]);
            if (arr[0].equals("P")) {
                // 구매
                while (cnt-- > 0) {
                    q.add(price);
                    st.push(price);
                }
            } else if (arr[0].equals("S")) {
                // 판매
                while (cnt-- > 0) {
                    FIFO += q.poll();
                    LIFO += st.pop();
                }
            }
        }

        int[] answer = new int[]{FIFO, LIFO};
        System.out.println(FIFO+" "+LIFO);
        return answer;
    }
}
