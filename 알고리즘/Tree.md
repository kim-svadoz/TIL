



# Tree :fist_oncoming:

# > 트리와 이진트리 기본

---

## 트리(Tree)

- 계층적인 구조를 표현하기 위해 사용하는 자료구조
  - 조직도
  - 디렉토리와 서브디렉토리 구조
  - 가계도

## 용어

- 루트(Root)
  - 트리는 노드(node)들과 노드들을 연결하는 링크(link)들로 구성된다.
  - 맨 위의 노드를 루트라고 한다.
- 부모-자식(parent-child) 관계
  - 각 노드들의 상하 관계를 부모-자식(parent-child)관계로 나타낸다.
- 형제 관계(sibling)
  - 루트노드를 제외한 트리의 모든 노드들은 유일한 부모노드를 가진다.
  - 부모가 동일한 노드들을 형제관계라고 부른다.
- 리프(leaf) 노드
  - 자식이 없는 노드들을 leaf 노드라고 부른다.
  - 리프노드가 아닌 노드들을 내부(internal)노드라고 부른다.
- 조상-자손(ancestor - descendant) 관계
  - 부모-자식관계를 확장한 것이 조상-자손 관계이다.
- subtree
  - 트리의 특성상 한 노드의 자손노드들의 집합도 트리이다.
  - 트리에서 어떤 한 노드와 그 노드의 자손들로 이루어진 트리를 subtree라고 한다.
- 레벨(level)
  - root 노드는 level 1
  - root 노드의 자식노드들은 level 2
  - ...
- 높이
  - 서로다른 레벨의 수이다.

## 트리의 기본적인 성질

- 노드가 N개인 트리는 항상 N-1개의 링크(link)를 가진다.
- 트리의 루트에서 어떤 노드로 가는 경로는 유일하다. 또한 임의의 두 노드간의 경로도 유일하다.(같은 노드를 두 번 이상 방문하지 않는다는 조건하에)

## 이진트리(binary tree)

- 이진 트리에서 각 노드는 최대 2개의 자식을 가진다.
- 각각의 자식 노드는 자신이 부모의 왼쪽자식인지 오른쪽 자식인지가 지정된다.(자식이 한명인 경우에도)

### 응용 예 - Expression Tree

- 수식을 트리로 구성하여 해석한다.
- 연산자들이 이진연산자라면 이진 트리의 형태로 표현할 수 있다.

