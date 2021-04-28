/*
    다익스트라.
    최단거리 dist[] 배열을 만들어서 MAX_VALUE로 초기화 한 뒤
    지름길을 이용하는 방식과 그냥 이동하는 방식을 일일이 대조하며 탐색한다.
*/
import java.io.*;
import java.util.*;

public class p1446 {
    static class Road {
        int s, e, w;
        public Road (int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        List<Road> list = new ArrayList<>();

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 역주행 불가
            if (e > d) continue;
            // 지름길이 아니야!
            if (e - s <= w) continue;
            list.add(new Road(s, e, w));
        }

        Collections.sort(list, new Comparator<Road>(){
            public int compare(Road o1, Road o2) {
                if (o1.s == o2.s) return o1.e - o2.e;
                return o1.s - o2.s;
            }
        });

        int idx = 0, move = 0;
        int[] dist = new int[10001];
        Arrays.fill(dist, 10001);
        dist[0] = 0;
        // 다익스트라
        while (move < d) {
            if (idx < list.size()) {
                Road r = list.get(idx);
                if (move == r.s) {
                    dist[r.e] = Math.min(dist[move] + r.w, dist[r.e]);
                    idx++;
                } else {
                    dist[move + 1] = Math.min(dist[move + 1], dist[move] + 1);
                    move++;
                }
            } else {
                dist[move + 1] = Math.min(dist[move + 1], dist[move] + 1);
                move++;
            }
        }
        System.out.println(dist[d]);
    }
}

/* HashMap과 Tow-Down DP 풀이법

public class Main {
    static int[] dp;
    static HashMap<Integer, List<int[]>> shortCut;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        shortCut = new HashMap<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(!shortCut.containsKey(to)){
                shortCut.put(to, new ArrayList<>());
            }
            shortCut.get(to).add(new int[]{from,dist});

        }

        dp = new int[D+1];

        System.out.println(findDP(D));
        
    }

    static int findDP(int n){

        if (n == 0) {
            return 0;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        dp[n] = findDP(n-1) + 1;

        if (shortCut.containsKey(n)) {
            for(int[] root : shortCut.get(n)){
                dp[n] = Math.min(dp[n], findDP(root[0])+root[1]);
            }
        }
        return dp[n];
    }
}

*/

/* bfs + 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Road implements Comparable<Road>{
	int start,dest,d;
	Road(int start,int dest, int d){
		this.start=start;
		this.dest=dest;
		this.d=d;
	}
	
	@Override
	public int compareTo(Road r) {
		return this.d-r.d;
	}
	
}
public class Main {
	static int result=0;
	static int n,d;
	static Road[] graph;
	static int[] dist;
	public static void dijkstra(int start) {
		PriorityQueue<Road> q=new PriorityQueue<Road>();
		
		q.offer(new Road(start,0,0));
		dist[start]=0;
		
		while(!q.isEmpty()) {
			Road fast=q.poll();
			int destination=fast.dest;
			boolean chk=false;
			for(Road r:graph) {
				
				if(r.start>=destination) {
					if(r.dest>d) 
						continue;
					int tmp=r.start-destination;
					chk=true;
					if(dist[r.dest]>dist[destination]+r.d+tmp) {
						dist[r.dest]=dist[destination]+r.d+tmp;
						q.offer(new Road(destination,r.dest,dist[r.dest]));
					}
				}

			}
			
			//if(q.isEmpty())
				dist[d]=Math.min(dist[destination]+d-destination,dist[d]);
			
			}
		}
	
	 public static void main(String[] args) throws IOException {
		    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		    StringBuilder sb=new StringBuilder();
		    StringTokenizer st=new StringTokenizer(br.readLine());
		     n=Integer.parseInt(st.nextToken());
		     d=Integer.parseInt(st.nextToken());
		     dist=new int[d+1];
		     for(int i=0;i<d+1;i++) {
		    	 dist[i]=i;
		     }
		     graph=new Road[n];
		     for(int i=0;i<n;i++) {
		    	 st=new StringTokenizer(br.readLine());
		    	 int start=Integer.parseInt(st.nextToken());
		    	 int dest=Integer.parseInt(st.nextToken());
		    	 int d=Integer.parseInt(st.nextToken());
		    	 graph[i]=new Road(start,dest,d);
		     }
		     
		     dijkstra(0);
		     System.out.println(dist[d]);
		    }
}

*/