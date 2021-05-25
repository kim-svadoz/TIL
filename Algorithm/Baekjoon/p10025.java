/*
	게으른 백곰
    sort + Two Pointer

	xi 1 2 7 15
	gi 5 2 4 10
*/
import java.io.*;
import java.util.*;
public class p10025 {
    static class Pair {
        int xi, gi;
        public Pair (int xi, int gi) {
            this.xi = xi;
            this.gi = gi;
        }
    }
    static int n, k;
    static Pair[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int gi = Integer.parseInt(st.nextToken());
            int xi = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(xi, gi);
        }

        
        Arrays.sort(arr, new Comparator<Pair>(){
            public int compare(Pair o1, Pair o2) {
                return o1.xi - o2.xi;
            };
        });
        
        
        int lo = 0, hi = 0, sum = 0, max = 0;
        int d = 2 * k + 1;
        while (hi < n) {
            if (arr[hi].xi - arr[lo].xi <= d) {
                sum += arr[hi++].gi;
                max = Math.max(max, sum);
            } else {
                sum -= arr[lo++].gi;
            }
        }
        System.out.println(max);
    }
}

/* 슬라이딩 윈도우로 더 간단하게 풀이 (시간복잡도 개선)

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		
		int arr[] = new int[1000001];
		for(int i=0; i<n; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(stk.nextToken());
			int p = Integer.parseInt(stk.nextToken());
			
			arr[p] = w; 
		}
		int sum = 0;
		int max = 0;
		int d = 1 +(2*k);
		for(int i=0; i<=1000000; i++) {
			if(i >= d) {
				sum -= arr[i-d];
			}
			sum += arr[i];
			if(sum > max) {
				max = sum;
			}
		}
		System.out.println(max);
	}
}

*/