![image-20200828134120186](https://user-images.githubusercontent.com/58545240/91522941-62019c80-e936-11ea-9f67-21ffc1ee920c.png)

### 응용 예 - Huffman Code

- 파일압축과 관련된 유명한 알고리즘 중 하나인 허프만 코딩을 할 때, 각 문자를 `encoding`하는데 허프만 tree를 구성한다.
- 추후에 자세히!

![image-20200828134257871](https://user-images.githubusercontent.com/58545240/91522965-6f1e8b80-e936-11ea-967a-a05f46dfb03c.png)

### Full and Complete Binary Trees

- 꽉찬 이진트리와 완전 이진트리
- 높이가 h인 full binary tree는 2^h-1개의 노드를 가진다.
- 노드가 N개인 full 혹은 complete binary 트리의 높이는 O(logN)이다. (노드가 N개인 이진트리의 높이는 최악의 경우 N이 될 수도 있다.)

## 이진트리의 표현

- 힙을 표현했을 때는, 완전 이진 트리의 형태 였기 때문에 부모와 자식간의 관계가 배열로 표현하기에 충분했다. 하지만, 완전 이진트리가 아닌 형태라면 아래와 같은 구조의 표현이 필요하다.

- 연결구조(Linked Structure)

  - 각 노드에 하나의 데이터 필드와 왼쪽자식(left), 오른쪽 자식(right), 그리고 부모노드(p)의 주소를 저장(부모노드의 주소는 반드시 필요한 경우가 아니면 보통 생략함)

  ![image-20200828140842141](https://user-images.githubusercontent.com/58545240/91523715-6929aa00-e938-11ea-82cb-33acdf530f2f.png)

  - 예시
    - 트리상의 노드를 객체로 표현하여 이진트리를 Linked Structure로 표현할 수 있다.

  ![image-20200828140856659](https://user-images.githubusercontent.com/58545240/91523723-6fb82180-e938-11ea-89e6-7aa8d1b83f2e.png)

    

## 이진트리의 순회(traversal)

- 순회 
  - 이진 트리의 모든 노드를 방문하는 일
- 중위(inorder) 순회
- 전위(preorder) 순회
- 후위(postorder)순회
- 레벨오더(level-order) 순회

### inorder-중위 순회

- 순회는 본질적으로 recursive한 알고리즘이다.
- 이진트리를 루트노드, left Tree, right Tree로 나누어 생각한다.
- 먼저 left Tree를 inorder로 순회하고,
- root를 순회하고,
- right Tree를 inorder로 순회한다.
- **pseudo code**
  - x를 루트로 하는 트리를 inorder 순회
  - 시간복잡도 O(n)

```java
INORDER-TREE-WALK(x)
  if x != NULL
    then INORDER-TREE-WALK(left[x])
         print key[x]
         INORDER-TREE-WALK(right[x])
```

### postorder와 preorder순회

- inorder 순회와 다른점은 노드를 방문하는 순서뿐이다. 나머지의 개념은 동일하다.

- 트리를 방문하는 순서만 바뀐다.

- preorder-전위 순회

  - root를 순회하고,
  - 먼저 left Tree를 inorder로 순회하고,
  - right Tree를 inorder로 순회한다.

  ```java
  PREORDER-TREE-WALK(x)
    if x != NULL
      then print key[x]
           PREORDER-TREE-WALK(left[x])
           PREORDER-TREE-WALK(right[x])
  ```

- postorder-후위 순회

  - 먼저 left Tree를 inorder로 순회하고,
  - right Tree를 inorder로 순회하고,
  - root를 순회한다.

  ```java
  POSTORDER-TREE-WALK(x)
    if x != NULL
      then POSTORDER-TREE-WALK(left[x])
           POSTORDER-TREE-WALK(right[x])
           print key[x]
  ```

### Expression Trees

![image-20200828140911666](https://user-images.githubusercontent.com/58545240/91523736-75ae0280-e938-11ea-9dd4-292568bdf801.png)

- Expression 트리를 inorder-중위 순회하면 다음과 같이 출력됨
  - x + y * a + b / c
- 각 부트리를 순회할 때 시작과 종료시에 괄호를 추가하면 다음과 같이 올바른 수식이 출력됨
  - (x + y) * ((a + b) /c)
- postorder-후위 순회하면 후위표기식이 출력됨
  - x y + a b + c / *

### lever-order 순회

![image-20200828140923359](https://user-images.githubusercontent.com/58545240/91523743-7ba3e380-e938-11ea-98d6-4d152b3dfec4.png)

- 레벌 순으로 방문, 동일 레벨에서는 왼쪽에서 오른쪽 순서로 방문
- 큐(queue)를 이용하여 구현
  - 맨 처음 큐에 루트 노드 3을 넣는다.
  - 큐를 꺼내고, 3을 출력한 뒤,
  - 3의 왼쪽자식 1과, 오른쪽자식 5를 큐에 넣는다.
  - 1을 꺼내고 출력한 뒤, 1의 자식인 0과 2를 큐에 넣는다.
  - 다음 순서로 5를 꺼내고 출력한 뒤, 5의 왼쪽 자식인 4와 오른쪽 자식인 6을 큐에 넣는다.
- pseudo code

```java
LEVEL-ORDER-TREE-TRAVERSAL()
  visit the root;
  Q <- root       //Q is a queue
  while Q is not empty do
    v <- dequeue(Q);
    visit children of v;
    enqueue children of v into Q;
  end.
end.
```

# > 이진탐색트리(BST)

## Dynamic Set

집합이다. 여러개의 데이터의 집합인데, 그것들의 내용이 고정되지 않고, 생성과 삭제를 반복하면서 유동적인 집합이다. 아래와 같은 특징을 가진다. Dynamic Set, Dictionary 또는 Search Structure라고 불린다.

- 여러 개의 키(key)를 저장
- 다음과 같은 연산들을 지원하는 자료구조
  - INSERT - 새로운 키의 삽입
  - SEARCH - 키 탐색
  - DELETE - 키의 삭제
- 예: 심볼 테이블
- 일반적으로 구현할 때 배열 or 연결리스트를 사용한다.
  - 각 동작에 있어서 다음과 같은 시간복잡도를 가진다.
  - 정렬된 배열의 이진탐색 - O(logn)
  - 정렬된 배열에서 원소를 insert, delete하면 나머지 원소들을 한칸식 shift - O(n)
  - 정렬된 연결리스트의 경우는 이진탐색 불가 - O(n)을 피할 수 없음.
  - 정렬된 연결리스트에서 insert할 자리를 찾는 시간 - O(n)

| 종류       | 정렬 | search  | insert | delete                          |
| :--------- | :--- | :------ | :----- | :------------------------------ |
| 배열       | O    | O(logn) | O(n)   | O(n)(삭제 후 shift)             |
|            | X    | O(n)    | O(1)   | O(1)(찾았다고 가정하고, 삭제만) |
| 연결리스트 | O    | O(n)    | O(n)   | O(1)(찾았다고 가정하고, 삭제만) |
|            | X    | O(n)    | O(1)   | O(1)(찾았다고 가정하고, 삭제만) |

- 정렬된 혹은 정렬되지 않은 배열, 연결 리스트를 사용할 경우 INSERT, SEARCH, DELETE 중 적어도 하나는 O(n)의 시간복잡도를 가짐.
- 3가지 연산 모두 O(n)의 시간을 갖지 않는 효율적인 알고리즘은 없는가?
  - 이진탐색트리(Binary Search Tree), 레드-블랙 트리, AVL- 트리등의 트리에 기반한 구조들
  - Direct Address Table, **해시** 테이블 등

## 검색트리

- Dynamic set을 트리의 형태로 구현
- 일반적으로 SEARCH, INSERT, DELETE 연산이 트리의 높이(height)에 비례하는 시간복잡도를 가진다.
- 이진검색트리, 레드-블랙트리, B-트리 등  

## 이진검색트리(BST)

- 이진 트리이면서
- 각 노드에 하나의 키를 저장
- 각 노드 v에 대해서 그 노드의 왼쪽 서브트리에 있는 키들은 key[v]보다 작거나 같고, 오른쪽 부트리에 있는 값은 크거나 같다.

### SEARCH

![image-20200828140947553](https://user-images.githubusercontent.com/58545240/91523751-8199c480-e938-11ea-9442-5b721470f197.png)

- pesedo code

  - 키 값에 따라서 왼쪽 서브트리 또는 오른쪽 서브트리를 recursive하게 탐색한다.
  - 시간복잡도: O(h), 여기서 h는 트리의 높이 이다.
  - Recursive Version

  ```java
  TREE-SEARCH(x, k)
    if x = NULL or k = key[x]
      then return x
    if k < key[x]
      then return TREE-SEARCH(left[x], k)
      else return TREE-SEARCH(right[x], k)
  ```

  - Iterative Version

  ```java
  TREE-SEARCH(x, k)
    while x!=NULL and k!=key[x]
      do if k < key[x]
        then x <- left[x]
        else x <- right[x]
    return x
  ```

#### 최소값 찾기

- 최소값은 항상 가장 왼쪽 노드에 존재
- 시간복잡도: O(h)
- 최대 값은 반대로 가장 오른쪽 노드를 찾아가는 방법으로 찾는다.

```java
TREE-MINIMUM(x)
  while left[x] != null
    do x <- left[x]
  return x
```

#### Successor 찾기

- 노드 x의 successor란 key[x]보다 크면서 가장 작은 키를 가진 노드
- 모든 키들이 서로 다르다고 가정한다.
- successor의 3가지 경우
  - 노드 x의 오른쪽 서브트리가 존재할 경우, 오른쪽 서브트리의 최소값
  - 오른쪽 서브트리가 없는 경우, 어떤 노드 y의 왼쪽 서브트리의 최대값이 x가 되는 그런 노드 y가 x의 successor
    - 부모를 따라 루트까지 올라가면서 처음으로 왼쪽 링크를 타고 올라간 해당 노드가 y
    - 여기서는 4(x)의 successor를 구할 때, 루트까지 올라가면서 처음으로 왼쪽 링크를 타고 위로 올라간 노드 6(y).
  - 그런 노드 y가 존재하지 않을 경우 successor가 존재하지 않음(즉, x가 최대값)

![image-20200828140959411](https://user-images.githubusercontent.com/58545240/91523758-878fa580-e938-11ea-8346-c573d800fc99.png)

- peudo code
  - 1,2 : 오른쪽 서브트리가 존재할 경우, 오른쪽 트리의 최소 값을 찾는다.
  - 3 : y는 x의 부모노드
  - 4, 5, 6 : 부모노드가 null이 아니고, x가 부모의 오른쪽 자식일 경우에만 계속해서 부모를 찾아 올라간다.
  - 7 : 부모를 찾아 올라가다가 y가 null이거나, 누군가의 왼쪽 자식이 되는 경우 null 또는 y값을return 한다.
  - 시간복잡도 : O(h)

```java
TREE-SUCCESSOR(x)
1  if right[x] != NULL
2    then return TREE-MINIMUM(right[x])
3  y <- p[x]
4  while y != NULL and x = right[y]
5    do x <- y
6       y <- p[y]
7  return y  
```

#### Predecessor

- 노드 x의 predecessor란 key[x]보다 작으면서 가장 큰 키를 가진 노드
- Successor와 반대

 

### INSERT

- 14의 삽입 위치를 찾으러 13의 오른쪽으로 내려갔는데, x의 위치가 null이 되므로 항상 x의 부모 노드 y를 관리해 준다.

![image-20200828141010611](https://user-images.githubusercontent.com/58545240/91523768-8d858680-e938-11ea-9d06-afd77ab0ed90.png)

- pseudo code
  - T는 Tree, z는 insert할 노드
  - 1,2 : y = null, x = root노드 에서 출발
  - 3 : x가 null이 아닐때 까지 
  - 4 : y에 x를 저장해놓고,
  - 5,6,7 : z와 x의 키값을 비교해서 z의 키값이 x의 키값보다 작으면 왼쪽 노드로, z의 키값이 더 크면 오른쪽 노드로 내려간다. 내려간 뒤, 다시 y에 x를 저장, 반복
  - 8 : 새로운 노드 z의 부모는 y가 된다.
  - 9,10 : y가 NULL이라면, Tree가 NULL이라는 의미이고 z는 root가 된다.
  - 11,12 : z의 키 값이 y보다 작다면 왼쪽 자식이 되고,
  - 13,14 : z의 키 값이 y보다 크다면 오른쪽 자식이 된다.
- 시간복잡도는 : O(h)
  - 트리의 높이보다 더 많은 시간을 필요로 하진 않는다.

```java
TREE-INSERT(T, z)
01  y <- NULL
02  x <- root[T]
03  while x!= NULL
04    do y <- x
05      if key[z] < key[x]
06        then x <- left[x]
07      else x <- right[x]
08  p[z] <- y
09  if y = NULL
10    then root[T] <- z
11  else if key[z] < key[y]
12    then left[y] <- z
13  else
14    then right[y] <- z  
```

### DELETE

- Case 1 : 자식노드가 없는 경우
  - 리프노드인경우 그냥 삭제한다.

![image-20200828141022597](https://user-images.githubusercontent.com/58545240/91523782-937b6780-e938-11ea-9d70-8424cc4d795f.png)

- Case 2 : 자식노드가 1개인 경우
  - 자신의 자식 노드를 원래 자신의 위치가 될 수 있도록 연결한다.

![image-20200828141037478](https://user-images.githubusercontent.com/58545240/91523799-9bd3a280-e938-11ea-9377-b4e80685f256.png)

- Case 3 : 자식노드가 2개인 경우
  - 가장 복잡한 경우이다. 자식 노드가 2개인 노드를 삭제하는 경우 트리의 구조가 깨지기 때문에 다시 조건을 만족하는 트리를 만들어주는 작업이 필요하다.
  - 13을 삭제하는 경우, 13의 노드에 있는 데이터만 삭제하고,
  - 삭제하려는 노드의 Successor의 데이터만 copy해서 가져온다.
  - 그리고 Successor를 삭제한다.
  - **자식노드가 2개인 노드의 Successor는 왼쪽 자식이 없다는 것이 보장되어 있다.** 하지만, 오른쪽 자식은 있을 수도 있고 없을 수도 있다. 즉, 자식노드가 0개 또는 1개이다.
  - 이 경우, Case 1 또는 2에 해당하므로, 방법에 맞게 삭제한다.

![image-20200828141048342](https://user-images.githubusercontent.com/58545240/91523811-a4c47400-e938-11ea-9b84-2a4da86031a1.png)

![image-20200828141100931](https://user-images.githubusercontent.com/58545240/91523822-ac841880-e938-11ea-8b46-9bf613298c7d.png)

- Case별 예시

![image-20200828141119116](https://user-images.githubusercontent.com/58545240/91523833-b279f980-e938-11ea-933e-38d556eb936f.png)

- pseudo code
  - T는 트리 z는 삭제할 노드, 삭제할 노드를 search하는 작업은 이루어 졌다고 가정하고, 삭제하는 로직
  - 1,2,3 : 실제로 트리에서 삭제할 노드 y를 정한다. 자식노드가 0개 또는 1개일 경우 실제로 해당 노드를 삭제할 것이기 때문에 y에 z를 대입한다. 자식노드가 2개일 경우 삭제할 노드 y는 z의 successor를 대입하여 삭제한다.
  - 따라서, 4 - 13라인은 실제 노드 y를 삭제하는 경우이고, 14 - 17라인은 노드 z의 Successor를 삭제하는 case 3인 경우이다.
  - 4, 5, 6 : y의 자식은 0개 또는 1개인 상태이다. y의 왼쪽 자식이 존재한다면, x는 y의 왼쪽 자식이고, 그렇지 않다면 x는 y의 오른쪽 자식이다.
  - 7, 8 : 그리고 x가 NULL이 아니라면, 현재 y의 부모를 x의 부모로 설정한다.
  - 9,10 : y의 부모가 없다면, 즉 y가 루트노드라면, x를 루트로 설정한다.
  - 11,12 : 그렇지 않고 y가 자신의 부모의 왼쪽 자식이었다면, 삭제될 노드 y의 부모의 왼쪽 자식을 x로 설정하고
  - 13 : 그렇지 않고 y가 자신의 부모의 오른쪽 자식이었다면, 삭제될 노드 y의 부모의  오른쪽 자식으로 x를 설정한다. 이렇게 해서 실제 y를 삭제하는 일이 끝났다.
  - 14, 15, 16 : 삭제하려는 노드 z와 실제 트리에서 삭제될 노드 z가 다르다는 것은 case 3에 해당한다는 이야기 이므로, y에 보관되어 있는 데이터를 z의 자리로 옮기고(y의 키값만이 아닌 satellite data들 까지 모두), 실제 삭제된 노드의 데이터 y를 리턴한다.
  - 시간복잡도 O(h)

```java
TREE-DELETE(T, z)
01  if left[z] = NULL or right[z] = null
02    then y <- z
03  else y <- TREE-SUCCESSOR(z)
04  if left[y] != NULL
05    then x <- left[y]
06  else x <- right[y]
07  if x != NULL
08    then p[x] <- p[y]
09  if p[y] = NULL
10    then root[T] <- x
11  else if y = left[p[y]]
12    then left[p[y]] <- x
13  else right[p[y]] <- x
14  if y != z
15    then key[z] <- key[y]
16      copy y's satellite data into z
17  return y
```

## BST 정리

- 각종 연산의 시간복잡도는 O(h)이다.
- 최악의 경우 트리의 높이 h는 O(n)이다.
  - 실제 위에서 학습한 BST의 Search, Insert, Delete 모두 최악의 경우 시간복잡도는 O(n)이다.
  - 그러나, 이것은 실제 최악의 경우에 해당한다. BST에 데이터들이 random하게 구성된다고 가정했을때, 평균 트리의 높이는 O(logn)이 된다. 이는 Search, Insert, Delete 연산의 시간복잡도가 O(logn)이 된다는 이야기이다.
- 최악의 경우에도 O(logn)을 넘지 않도록 하는 균형잡힌 트리
  - 레드-블랙 트리 등
  - 키의 삽입이나 삭제시 추가로 트리의 균형을 잡아줌으로써 높이를 O(logn)으로 유지한다.

# > 부록 - 이진트리

---

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