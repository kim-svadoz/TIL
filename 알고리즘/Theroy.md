# 알고리즘 이론:fist_oncoming:

> C 와 C++과  java가 섞여있을 수 있습니다!

# > 회문판별

> - 회문은 유전자 염기서열 분석에서 많이 쓰인다
> - 회문(palindrome)은 순서를 거꾸로 읽어도 제대로 읽은 것과 같은 단어와 문장을 말한다.

```C
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(){
    char word[30];				// 단어 저장 배열
    int length;					// 문자열 길이
    bool isPalindrome = true;	// 회문 판별값을 저장할 변수, 초깃값 TRUE
    
    printf("단어 입력하세요: ");
    scanf("%s", word);
    
    length = strlen(word);
    
    // 0부터 문자열 길이의 절반만큼 반복
    for(int i=0; i<length/2; i++){
        // 왼쪽 문자와 오른쪽 문자를 비교하여 문자가 다르면
        if(word[i] != word[length-1-i]){
            //회문이 아님
            isPalindrome = false;
            break;
        }
    }
    printf("%d\n", isPalindrome);	// 회문 판별값 출력
    
    return 0;
}
```

# > N-gram

> - 빅데이터 분석, 검색 엔진에서 많이 쓰인다. 구글은 책들을 스캔해서 N-gram viewer를 만들었는데 사람들의 언어 패턴을 시대별로 분석하기도 함.
> - N-gram은 문자열에서 N개의 연속된 요소를 추출하는 방법. 만약 "hello"라는 문자열을 2-gram으로 추출하면
>   - He, el, ll, lo

- 글자 단위 N-gram( 2gram )

```C
#include <stdio.h>
#include <string.h>

int main(){
    char text[30] = "Hello";
    int length;
    
    length = strlen(text);
    
    for (int i=0; i<length; i++){
        printf("%c%c\n", text[i], text[i+1]);		// 현재 문자와 그 다음 문자 출력
    }
    
    return 0;
}
```

- 단어 단위 N-gram( 2gram )

```C
#define _CRT_SECURE_NO_WARNINGS 	// strtok 보안 경고로 인한 컴파일 에러 방지
#include <stdio.h>
#include <string.h>

int main(){
    char text[100] = "this is c language";
    char *tokens[30] = { NULL, };		// 자른 문자열의 포인터를 보관활 배열, NULL로 초기화
    int count = 0;
    
    char *ptr = strtok(text, " ");		// " " 공백 문자를 기준으로 문자열을 자름, 포인터 반환
    
    while(ptr != NULL){	// 자른 문자열이 나오지 않을 때까지 반복
        token[count] = ptr;		// 문자열을 자른 뒤 메모리 주소를 포인터 배열에 저장
        count++;
        
        ptr = strtok(NULL, " ");		// 다음 문자열을 잘라서 포인터를 반환
    }
    
    // 2-gram이므로 배열의 마지막에서 요소 한 개 앞까지만 반복함
    for(int i=0; i<count-1; i++){
        printf("%s %s\n", tokens[i], tokens[i+1]);
    }
    return 0;
}
```

> **N-gram의활용**
>
> ```bash
> 4-gram을 쓰면 picked, picks, picking에서 pick만 추출하여 단어의 빈도를 세는 데 이용된다. 이런 특성 때문에 검색엔진, 빅데이터, 법언어학 분야에서 주로 활용된다.
> 
> 해리포터의 작가 조앤 롤링은 가명으로 <더 쿠쿠스 콜링>이라는 소설을 출간한 적이 있는데 그 작가가 조앤롤링 이라는 것을 밝혀내는데 N-gram을 비롯한 다양한 기법이 동원되었다. 즉, 사람마다 사용하는 문장에 패턴이 있는것. 그래서 같은 의미라 하더라도 사람마다 단어 선택이 다르다는 것을 통계적으로 분석해낸 사례이다.
> ```

# > 두 점 사이 거리 구하기

