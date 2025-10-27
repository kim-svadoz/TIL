/*
contains()
- boolean contains(CharSequence s)
- contains() 함수는 대상 문자열에 특정 문자열이 포함되어 있는지 확인하는 함수이다.
- 대/소문자를 구분한다.

public class ContainsTest{
    public static void main(String[] args){
        String str = "my java test";

        System.out.println( str.contains("java") );  // true
        System.out.println( str.contains(" my") );  // false
        System.out.println( str.contains("JAVA") );  // false
        System.out.println( str.contains("java test") );  // true
    }
}
*/

import java.io.*;

public class p16171 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String pattern = br.readLine();
        input = input.replaceAll("[0-9]", "");
        int patLen = pattern.length();
        boolean flag = false;
        for (int i = 0; i <= input.length() - patLen; i++) {
            if (input.substring(i, i + patLen).equals(pattern)) {
                flag = true;
            }
        }
        System.out.println(flag ? 1 : 0);
    }
}

/******** indexOf 사용시
 * indexOf(s) 는 특정 문자나 문자열이 앞에서부터 처음 발견되는 인덱스를 반환하며 만약 찾지 못했을 경우 "-1"을 반환.
 * 
 * lastIndexOf(s)는 반대로 끝에서부터 조회를 한다. (origin의 높은 index 부터)
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String str = br.readLine().replaceAll("[0-9]", "");
		String strs = br.readLine();
		
		if(str.indexOf(strs) != -1) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
}

*/