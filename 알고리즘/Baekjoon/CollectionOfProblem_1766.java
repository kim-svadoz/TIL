package baekjoon;

import java.io.*;
import java.util.*;

public class CollectionOfProblem_1766 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        SegmentTree st = new SegmentTree(n); 
        for(int i=1; i<=n; i++){
            long v = Long.parseLong(br.readLine());
            st.update(i, v);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m+k; i++){
            stk = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(stk.nextToken());
            if(type==1){
                st.update(Integer.parseInt(stk.nextToken()), Long.parseLong(stk.nextToken()));
            } else {
                sb.append(st.getSum(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()))).append('\n');
            }
        }
        System.out.print(sb);
    }
}

class SegmentTree{
    long[] tree;
    int s;
    
    public SegmentTree(int n){
        for(s = 1; s < n; s *= 2)
            ;
        tree = new long[s * 2];

        for(int i = 1; i < s + s; i++){
            tree[i] = 01;
        }
    }
    
    void update(int x, long v){
        int l = x + s - 1;
        tree[l] = v;
        l /= 2;
        while(l >= 1){
            tree[l] = tree[l * 2] + tree[l * 2 + 1];
            l /= 2;
        }
    }
    
    long getMin(int Left, int Right){
        int l = Left + s - 1, r = Right + s - 1;
        long rval = Long.MAX_VALUE;
        while(l <= r){
            if(l % 2 == 0)
                l /= 2;
            else {
                rval = Math.min(rval, tree[l]);
                l = (l / 2) + 1;
            }
            
            if(r % 2 == 1)
                r /= 2;
            else {
                rval = Math.min(rval, tree[r]);
                r = (r / 2) - 1;
            }
        }
        return rval;
    }
    
    long getSum(int Left, int Right){
        int l = Left + s - 1, r = Right + s -1;
        long rval = 0;
        while (l <= r){
            if(l % 2 == 0)
                l /= 2;
            else {
                rval += tree[l];
                l = (l / 2) + 1;
            }
            
            if(r % 2 == 1)
                r /= 2;
            else {
                rval += tree[r];
                r = (r / 2) - 1;
            }
        }
        return rval;
    }
}