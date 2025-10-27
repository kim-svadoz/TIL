/*
    dfs를 돌릴 때 왜 idx++ 은 되지 않고 idx + 1은 되는걸까???
*/
import java.io.*;
import java.util.*;

public class p16987 {
    static class Egg {
        int si, wi;
        
        public Egg(int si, int wi) {
            this.si = si;
            this.wi = wi;
        }
    }
    static int n, max;
    static Egg[] egg;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        egg = new Egg[n];
        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken());
            int wi = Integer.parseInt(st.nextToken());
            egg[i] = new Egg(si, wi);
        }
        max = 0;
        dfs(0, 0);
        System.out.println(max);
    }
    static void dfs(int idx, int cnt) {
        if (idx == n) {
            // 최대값 갱신
            max = Math.max(cnt, max);
            return;
        }
        
       	Egg e = egg[idx];
        if (e.si <= 0 || cnt == n - 1) { // 손에 쥔 계란이 깨졌있을 때 or 다른 모든 계란이 깨졌을 때
            dfs(idx + 1, cnt);
            return;
        }
        int nCnt = cnt;
        for (int i = 0; i < n; i++) {
            if (i == idx) continue; // 손에 쥔 계란
            if (egg[i].si <= 0) continue; // 다른 계란들이 모두 깨졌을 때
            
            e.si -= egg[i].wi; // 손에 쥔 계란 내구도 감소
            egg[i].si -= e.wi; // 상대 계란 내구도 감소
            if (e.si <= 0) {
                cnt++;
            }
            if (egg[i].si <= 0) {
                cnt++;
            }
            
            dfs(idx + 1, cnt );
 
            // 원상 복구
            e.si += egg[i].wi;
            egg[i].si += e.wi;
            cnt = nCnt;
        }
    }
}