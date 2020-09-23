package baekjoon;

import java.util.*;
import java.io.*;

public class MaximumFlow_6086 {

    static int[][] capacity, flow;

    public static int atoi(char c) {
        if('A' <= c && c <= 'Z') return (c - 65);
        else if('a'<=c && c<='z')  return (c - 71);
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        // Build Graph
        capacity = new int[52][52];
        flow = new int[52][52];

        int n = Integer.parseInt(token.nextToken());
        for(int i=0;i<n;i++){
            token = new StringTokenizer(br.readLine());
            int start = atoi(token.nextToken().charAt(0));
            int end = atoi(token.nextToken().charAt(0));
            int weight = Integer.parseInt(token.nextToken());
            capacity[start][end] += weight;
            capacity[end][start] += weight;
        }

        int totalFlow = 0;
        int source = 0, sink = 25;
        while(true) {
            int[] parent = new int[52];
            Queue<Integer> queue = new PriorityQueue();
            //for(int i=0;i<52;i++) parent[i] = -1;
            Arrays.fill(parent, -1);
            parent[source] = source;
            queue.add(source);

            while(!queue.isEmpty() && parent[sink] == -1) {
                int here = queue.poll();
                for(int there=0; there<52; there++) {
                    if (capacity[here][there] - flow[here][there] > 0
                        && parent[there] == -1) {
                        queue.add(there);
                        parent[there] = here;
                    }
                }
            }

            // 더이상 증가 경로가 없으면 종료한다.
            if (parent[sink] == -1) break;

            // 증가 경로를 찾았으면 유량을 결정한다.
            int amount = Integer.MAX_VALUE;
            for(int i = sink; i!=source; i=parent[i]) {
                amount = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], amount);
            }

            // 증가 경로를 통해 유량을 보낸다.
            for(int i = sink; i!=source; i=parent[i]) {
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            totalFlow += amount;
        }

        bw.write(totalFlow+"\n");
        bw.close();
    }
}