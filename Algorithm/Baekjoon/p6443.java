import java.io.*;
import java.util.*;

public class p6443 {
    static int n;
    static char[] cArr, result, mx;
    static boolean[] visit;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            cArr = br.readLine().toCharArray();
            int len = cArr.length;
            result = new char[len];
            mx = new char[len];
            visit = new boolean[len];

            Arrays.sort(cArr);

            dfs(len, 0);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int len, int depth) throws IOException {
        if (depth == len) {
            bw.write(result);
            bw.write("\n");
            return;
        }

        mx[depth] = 0;
        for (int i = 0; i < len; i++) {
            if (visit[i]) continue;
            if (mx[depth] >= cArr[i]) continue; // mx 함수로 중복 순열을 제거

            mx[depth] = cArr[i];

            visit[i] = true;
            result[depth] = cArr[i];
            dfs(len, depth + 1);
            visit[i] = false;
        }
    }
}

/* alphabet 배열 과 PriorityQueue를 이용한 풀이

import java.io.*;
import java.util.*;

public class Main {
    static int n,t;
    static String s;    
    static boolean[] v;
    static int[] alpa;
    static StringBuilder sb=new StringBuilder();
    static void dfs(int idx,String str){
        if(idx==n){
            sb.append(str+"\n");
            return;
        }
        for(int i=0;i<26;i++){
            if(alpa[i]>0){
                alpa[i]--;
                dfs(idx+1,str+((char)('a'+i)+""));
                alpa[i]++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine());       
        t=Integer.parseInt(st.nextToken());
        for(int tt=0;tt<t;tt++){
            st=new StringTokenizer(bf.readLine());       
            s=st.nextToken();            
            n=s.length();
            v=new boolean[n]; 
            alpa=new int[26];           
            PriorityQueue<Character>pq=new PriorityQueue<>();
            for(int i=0;i<n;i++)alpa[s.charAt(i)-'a']++;            
            dfs(0,"");
        }
        System.out.println(sb);
    }    
}

*/


/* next_permutation 풀이 (모든 다음 순열을 구한다)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String input = br.readLine();
            StringBuilder sb = new StringBuilder();
            char[] arr = new char[input.length()];
            for(int j=0; j<arr.length; j++)
                arr[j] = input.charAt(j);

            Arrays.sort(arr);

            for(int j=0; j<arr.length; j++)
                sb.append(arr[j]);
            sb.append("\n");

            while(next_permutation(arr)) {
                for(int j=0; j<arr.length; j++)
                    sb.append(arr[j]);
                sb.append("\n");
            }

            System.out.print(sb.toString());
        }
    }

    public static boolean next_permutation(char[] arr) {
        int i = arr.length-1;

        while(i>0 && arr[i]<=arr[i-1])
            i--;                          //앞의 문자보다 뒤에 문자가 사전상 뒤에 오는 경우 탐색

        if(i<=0) return false;

        int j = arr.length-1;

        while(arr[i-1]>=arr[j])
            j--;                          //선택한 문자보다 사전상 뒤에 오는 문자를 배열 끝에서부터 탐색

        char temp = arr[j];
        arr[j] = arr[i-1];
        arr[i-1] = temp;                //두 문자를 서로 교환

        j = arr.length-1;
        while(i<j) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;              //뒤에 문자들 순서를 뒤집어 줌
            i++;
            j--;
        }

        return true;
    }
}

*/