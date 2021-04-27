import java.util.*;
import java.io.*;

public class p1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        // 최대 힙 (내림차순)
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());
        // 최소 힙 (오름차순)
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            if (leftPQ.size() == rightPQ.size()) leftPQ.add(n);
            else rightPQ.add(n);

            if (leftPQ.size() != 0 && rightPQ.size() != 0) {
                // 왼쪽이 오른쪽보다 더 크다면 순서에 어긋나므로 스왑
                if (leftPQ.peek() > rightPQ.peek()) {
                    int tmp = rightPQ.poll();
                    rightPQ.offer(leftPQ.poll());
                    leftPQ.offer(tmp);
                }
            }
            
            sb.append(leftPQ.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}