```C
#include <stdio.h>
#include <math.h> 		// sqrt함수

struct Point2D{
    int x;
    int y;
};

int main(){
    struct Point2D p1;
    struct Point2D p2;
    
    p1.x = 30;
    p1.y = 20;
    
    p2.x = 60;
    p2.y = 50;
    
    int a = p2.x - p1.x;
    int b = p2.y - p1.y;
    
    double c = sqrt((a * a) + (b * b));
    
    printf("%f\n", c);
    
    return 0;
}
```

# > 이진트리

## 1. 후위 순회(postorder)

이진 트리(binary tree)의 후위 순회 알고리즘이 사용될 수 있는 대표적인 예는 **특정 디렉토리(directory)의 용량 계산**이다. 단, 이진 트리이기 때문에 특정 디렉토리(=폴더)의 서브 디렉토리의 개수는 2개 이하로만 존재해야 한다. 삼진 트리(ternary tree)였다면 서브 디렉토리는 총 3개까지 존재할 수 있다.

### 1.1 디렉토리의 용량 계산

디렉토리의 용량 계산을 위해서는 **어떤 알고리즘이 사용되야하는가**를 먼저 고민해보자. 생각을 할 때 구체적인 상황을 두고 예시를 들어보면 이해가 빠르다. 현재 사용하는 컴퓨터에 `datastructure` 라는 디렉토리가 있다고 가정해보자. 이 `datastructure` 디렉토리 내부에는 `stack`, `queue` 라는 서브 디렉토리가 존재한다.

- `datastructure/stack`
- `datastructure/queue` 이 때 `datastructure` 디렉토리의 크기를 구하려면 어떻게 해야할까?

서브 디렉토리인 `stack` 디렉토리와 `queue` 디렉토리의 크기를 각 계산하여 합하면 `datastructure` 폴더의 크기를 계산할 수 있다. **서브 디렉토리의 크기를 더해 현재 디렉토리의 크기를 계산한다**는 아이디어를 생각해보면 서브 디렉토리의 크기를 먼저 계산하는데, 바로 이러한 특징 때문에 후위 순회 알고리즘을 사용해야 한다.

### 1.2 코드

> *후위 순회: 트리를 탐색할 때 왼쪽 서브트리, 오른쪽 서브트리, 루트 순으로 방문하는 순회 알고리즘을 일컫는 말이다. 후위 순회 알고리즘은 아래와 같다.*

```C
// 후위 순회 알고리즘
void postorder(TreeNode *root){
    if(root){
        postorder(root->left);		// 왼쪽 서브 트리를 가장 먼저 순회
        postorder(root->right);		// 다음으로 오른쪽 서브 트리를 순회
        printf("%d", root->data);	// 마지막으로 루트의 노드를 방문
    }
}
```

위와 같은 후위 순회를 사용하여 디렉토리 크기를 계산하는 코드를 작성해보도록 한다. 순환 호출되는 함수가 용량을 반환하도록 만들어줘야 한다.

```C
// 디렉토리 용량 계산 프로그램
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

typedef struct TreeNode{
    int data;
    struct TreeNode *left, *right;
} TreeNode;

int calc_dir_size(TreeNode *root);

// 아래와 같은 트리 구조를 생성한다.
//				n1
//			n3		n2
//		n4		n5
int main(){
    TreeNode n4 = { 500, NULL, NULL };
    TreeNode n5 = { 200, NULL, NULL };
    TreeNode n3 = { 100, &n4, &n5 };
    TreeNode n2 = { 50, NULL, NULL };
    TreeNode n1 = { 0, &n2, & n3 };
    
    printf("디렉토리의 크기 = %d\n", calc_dir_size(&n1));
}

int calc_dir_size(TreeNode *root){
    if(!root) return 0;		// 존재하지 않는 노드일 경우
    else{
        int left = calc_dir_size(root->left);		// 왼쪽 서브트리를 먼저 순회한다
        int right = calc_dir_size(root->right);		// 오른쪽 서브트리를 그 다음으로 순회한다.
        return left + right + root->data;			// 순회한 노드에 해당하는 데이터를 계속해서 더해간다.
    }
    return 0;
}
```

