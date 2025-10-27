/*
    알파벳 배열을 활용한 dfs + 백트래킹 !
    가능한 모든 경우를 탐색할 수 있는 이유는 n이 16이하로 넉넉하기 때문이다.

    + bfs , 비트마스킹 으로도 풀 수 있다.
*/  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16508 {
    static List<Book> books = new ArrayList<>();
    static String T;
    static int[] count = new int[26];
    static int[] select_cnt = new int[26];
    static int n, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = br.readLine();
        int len = T.length();
        for (int i = 0; i < len; i++) {
            count[T.charAt(i) - 'A']++;
        }

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            books.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        dfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? - 1 : min);
    }

    static void dfs(int index, int total) {
        if (index == n) {
            // 뽑은 알파벳이 유효하다면 최소값 구한다
            if (check()) {
                min = Math.min(min, total);
            }
            return;
        }

        // dfs 백트래킹으로 모든 경우를 조합한다.
        for (int i = 0; i < books.get(index).getTitle().length(); i++) {
            select_cnt[books.get(index).getTitle().charAt(i) - 'A']++;
        }
        dfs(index + 1, total + books.get(index).getPrice());
        for (int i = 0; i < books.get(index).getTitle().length(); i++) {
            select_cnt[books.get(index).getTitle().charAt(i) - 'A']--;
        }
        dfs(index + 1, total);
    }

    static boolean check() {
        for (int i = 0; i < 26; i++) {
            if (count[i] > select_cnt[i]) {
                return false;
            }
        }
        return true;
    }
}

class Book {
    int price;
    String title;

    public Book (int price, String title) {
        this.price = price;
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

/*================================  bfs 풀이법 ===========================

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String target;
    static int n;
    static Book[] books;
    static public void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input=br.readLine();
        StringTokenizer st=new StringTokenizer(input," ");
        target=st.nextToken();
        st=new StringTokenizer(br.readLine()," ");
        n=Integer.parseInt(st.nextToken());
        books=new Book[n];
        int answer=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine()," ");
            books[i]=new Book(Integer.parseInt(st.nextToken()),st.nextToken());
        }
        Arrays.sort(books,(a,b)->a.price-b.price);


        for(int i=0;i<n;i++){
            String remain=remove(target,books[i].name);
            if(!remain.equals(target))
                answer=Math.min(answer,bfs(i,books[i].price,remain));
        }
        if(answer==Integer.MAX_VALUE)
            System.out.println(-1);
        else
          System.out.println(answer);
    }


    private static int bfs(int index, int price,String remain) {
        if(remain.equals("")){
            return price;
        }
        int result=Integer.MAX_VALUE;

        for (int i=index+1;i<n;i++){
            String next=remove(remain,books[i].name);
            if(!next.equals(remain))
                result=Math.min(result,bfs(i,price+books[i].price,next));
        }

        return result;
    }

    private static String remove(String target, String name) {
        StringBuilder sb=new StringBuilder(target);
        char[] array=name.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int index=sb.toString().lastIndexOf(array[i]);
            if(index>=0){
                sb.deleteCharAt(index);
            }
        }
        return sb.toString();
    }

    static class Book{
        String name;
        int price;

        public Book(int price,String name) {
            this.name = name;
            this.price = price;
        }
    }
}

=========================== 비트마스킹을 이용한 완전탐색 ===========================
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        int t = target.length();
        //전공책 수
        int n = Integer.parseInt(br.readLine());
        String[] title = new String[n];
        int[] costs = new int[n];
        for(int i = 0; i < n; i++){
            String[] book = br.readLine().split(" ");
            costs[i] = Integer.parseInt(book[0]);
            title[i] = book[1];
        }
        
        //total 조합
        int total = 1<<n;
        
        int answer = -1;
        for (int i = 1; i < total; i++) {
            int[] cnt = new int[26];
            int costSum =0;
            for (int j = 0; j < n; j++) {
                //책 선택
                if ((i & (1 << j)) > 0) {
                    String bookT = title[j];
                    for (int k = 0; k < bookT.length(); k++) {
                        cnt[bookT.charAt(k) - 'A']++;
                    }
                    costSum += costs[j];
                }
            }
            if (check(cnt,target)) {
                if(answer == -1)answer=costSum;
                else answer = Math.min(answer, costSum);
            }
        }
        System.out.println(answer);
    }
    public static boolean check(int[] cnt, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (cnt[target.charAt(i) - 'A'] == 0) return false;
            cnt[target.charAt(i) - 'A']--;
        }
        return true;
    }
}

*/