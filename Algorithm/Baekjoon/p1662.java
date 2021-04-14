/*
    이 문제는 압축이 문자열 길이라는 것에 주의해야 하며, 대부분의 문자열을 다루는 것은 stack을 통해서 해결해야 합니다.
    더불어 재귀를 이용하여 괄호를 처리하는 스킬을 배울 수 있습니다.

    괄호의 위치가 중요하므로 괄호의 모양에 따라 인덱스를 저장합니다. 이 때 map을 사용할 수도 있고 int[] 배열을 사용할 수 있습니다.
    여기서는 (이 나오는 인덱스에 )이 나오는 인덱스 위치를 저장하면 됩니다. 따라서 stack.pop()의 값을 배열 값으로 저장하는 것이 아니라 인덱스의 위치에 오게 됩니다.
    다음에는 문자열 길이를 계산하기 위해 traversal 함수를 실행합니다.

    이제 문자열 길이를 구하기 위해서 재귀를 돌면서 해결합니다. 맨 처음에 반환 할 길이 변수를 선언합니다. 
    마지막에는 그 길이를 그냥 반환하면 됩니다. 먼저 쓰고 시작합시다. 이제 for문에서 재귀를 통해서 연장합니다. 
    우리가 재귀하는 함수에서 각 함수를 구분하는 방법은 인덱스 start, end를 계속 다르게 설정하는 것입니다. 
    그리고 그 인덱스는 '('와 ')'의 위치입니다. '(' 앞에 나온 숫자를 해당 길이에 곱해주어 반환하면 됩니다. 
    다음 문자열 검사를 위해 인덱스 i의 위치는 reverse[i]로 이동하여 ')' 뒤부터 검사하도록 합니다. 
    '('가 아니면 일반 숫자이므로 길이를 1 증가시킵니다.

    * 1) 먼저 스택을 사용하여 괄호 쌍을 묶어야한다. 문자열에서 '('이 나오면 스택에 그 인덱스를 저장.
    * 2) 괄호쌍을 알아야한다. ')'가 나오면 앞에 '('와 짝을 지어야 하므로 배열하나를 만들어서 ')'가 나오면 그와 쌍이 되는 '('의 인덱스를 저장해두기
    * 3) 재귀함수를 이용해서 괄호를 풀기. 안쪽 괄호부터 글자수를 세야한다.
*/

import java.io.*;
import java.util.*;
public class p1662 {
    static int[] paren = new int[50];
    static char[] s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        s = input.toCharArray();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i] == '(') st.push(i);
            if (s[i] == ')') paren[st.pop()] = i;
        }
        System.out.println(recur(0, s.length));
    }

    static int recur(int start, int end) {
        int len = 0;
        for (int i = start; i < end; i++) {
            if (s[i] == '(') {
                len += (s[i - 1] -'0') * recur(i + 1, paren[i]) - 1;
                i = paren[i];
            } else {
                len++;
            }
        }
        return len;
    }
}