## 2. 이진트리의 높이 구하기

트리의 높이를 구할 때의 핵심 개념은 왼쪽 서브트리의 높이와 오른쪽 서브트리의 높이를 비교하여 **더 큰 쪽을 택한다**는 것이다. 그렇다면 우리에게 필요한 건 서브트리의 높이와 오른쪽 서브트리의 높이다. 높이는 어떻게 구할 수 있을까?

### 2.1 코드

```C
typedef struct TreeNode{
    int data;
    struct TreeNode *left, *right;
} TreeNode;
int get_height(TreeNode *root);
// 아래와 같은 트리 구조를 생성한다.
//      n1
//    n3    n2
//  n4    n5
void main(){
    TreeNode n4 = { 500, NULL, NULL };
    TreeNode n5 = { 200, NULL, NULL };
    TreeNode n3 = { 100, &n4, &n5 };
    TreeNode n2 = { 50, NULL, NULL };
    TreeNode n1 = { 0, &n2, &n3 };
    
    printf("디렉토리의 크기 = %d\n", calc_dir_size(&n1));
}

int get_height(TreeNode *root){		// 트리의 높이를 구한다
    if(!root) return 0;
    else{
        int left_h = get_height(root->left);		// 왼쪽 서브트리의 높이를 순환호출을 통해 얻는다.
        int right_h = get_height(root->right);		// 같은 방법으로 오른쪽 서브트리의 높이를 얻는다.
        return 1 = (left_h > right_h ? left_h : right_h);		// 둘 중 큰 값에 1을 더해 반환한다.
    }
}
```

위 코드를 이해해보자. 순환 알고리즘이 적용된 코드를 작성하거나 읽을 때는 탈출 조건 보다는 **순환호출 되는 부분을 먼저 살펴봐야 한다.** 호출되는 부분은 `int left_h = get_height(root->left)`, `int right_h = get_height(root->right)` 이 두 부분이다.

