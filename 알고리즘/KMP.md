# KMP :fist_oncoming:

KMP알고리즘에 대해 간략히 설명 하자면, 지금까지 알려진 문자열 알고리즘 가운데 가장 최저의 시간복잡도를 가진 알고리즘이다. 

일단, KMP알고리즘의 시간복잡도는 O(N+K) 여기서 N과 K는 비교할 문자열의 길이이다. 매칭을 하려면 최소한 비교대상과 타겟의 문자열을 한번씩 읽어봐야 할테니, 가장 최적의 시간복잡도이다.

알고리즘에 대한 기본적인 설명과 이해는 아래의 링크를 통해서 천천히 반복적으로 학습하는 것을 추천하고, 본인 역시 아래의 링크를 참고해서 학습한 내용에 이해에 필요한 설명을 추가하려 포스팅하려고 한다.

https://m.blog.naver.com/kks227/220917078260



## [ 백준 1786 ] 찾기(JAVA)

문제 링크: https://www.acmicpc.net/problem/1786

- KMP알고리즘을 통해 구현한다
- 일치하는 문자열이 있는 경우, `Cnt++`를 시키고, 해당 Index를 Li리스트에 담는다
- 모두 검사한 후, Cnt와 Li에 있는 원소를 전부 출력한다.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_bj_1786_찾기 {
    static int cnt = 0;
    static List<Integer> li;
    static int[] getPi(String ptn) {
        int[] pi = new int[ptn.length()];
        int j = 0;
        for(int i=1; i<ptn.length; i++){
            while(j>0 && ptn.charAt(i)!=ptn.charAt(j)){
                j = pi[j-1];
            }
            if(ptn.charAt(i) == ptn.charAt(j))
                pi[i] = ++j;
        }
        return pi;
    }
    
    static void KMP(String org, String ptn) {
        int pi[] = getPi(ptn);
        int j = 0;
        for(int i=0; i<org.length(); i++) {
            while(j>0 && org.charAt(i)!=ptn.charAt(j)) {
                j = pi[j-1];
            }
            if(org.charAt(i)==ptn.charAt(j)) {
                if(j==ptn.length()-1) {
                    ++cnt;
                    li.add(i-j+1);
                    j=pi[j];
                } else {
                    j++;
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(Sytem.in));
        String origin = br.readLine();
        String pattern = br.readLine();
        li = new ArrayList<>();
        KMP(origin, pattern);
        System.out.println(cnt);
        for(int i=0; i<cnt; i++)
            System.out.println(li.get(i));
    }
}
```





