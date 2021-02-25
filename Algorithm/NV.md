

# 2019 AI HACKERTON TEST

## Prob1

주사위의 현 상태 배열을 최소한의 이동을 통해 같은 면으로 통일하는 문제로, 6가지 면에 대해 각 면에서 면으로의 이동 값을 2차원 배열로 미리 선언한 뒤, 각 이동 값들을 모두 더해 총 이동값을 구하는 방식으로 풀었다. ACM-ICPC나 Google Code Jam에서 자주 나오는 지도 위의 방향 전환 문제에서 힌트를 얻었다.

```java
public class Solution{
    public static int MINVAL = 99999999;
    public static int[7][7] cost = {
        {0, 1, 2, 3, 4, 5, 6},
        {0, 0, 1, 1, 1, 1, 2},
        {0, 1, 0, 1, 1, 2, 1},
        {0, 1, 1, 0, 2, 1, 1},
        {0, 1, 1, 2, 0, 1, 1},
        {0, 1, 2, 1, 1, 0, 1},
        {0, 2, 1, 1, 1, 1, 0},
    };
    
    public static void main(String[] args){
        int N;
        int[] A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt()~
        }
        return 0;
    }
    
    public static int solution(int[] A){
        int len = A.length;
        int min_move = MINVAL;
        for(int top=1; top<=6; top++){
            int move = 0;
            for(int i=0; i<len; i++){
                move += cost[A[i][top]];
            }
            min_move = Math.Min(min_move, move);
        }
        return min_move;
    }
}
```

## Prob2

이진 트리와 set을 활용해 중복되는 않는 노드들로 이루어진 최대 height를 구하는 문제였는데, 해당 자료구조에 익숙한 사람이었다면 1, 3번에 비해 비교적 쉬웠을 것 같다.

```C++
#include <cstido>
#include <set>
#include <algorithm>

using namespace std;

typedef struct TREE{
    int x;
    TREE* 1;
    TREE* r;
} tree;
set<int> s;

int solution(tree * T){
    bool flag = false;
    int max_len = 0;
    
    if(!s.count(T->x)){
        s.insert(T->x);
        flag = true;
    }
    max_len = s.size();
    
    if(T->1 != NULL) max_len = max(max_len, solution(T->1));
    if(T->r != NULL) max_len = max(max_len, solution(T->r));
    if(flag) s.erase(s.find(T->x));
    
    return max_len;
}
```

## Prob3

겨울과 여름의 경계선을 구하는 문제로, 전형적인 DP 문제였다. 왼쪽부터 순회하면서 구간별 최대 온도를 저장하고, 다시 오른쪽부터 순회하면서 구간별 최저 온도를 저장한 뒤, 최대 온도보다 최저 온도가 높은 지점을 반환하도록 했다.

```c++
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
 
const int MAXVALUE = 1500000000;
const int MINVALUE = -1500000000;
typedef struct NODE{
	int lmax;
	int rmin;
}Node;
 
vector <Node> dp;
 
int solution(vector <int> &A){
	int len = A.size();
	dp.resize(len);
	
	int lmax = MINVALUE;
	int rmin = MAXVALUE;
	for(int i = 0; i < len; i++){
		lmax = max(lmax, A[i]);
		dp[i].lmax = lmax;
	}
	for(int i = len-1; i >= 0; i--){
		rmin = min(rmin, A[i]);
		dp[i].rmin = rmin;
	}
	
	for(int i = 0; i < len-1; i++){
		if(dp[i].lmax < dp[i+1].rmin)	return i+1;
	}
	return len;
}
 
int main() {
	// N 2~300,000
	int N;
	scanf("%d", &N);
	vector <int> A(N);
	for(int i = 0; i < N; i++){
		scanf("%d", &A[i]);
	}
	printf("%d\n", solution(A));
	return 0;
}
 
// 겨울 짧게
// 5, -2, 3, 8, 6
// 3
// 3까지
// -5, -5, -5, -42, 6, 12
// 4
// -42까지
// 1,000,000,000
```

# 2020 AI HACKERTON TEST

## Prob1

문자열 concatenation 문제로, 중복을 제외한 가장 긴 문자열을 생성하는 문제다. Trie를 사용해 풀어야하나 싶었는데 생각보다 범위가 작아 그냥 해쉬와 DFS를 이용해 완전탐색으로 풀었다.

**C++**

```c++
#include <cstdio>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

#define FOR(i,s,e) for(int i = s; i < e; i++)

int N;
vector<bool> selected;
vector<string> B;

int dfs(int idx, bool sel) {
	if (idx == N) {
		vector<bool> hash(26, false);

		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if (!selected[i])	continue;
			for(int s = 0, sz = B[i].size(); s < sz; s++) {
				if (hash[i]) {
					return 0;
				}
				hash[i] = true;
				cnt++;
			}
		}
		return cnt;
	}
	selected[idx] = sel;
	return max(dfs(idx+1, true), dfs(idx+1, false));
}

int solution(vector<string> &A) {
	for(int i = 0, len = A.size() ; i < len; i++) {
		vector<bool> hash(26, false);
		for(int s = 0, sz=A[i].size(); s < sz; s++) {
			if (hash[s]) {
				A.erase(A.begin() + i);
				break;
			}
			hash[s] = true;
		}
	}
	N = A.size();
	B = A;
	return max(dfs(0, true), dfs(0, false));
}
```

**Python**

```python
function solution($A) {
    return recursive($A);
}

function recursive($A, $word="", $len=0) {
    $_len = $len;
    foreach($A as $a) {
        $_word = $word . $a;
        
        $next = true;
        $b = array();
        $c = strlen($_word);
        for($i = 0; $i < $c; $i++) {
            $d = substr($_word, $i, 1);
            if(in_array($d, $b)) {
                $next = false;
            } else {
                $b[] = $d;
            }
        }

        if($next) {
            $len = $c;
            if($len > $_len) {
                $_len = recursive($A, $_word, $len);
            }
        }
    }
    
    return $_len;
}
```

## Prob2

전형적인 팰린드롬 문제로, 빈칸이 포함된 문자열의 팰린드롬 가능 여부를 판단한다.

## Prob3

앞뒤가 랜덤한 동전 배열을 조건에 맞는 패턴으로 바꾸는데 필요한 최소 뒤집기 횟수를 구하는 문제다. 범위가 작아서 완전탐색으로 풀었다.

## Prob4

주어진 수들을 특정 연산 후의 결과를 이진법으로 나타냈을 때 1이 나타나는 개수를 구하는 문제다. 숫자 범위가 작아 long long과 string으로 변환해 풀었다.