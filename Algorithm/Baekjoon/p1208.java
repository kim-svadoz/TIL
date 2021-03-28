/*
** 주어진 수열의 부분합을 구하는 문제
1182 문제와의 차이점은 N이 20 -> 40이 되었다. 따라서 완전탐색이 불가능하다. (int:2^32, long:2^40)
해법 1. Binary Search -> lower_bound 와 upper_bound를 구현해 문제를 해결한다. (p10816 참고)
해법 2. [1 3 5 7 2 4 5 10] 을 [1 3 5 7] [2 4 5 10]으로 나누어 왼쪽의 부분집합의 합, 오른쪽의 부분집합의 합을 구하는 리스트를 구한 후,
정렬하여 투포인터 알고리즘을 사용하여 해결한다. (p2632 참고)
해법 3. 기본적으로 두 배열로 나눈 뒤 각각 HashMap을 이용해 부분합(sum)을 key로, 빈도를 value로 체크하면서 구할 수 있다.(시간 복잡도 최상!!!)
*/
import java.io.*;
import java.util.*;

public class p1208 {
    static int n, s, arr[];
    static List<Integer> leftlist, rightlist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        leftlist = new ArrayList<>();
        rightlist = new ArrayList<>();
        dfs(0, n / 2, 0, leftlist);
        dfs(n / 2, n, 0, rightlist);

        Collections.sort(rightlist);

        long ans = 0;
        for (int val : leftlist) {
            val = s - val;
            int lo = upper_bound(rightlist, val);
            int hi = lower_bound(rightlist, val);
            ans += hi - lo;
        }

        // 공집합 제거
        if (s == 0) {
            --ans;
        }
        System.out.println(ans);
    }

    // lower bound는 찾고자 하는 값 이상이 처음 나타나는 위치
    static int lower_bound(List<Integer> list, int val) {
        int left = 0;
        int right = list.size();
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (list.get(mid) > val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // upper bound는 찾고자 하는 값 이상이 처음 나타나는 위치
    static int upper_bound(List<Integer> list, int val) {
        int left = 0;
        int right = list.size();
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (list.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    static void dfs(int index, int end, int sum, List<Integer> list) {
        if (index == end) {
            list.add(sum);
            return;
        }
        dfs(index + 1, end, sum, list);
        dfs(index + 1, end, sum + arr[index], list);
    }
}

/*************** 34~45번째와 lower/upper 대신 이렇게 쓸 수도 있다. ******************

    int left_idx = 0;
    int right_idx = rightlist.size()-1;
    
    while (left_idx < leftlist.size() && right_idx >= 0) {
        int left_sum = leftlist.get(left_idx);
        int right_sum = rightlist.get(right_idx);
        long left_cnt = 0;
        long right_cnt = 0;
        
        if (left_sum + right_sum == s) {
            while (left_idx < left.size() && left.get(left_idx) == left_sum) {
                left_idx++;
                left_cnt++;
            }
            while (right_idx >= 0 && right.get(right_idx) == right_sum) {
                right_cnt++;
                right_idx--;
            }
            cnt += left_cnt * right_cnt;
            
        }
        else if(left_sum + right_sum > s) {
            right_idx--;
        }
        else if(left_sum + right_sum<s) {
            left_idx++;
        }
    }
    if (s == 0) {
        cnt -= 1;
    }
    System.out.println(cnt);
*/