위 순환 호출되는 코드로 왼쪽 서브트리의 높이 `left_h`와 오른쪽 서브트리의 높이 `right_h`를 구하기 위해 순환 함수로 각 서브트리의 끝(단말 노드)까지 방문할 수 있다. 방문 순서는 `get_height(root->left`가 먼저 호출되었으므로 먼저 왼쪽 서브트리의 단말노드까지 방문할 것이고 탈출 조건 `if (!root)`를 만나면서 왼쪽 서브트리를 빠져나오면 오른쪽 서브트리로 진입한다(`get_height(root->right)`.

다음으로 탈출되는 조건을 살펴보자. 위 코드에서 상정한 탈출 조건은 **단말노드를 만났을 때**다. 단말노드란 자식이 없는 노드를 뜻한다. 그렇다면 단말노드인 것은 어떻게 알 수 있을까?

자식이 없는 노드가 단말노드이므로 아래와 같은 두 조건을 생각해볼 수 있다.

```C
/**
* if (root->left == NULL && root->right == NULL) // 왼쪽 서브트리와 오른쪽 서브트리가 없을 때
* if (root == NULL) or if (!root)	// 방문을 했는데 존재하지 않는 노드였을 경우
*/
```

`root == NULL`의 의미를 좀 더 풀어서 설명하자면 **자식 노드가 있나 살펴보려 서브 트리를 방문했는데 아무것도 발견할 수 없었다**라는 의미다. 위 코드는 이 조건을 적용한 코드다.

마지막으로 `ger_height`함수에서 맨 아래 리턴하는 부분을 살펴보자. **왜 1을 더해서 리턴해주는 걸까?**라는 의문이 든다면 그 이전에 어떤 작업을 시행했는지를 살펴보자. 특히 첫번째 완전한 순환이 끝났을 때를 생각해보자. 순환을 계속하면서 각 서브 트리의 끝까지 방문한 상태(정확히는 오른쪽 서브트리를 끝까지 방문한 상태)에서 한 단계 더 내려가 `root == NULL`이라는 탈출 조건을 만났다. 그렇게 탈출 조건을 만나 빠져나오면 마지막 리턴문을 만나게 되는데 이때의 상황은 더 이상의 자식노드, 서브트리가 없는 상황이므로 리턴문에 있는 `left_h`와 `right_h`는 둘 다 0이 될 것이다.

위 상황에 대한 결론을 내려보면 나에게 자식노드, 서브트리는 존재하지 않지만 적어도 **나는 존재한다는 것을 입증했다는 것**이다. 따라서 나 자신을 하나의 개수로 칠 수 있다. 그래서 1을 더하는 것이다. 1을 더한 후에는 왼쪽 서브트리와 오른쪽 서브트리의 높이 중 더 큰 값을 택하기만 하면 되는 것이다.

순환의 개념을 접할 때는 추상적으로 이해하면 이해에 좀 더 도움이 된다. 큼지막한 매커니즘, 예를 들어 왼쪽 서브트리 높이 구하기, 오른쪽 서브트리 높이 구하기들이 대략 어떤 순서로 실행되는지를 파악하고 리턴은 언제 되고 탈출 조건은 어떻게 되는지를 살펴보는 것이다. 실행 흐름을 하나 하나 다 따지려 하기보다는 먼저 이렇게 개념적으로 접근한 후에 세부적으로 이해하려고 하면 좀 더 편하다.

## 3. 이진트리의 단말 노드 개수 구하기

2번에서 살펴본 알고리즘을 그대로 적용하면 의외로 쉽게 구할 수 있다. 아래의 코드만 보고 개념을 이해하도록 해보자.

### 3.1 코드

```C
typedef struct TreeNode{
    int data;
    struct TreeNode *left, *right;
} TreeNode;

int get_leaf(TreeNode *root);
// 아래와 같은 트리 구조를 생성한다.
//      n1
//    n3    n2
//  n4    n5

void main(){
    TreeNode n4 = { 500, NULL, NULL };
    TreeNode n5 = { 200, NULL, NULL };
    TreeNode n3 = { 100, &n4, &n5 };
    TreeNode n2 = { 50, NULL, NULL };
    TreeNode n1 = { 0, &n2, &n3 };
 
    printf("단말 노드의 갯수 = %d\n", get_leaf(&n1));
}

int get_leaf(TreeNode *root){		// 트리에 존재하는 단말노드의 갯수를 구한다.
    if(!root) return 0;
    if(root->left == NULL & root->right == NULL) return 1;
    else return get_leaf(root->left) + get_leaf(root->right);
}
```

위 코드의 탈출 조건은 `if (root->left == NULL && root->right == NULL)`다. 즉 **자식노드 혹은 서브트리가 존재하지 않을 때**가 탈출 조건이다. 의미는 **자식 노드는 존재하지 않지만 내가 존재한다는 건 적어도 증명했으니 난 1개로 셀 수 있다**는 것이다. 첫번째 `if (!root)`의 조건은 누군가 이 함수를 사용할 때 존재하지 않는 노드를 매개변수로 넘겼을 때를 대비하기 위함이다 .오류를 방지하는 용도로서의 의미를 가진 조건이다. 이 조건을 탈출 조건으로 쓸 수 없는 것은 이 조건 만으로는 단말 노드의 여부를 증명할 수 없기 때문이다. 아무것도 존재하지 않으므로 개수를 셀 수 없다.

# > Tail Call Recursion

> C++

제가 이번에 설명할 것은 제가 검색하다가 발견한! `Tail Call Recursion` 이라는 새로운 재귀?적인 방법의 코딩입니다. 기존의 재귀함수와 비교하면서 설명하도록 하겠습니다.

## 1. 기존의 재귀함수

먼저 기존의 재귀함수를 보도록합니다. 여기서는 가장 대표적인 피보나치 수열을 이용한 재귀함수를 살펴보겠습니다.

```C++
#include <iostram>
using namespace std;

int f(int n){
    if(n < 0) return 0;
    if(n < 2) return n;
    return f(n-1) + f(n-2);
}

int main(void){
    cout << f(4) << '\n';
    
    return 0;
}
```

뭐.. 설명할 것이 많이 없네요. f라는 함수는 피보나치 수열의 n번째 항을 값으로 리턴해주는 함수입니다. 0번째 항은 0으로 정의 하겠습니다. f(4)를 호출하게 되면 피보나치수열의 4번째 값인 ‘3’이 잘 출력되는 프로그램입니다. 여기서! f(4)가 호출되었을 때, 일어나는 과정을 살펴보면.

```bash
f(4) 호출
    f(3) 호출
        f(2) 호출
            f(1) 호출 1 리턴
            f(0) 호출 0 리턴
        f(2) 가 1 리턴
        f(1) 호출 1리턴
    f(3) 이 2 리턴
    f(2) 호출
        f(1) 호출 1 리턴
        f(0) 호출 0 리턴
    f(2) 가 1 리턴
f(4) 가 3 리턴
```

f(4)만 호출해도 머리로만 따라가기엔 조금 벅찹니다.. 물론 컴퓨터는 이러한 연산을 빠르게 해서 답을 잘 내주는 것이죠. 그렇다면 여기서 f(40)을 호출하면 어떻게 될까요? 콘솔이.. 쉽게 답을 내주지 않습니다. 멈춘것이 아니라 계속 연산을 진행중인 것입니다. f(4)만 호출해도 이정도의 양을 연산해야 하는데 f(40)을 호출하게되면 엄청난 양의 연산을 진행해야 하기때문입니다. 게다가 엄청난 양의 Stack공간도 사용하게 됩니다. **왜냐하면 호출 스택에 자신을 호출했던 주소를 저장해놓고 그 위치로 다시 돌아가야 하기 때문이죠.** 100보다 큰 값을 호출하면 프로그램이 죽는 경우도 있을 겁니다..

## 2. Tail Call Recursion

### 2.1 Tail Call

먼저 `Tail Call` 이란 것을 살펴보도록 합시다. tail call 이란 말그대로 꼬리호출, 맨 마지막에서 호출한다는 뜻입니다. 예를 들어 보겠습니다.

```C++
#include <iostram>
using namespace std;

int f(int a){
    a = 0;
    return 0;
}

int foo1(int b){
    return f(b) + 1;
}

int foo2(int c){
    return f(c);
}

int main(void){
    cout << foo1(10) << '\n';
    cout << foo2(10) << '\n';
    
    return 0;
}
```

먼저 `f`라는 함수를 보겠습니다. f는 받은 값을 0으로 만들어 돌려주는 함수입니다. 여기서 `foo1` 을 보면 `f(b)` 를 호출하고 + 1을 한뒤에 리턴합니다. 이렇게 되면 `foo1` 에서 불린 `f(b)` 는 tail call이 **아닙니다.** 왜냐하면 `f(b)` 가 값을 리턴한 후에 다시 `foo1` 으로 **돌아와서** + 1을 해준후에 값을 리턴해야하기 때문입니다. 반면에 `foo2` 를 보게되면 `f(c)` 를 호출하고 바로 리턴합니다. **이렇게 되면 다시 `foo2`로 돌아와서 작업할 필요가 없고 그냥 `f(c)` 의 값만 리턴하면 됩니다.**

즉, `Tail Call` 이란건 함수를 바로 종료하기 위해서 함수의 마지막에서 함수를 호출하는 것을 말합니다. 위의 `foo1` 처럼 마지막에 함수를 호출했다고 다 tail call이 아니라 함수를 호출 했을 때, 함수를 호출한 함수가 바로 종료될 수 있도록 하는 기법입니다.

### 2.2 Tail Call Optimization

이렇게 `Tail Call` 을 사용하게 되면 무엇이 좋은가! 라고 생각하실 수 있습니다. 이렇게 함수의 마지막에서 함수를 호출하게 되면(다른 추가적인 일을 할 필요 없는 상태에서) 다시 이 호출한 함수로 돌아올 필요가 없어지게 됩니다. **함수에서 더 이상 할일이 없기 때문에 Stack에 저장할 필요없이 끝내도 된다는 뜻입니다..!** 그리고 사용한 Stack 공간을 재사용 할 수 있게 됩니다. 이러한 기법을 `Tail Call Optimiztion` 이라고 하는 것입니다. 하지만 이것은 언어가 지원하는 경우에만 해줍니다...

### 2.3 Tail Recursion

그렇다면 `Tail Recursion` 이란? tail call방식으로 함수를 작성하는데 호출하는 함수가 자기자신인 재귀함수인 것입니다. 위의 피보나치 코드를 tail recursion방식으로 작성하여 보면

```c++
#include <iostream>
using namespace std;

int f(int n, int prev=0;, int next =1){
    if(not n) return prev;
    return f(n-1, next, prev + next);
}

int main(){
    cout << f(4) << '\n';
    return 0;
}
```

이렇게 됩니다. 함수 `f` 에서 마지막에 자기자신만 호출하고 아무것도 안하므로 마지막에 호출하는 tail call recursion 형식이 됩니다. f(40)까지 빠르게 구할 수 있게됩니다. 여기에 tail call optimization까지 적용한다면 Stack을 더이상 낭비하지 않고 함수안의 피라미터만 바꿈으로써 추가적인 메모리도 필요하지 않게 됩니다. *wikipedia 에서는 이렇게 되면* `*goto*` *문의 사용과 비슷한 효과를 내며 기계어로* `*JUMP*`*와 같은 코드가 된다고 합니다.*

위의 피보나치 함수와 어떻게 다른지 살펴보면,

```bash
f(4, 0, 1) 호출
    f(3, 1, 1) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
        f(2, 1, 2) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
            f(1, 2, 3) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
                f(0, 3, 5) 피라미터 변경 호출(추가적인 Stack공간의 사용 없음)
                return 3;
            return 3;
        return 3;
    return 3;
return 3;
```

위에서는 f(4)를 호출하게되면 총 9번의 함수호출이 있었지만 tail call recursion을 사용하니 호출이 5개로 확 줄은 것을 볼 수 있습니다. 또한 tail call optimization으로 Stack공간을 추가적으로 사용하지 않고 피라미터의 값만 변경함으로써 메모리공간도 확보되었습니다.

참고한 사이트를 첨부하며 글 마칩니다.

- [https://homoefficio.github.io/2015/07/27/%EC%9E%AC%EA%B7%80-%EB%B0%98%EB%B3%B5-Tail-Recursion](https://homoefficio.github.io/2015/07/27/재귀-반복-Tail-Recursion/)
- https://en.wikipedia.org/wiki/Tail_call

# > Locality의 관점에서 Quick sort가 Merge sort보다 빠른이유

Quick sort와 Merge sort는 nlogn의 시간복잡도를 가지는 대표적인 정렬 방법이다.

일반적으로 Quick sort가 Merge sort보다 크다. 그 이유는 Locality와 관련이 있다. Locality의 개념을 알아보고 왜 Quick sort가 더 빠른지 알아보도록 하자.

## Locality

> *지역성(Locality)은 CPU가 짧은 시간 범위 내에 일정 구간의 메모리 영역을 반복적으로 엑세스하는 경향을 말한다.*

메모리 내의 정보를 균일하게 엑세스 하는게 아닌, 짧은 시간내에 특정 부분을 집중적으로 참조하는 특성이다.

```c
void f()
{
    //간단한 작업을 수행
}

void g()
{
    int arr[1000];
    for(int i=0;i<1000;++i){
        //arr참조
    }
}

int main(void)
{
    f();
    g();
    
    return 0;
}
```

위 코드는 메모리의 모든 부분을 균일하게 엑세스 하지 않는다. 거의 f함수나 g함수 내에서 대부분의 실행이 이루어 진다. ***현재 프로세스의 실행 패턴을 보고 가까운 미래에 프로세스의 코드와 데이터를 합리적으로 사용할 수 있도록 예측 할 수있다.\*** 그래서 해당 코드와 데이터를 캐시에 올린다면 아주 빠른 성능을 얻을 수 있게 된다. 위의 함수 g에서, i와 array는 반복적으로 CPU가 엑세스 한다. 해당 변수를 캐시에도 올린다면 아주 큰 효과를 얻을 수 있다.

여기에 demand paging의 개념으로 확장해보자.

![image-20200811173917448](https://user-images.githubusercontent.com/58545240/90202217-0b657000-de18-11ea-8df8-be27a8ecaebc.png)

자주 사용되는 페이지는 물리 메모리에만 두는게 아니라, 캐시에도 둔다. 그래서 빠르게 해당 페이지에 접근하게 된다.

하지만, 지역성의 정도가 떨어질 때(=여러 페이지에 있는 코드 및 데이터들을 접근할 때) cache에 해당 페이지를 올려도 의미가 없어진다. 캐시를 접근하는게 아니라 메인메모리를 접근하기 때문이다. 결국, cache에 해당 페이지가 있을 수록 더 빠른 데이터 접근을 할 수 있게 된다.

## 정렬 알고리즘 평가 기준

### - Locality

왜 Locality가 정렬 알고리즘의 평가 기준이 될 수 있을까?

정렬하려고 하는 데이터들이 다른 페이지로 이동하는 것 없이, 자신의 페이지에서 계속 있는게 좋다. 다른 캐쉬에 없는 페이지로 이동하면 page cache hit 비율이 떨어지게 된다. 그래서 결국 physical memory로 접근을 해야 하기 때문에 시간이 상대적으로 오래 걸린다. 만약 자신의 페이지에 계속 있는다면, cache에서 반복적으로 접근하기 때문에 시간이 덜 걸린다. 결국 데이터가 이동하지 않을 수록 좋다.

### - 비교, swap 횟수

평소 알고리즘 전공수업에 했던 방법이다.

정렬 알고리즘의 평가는 알고리즘의 비교, swap 횟수를 이용할 수 있다.

```c
// bubble sort
for(int i=0;i<n-1;++i) {
    for(int j=0;j<n-1-i;++j) {
        if(arr[j]>arr[j+1]) {
            int temp = arr[j];
            arr[j] = arr[j+1];
            arr[j+1] = temp;
        }
    }
}
```

위 알고리즘은 구현이 간단하지만 시간복잡도는 n²인 버블 정렬이다. 버블 정렬은 비교 횟수 n², swap횟수 n²이다. 알고리즘의 평가가 단순히 코드상으로 몇 번 비교및 swap 되는 지를 통해서 정렬 알고리즘을 평가한 것이다.

머지, 퀵정렬은 재귀함수로 구현되어 있다. 이 함수들의 시간복잡도를 구하려면 반복대치 방법을 이용하면 된다.

## 정리

대부분의 프로그램들은 짧은 시간동안 반복적으로 접근하는 데이터들이 있다. 이 성질을 참조의 지역성이라고 한다. 그래서 지역성을 띄는 데이터들을 더 빠르게 접근하기 위해서 캐쉬라는 하드웨어를 이용한다. 메인메모리보다 더 빨리 접근하기 위해서 만든 하드웨어이다. 위에서 살펴 본 것과 같이, 퀵정렬은 병합정렬보다 더 참조의 지역성의 성질을 띤다. 즉, 캐쉬의 도움을 더욱 받을 수 있게 된다. 그래서 일반적으로 퀵정렬이 더 빠르다.