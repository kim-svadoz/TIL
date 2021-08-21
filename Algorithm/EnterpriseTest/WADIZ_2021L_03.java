/*
    스택으로... 전체 배열 순회하면서 이전값보다 크면 차를 push, 
    작으면 pop하면서 이전값에서 빼다가 작아지면 다시 차를 push...

    저도 스택 썼는데 스택 값보다 크면 스택에 푸쉬, 작은거 나오면 스택에서 같거나 작은거 나오기 전까지 
    계속 빼주고 같으면 
    카운팅 안하고 넘어가고 스택에 집어넣을때마다 카운팅 했어요.. 맞는진 모르겠네요
*/
import java.util.*;
public class WADIZ_2021L_03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 8, 4, 2, 1};
        solution(arr);
    }

    static boolean[] check;
    static int[] a;
    static public int solution(int[] arr) {
        check = new boolean[arr.length];
        a = new int[arr.length];
        int answer = 0;

        

        answer = func(arr);

        System.out.println(answer);
        return answer;
    }

    static int func(int[] arr) {
        int ret = Integer.MAX_VALUE;
        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!check[i] && arr[i] != 0) {
                ret = Math.min(ret, arr[i]);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (!check[i]) {
                if (ret == arr[i]) {
                    check[i] = true;
                    list.add(i);
                }
            }
        }



        return cnt;
    }

    static int findMax(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }
}
