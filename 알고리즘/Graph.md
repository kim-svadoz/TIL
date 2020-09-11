

# Graph :fist_oncoming:

# **> Graph Algorithm - 개념과 표현**

---

## Graph

- (무방향) 그래프 G = (V, E)
  - V : 노드 혹은 정점(vertex)
  - E : 노드쌍을 연결하는 Edge 혹은 Link(간선)
  - Object들 간의 이진관계를 표현
  - n = |V|, m = |E|

![image-20200828143623650](https://user-images.githubusercontent.com/58545240/91525664-f8d15780-e93c-11ea-9dd3-c75da58350a4.png)

- 방향 그래프와 가중치 그래프
  - 방향그래프(Directed Graph) G = (V, E)
  - Edge (u, v)는 u로부터 v로의 방향을 가짐
- 가중치 그래프
  - Edge마다 가중치(weight)가 존재

### 그래프의 표현

- 인접행렬(adjacency matrix)

![image-20200828143638892](https://user-images.githubusercontent.com/58545240/91525670-fcfd7500-e93c-11ea-94c9-2bf4e7295fcb.png)

- 인접리스트(adjacency list)

  - 정점 집합을 표현하는 하나의 배열과
  - 각 정점마다 인접한 정점들의 연결 리스트

  ![image-20200828143658731](https://user-images.githubusercontent.com/58545240/91525679-0090fc00-e93d-11ea-895c-ffb31e63dda2.png)

  - 저장 공간 : O(n + m)
  - 어떤 노드 v에 인접한 모든 노드 찾기 : O(degree(v)) 시간
  - 어떤 Edge (u, v)가 존재하는지 검사 : O(degree(u)) 시간

- 방향그래프(directed graph)

  - 인접행렬은 비대칭
  - 인접 리스트는 m개의 노드를 가진다

  ![image-20200828143716749](https://user-images.githubusercontent.com/58545240/91525687-04bd1980-e93d-11ea-8c14-909aa0dbb0c4.png)

- 가중치 그래프의 인접행렬 표현

  - 엣지의 존재를 나타내는 값으로 1대신 엣지의 가중치를 저장
  - 엣지가 없을 때 혹은 대각선
    - 그때마다 상황에 따라 정의 가능
    - 특별히 정해진 규칙은 없으며, 그래프와 가중치가 의마하는 바에 따라서
    - Ex) 가중치가 거리 혹은 비용을 의미하는 경우라면 엣지가 없으면 oo(무한대), 대각선은 0
    - Ex) 만약 가중치가 용량을 의미한다면 엣지가 없을때 0, 대각선은 oo(무한대)

### 경로와 연결성

- 인접하다라는 것은 해당 경로를 거쳐서 그 노드에 도달할 수 있다라는 것이고, 연결되어 있다라는 것은 노드와 노드를 연결하는 경로가 존재할 때를 말한다.
- 무방향 그래프 G = (V, E)에서 노드 u와 노드 v를 연결하는 경로(path)가 존재할 때 v와 u는 서로 연결되어 있다고 말함
- 모든 노드 쌍들이 서로 연결된 그래프를 연결된(connected) 그래프라고 한다.
- 연결요소(connected component)

![image-20200828143739748](https://user-images.githubusercontent.com/58545240/91525707-0f77ae80-e93d-11ea-8ead-4230a975452b.png)



# **> BFS(Breadth-First Search, 너비우선탐색)**

---

## 그래프 순회

- 순회(traversal)
  - 그래프의 모든 노드들을 방문하는 일
- 대표적 두 가지 방법
  - BFS (Breadth-First Search, 너비우선순회)
  - DFS (Depth-First Search, 깊이우선순회)

### 너비우선탐색(BFS)

- BFS 알고리즘은 다음 순서로 노드들을 방문

  - L0 = {s}, 여기서 s는 출발 노드
  - L1 = L0의 모든 이웃 노드들
  - L2 = L1의 이웃들 중 L0에 속하지 않는 노드들
  - ...
  - Li = Li-1의 이웃들 중 Li-2에 속하지 않는 노드들
  - 한마디로 그래프에서 노드들을 동심원의 형태로 순회하는 방법

  ![image-20200828143917671](https://user-images.githubusercontent.com/58545240/91525714-17cfe980-e93d-11ea-9364-06bf8287dfab.png)

### 큐를 이용한 너비우선탐색

- 출발노드를 check하고 시작한다.
- 큐에 스타트 노드(1번노드)를 삽입한다.

![image-20200828143929375](https://user-images.githubusercontent.com/58545240/91525727-1dc5ca80-e93d-11ea-8cb4-646a6d314c30.png)

- whil문을 돌면서 큐가 비어있을 때까지 반복한다.
  - 큐에서 노드(v)를 하나 꺼내고
  - 꺼낸 노드의 인접노드 중, 아직 방문되지 않은(unchecked) 노드들(w)을 체크하고 큐에 넣는다.
  - 이 때, 큐에 넣는 순서는 중요하지 않다.

![image-20200828143943051](https://user-images.githubusercontent.com/58545240/91525736-23bbab80-e93d-11ea-9048-b90852d6cd2f.png)

- 다시 큐에서 노드를 하나 꺼내고(2번 노드)
- 2번 노드의 체크되지 않은 인접 노드들(4, 5번 노드)을 체크상태로 변경하고 큐에 넣는다.

![image-20200828143954255](https://user-images.githubusercontent.com/58545240/91525756-2c13e680-e93d-11ea-87d3-0eecfc835ad7.png)

- 이런 방법으로 큐가 비어있는 상태가 될때까지 반복한다.
- 최종적으로 노드를 방문한 순서는 1, 2, 3, 4, 5, 7, 8, 6 이 된다. 하지만, 이 방문 순서는 유일하지 않다. 큐에 인접노드를 삽입하는 순서에 따라 달라지기 때문이다.

### BFS pesudo code

- 그래프 G, 출발 노드 S

```java
00 BFS(G, s)
01   Q <- null;
02   Enqueue(Q, s);
03   while Q != null do
04     u <- Dequeue(Q);
05     for each v adjacent to u do
06       if v is unvisited then
07         mark v as visited;
08         Enqueue(Q, v);
```

### BFS와 최단경로

- BFS는 단순히 그래프의 모든 노드를 방문하는 것 이상의 추가적인 중요한 일을 할 수 있다. 최단 경로를 구하는 일이다.
- s에서 Li에 속한 노드까지 최댄 경로의 길이는 i이다.
  - 여기서 경로의 길이는 경로에 속한 엣지의 수를 의미한다.
- BFS를 하면서 각 노드에 대해서 최단 경로의 길이를 구할 수 있다.
- 입력
  - 방향 혹은 무방향 그래프 G = (V, E), 그리고 출발노드 s
- 출력
  - 모든 노드 v에 대해서
  - d[v] = s로 부터 v까지의 최단 경로의 길이(엣지의 개수)
  - π[V] = s로 부터 v까지의 최단경로상에서 v의 직전 노드(predecessor)
- Pseudo code
  - 02 - 04 : 모든 노드 u에 대해서 d[], π[]를 초기화
  - 05 - 06 : 스타트 노드의 d[], π[]를 설정
  - 11 : d[v] 가 -1인가를 체크하여 unvisited 체크를 구현
  - 12 - 13 : unvisited 노드에 대하여 d[v], π[v]를 저장
    - 최단경로 길이 d[v]는 u까지의 최단경로길이 d[u]를 지나오는 것이므로 d[u] + 1이 될 것이고,
    - v노드의 최단경로상에서 v의 직전노드는 u가 된다.
  - 14 : unchecked 노드만 큐에 들어갈 수 있으므로 어떤 노드도 큐에 두번 들어가지는 않는다.

```java
00 BFS(G, s)
01   Q <- null;
02   for each node u do
03     d[u] <- -1;
04     π[u] <- null;
05   d[s] <- 0;     //distance from s to s is 0
06   π[s] <- null;  //no predecessor of s
07   Enqueue(Q, s);
08   while Q != null do
09     u <- Dequeue(Q);
10     for each v adjacent to u do
11       if (d[v] == -1) then
12         d[v] <- d[u] + 1;    //distance to v
13         π[v] <- u;           //u is the predecessor of v
14         Enqueue(Q, v);
```

### 시간복잡도

- 02라인 for 의해 기본적으로 O(n)이다.
- 실제로는 08라인의 while문이 알고리즘의 시간복잡도를 결정한다.
- 기본적으로 while문이 한번 돌 때마다 큐에서 노드 하나씩 꺼내므로 while문은 최대 n번 돈다.
- 10라인의 for문은 u의 degree() 만큼 돈다. 리스트를 인접 행렬로 구현하느냐, 인접리스트로 구현하느냐에 따라 for문의 시간복잡도가 달라진다.
  - degree(v)는 어떤 한 노드 v에 실제로 인접한 노드의 수
- 그래프를 **인접 행렬로 구현할 경우** degree(v)를 찾으려면 O(n)이 든다. 따라서 인접행렬로 구현했을 떄의 while문의 시간복잡도는 O(n^2)이 된다.
- **인접 리스트로 구현할 경우**  전체 그래프에서 보면, for 문은 결국 모든 노드들의 degree() 만큼 돌 것이다. 인접리스트에서 그것은 2m이다.(무방향 그래프에서 총 엣지의 수) 시간복잡도는 O(m). 따라서, while문의 시간복잡도는 O(n + m)이 된다.
- 결과적으로 인접 리스트의 최악의경우 m이 n이 되므로 최악의 경우가 아닌 이상 인접 리스트로 구현하는 것이 좀 더 효율적이다.

### BFS로 구현한 d[]와 π[] 예시

![image-20200828144013107](https://user-images.githubusercontent.com/58545240/91525770-333af480-e93d-11ea-9e76-1cc6c00867d1.png)

### BFS 트리

- 각 노드 v와 π[v]를 연결하는 엣지들로 구성되는 트리
- BFS 트리에서 s에서 v까지의 경로는 s에서 v까지 가는 최단 경로
- 어떤 엣지도 동심원의 2개의 layer(L0에서 L2로 가지 않는다)를 건너가지 않는다.(동일 레이어의 노드를 연결하거나, 혹은 1개의 layer를 건너간다.)

![image-20200828144024810](https://user-images.githubusercontent.com/58545240/91525788-3b932f80-e93d-11ea-981b-c8c9e759cb31.png)

### 너비우선탐색: 최단 경로 출력하기

- 출발점 s에서 노드 v까지의 경로 출력하기
  - resursion으로 해결한다.
  - s에서 v까지 가는 최단 경로는 먼저 s에서 π[v]까지 가는 경로를 출력하고, v를 추가로 출력하면 된다.

```java
00 PRINT-PATH(G, s, v)
01   if v = s then
02    print s;
03  else if π[v] = null then    // 실제로 s에서 v까지 가는 경로가 없을 경우(최단경로도 없음)
04    print "no path from s to v exists";
05  else
06    PRINT-PATH(G, s, π[v]);
07    print v;
```

### 너비우선탐색(BFS) 정리

- 그래프가 connected 라면 모든 노드를 방문하게 된다. 하지만, 그래프가 **disconnected** 이거나 혹은 방향 그래프라면 BFS에 의해서 모든 노드가 방문되지 않을 수도 있다.
- disconnected 그래프의 모든 노드를 방문하려면 BFS를 반복하여 모든 노드 방문
  - 전체 노드중 unvisited 노드가 없을 때까지 BFS를 반복한다.

```java
BFS-ALL(G)
  while there exists unvisited node v
    BFS(G, V)
```

# **> DFS(Depth-First Search, 깊이우선탐색)**

---

- 이진트리의 순회 방법인 inorder, preorder, postorder 순회방법이 DFS의 이진트리 버전에 해당한다.
- lever order는 BFS의 이진트리 순회 버전이다.

## 깊이우선탐색(DFS)

- 출발점 s에서 시작한다.
- 현재 노드를 visited로 mark하고 인접한 노드들 중 unvisited 노드가 존재하면 그 노드로 간다.
- 2번을 계속 반복한다.
- 노드 8에 도달했을 때처럼 인접한 노드들중 invisited 노드가 존재하지 않는다면, unvisited인 이웃 노드가 존재하지 않는 동안 계속해서 직전 노드로 되돌아간다.
- 다시 2번을 계속 반복한다.
- 시작노드 s로 돌아오고 더 이상 갈 곳이 없으면 종료한다.

![image-20200828144256618](https://user-images.githubusercontent.com/58545240/91525796-41891080-e93d-11ea-935d-d054d8dde058.png)

- 다음과 같은 흐름으로 깊이우선순회가 이루어 진다.

![image-20200828144312828](https://user-images.githubusercontent.com/58545240/91525804-451c9780-e93d-11ea-93aa-9d4b21ebbcef.png)

### DFS, 깊이우선탐색

- 이진트리의 순회를 recursion으로 구현한 것처럼, 깊이우선탐색도 resursion으로 구현하는 것이 간명하다.
- 01 : 먼저 방문한 노드 v에 대해서 visited 체크를 하고
- 02 : v와 인접한 노드 x들에 대해서
- 03 : visited[x]가 No 이면,
- 04 : DFS(G, x)를 recursive하게 호출한다.
- 순회를 위해서 직전의 노드로 돌아가는 행동이 recursion으로 간명하게 구현된다.

```java
00 DFS(G, v)
01   visited[v] <- YES;
02   for each node x adjacent to v do
03     if visited[x] = No then
04       DFS(G, x);
```

- 그래프가 **disconnected 그래프 이거나 혹은 방향 그래프**라면 DFS에 의해서 모든 노드가 방문되지 않을 수도 있음
- DFS를 반복하여 모든 노드 방문
  - 모든 노드의 visited를 NO로 설정하고,
  - 해당 노드들을 출발노드로 하여 DFS를 호출, 연결되지 않은 그래프에 해당하는 노드는 visited가 NO로 유지되어서 DFS를 호출하게됨
- 시간복잡도
  - 첫번째 for문에 의해서 시간복잡도 O(n)은 피할 수 없고,
  - 두번째 for문에 의해서 v노드와 엣지로 이어진 노드가 visited인지를 체크한다. 따라서, 인접리스트로 표현했다면 시간복잡도는 엣지의 갯수에 비례하게 된다. O(m)
  - 최종적으로 O(n + m)의 시간복잡도를 갖는다.
  - 만약, 인접행렬로 표현했다면 인접노드의 여부를 알기위해서 전체 노드의 수 만큼 검색해야 하므로 O(n)이므로, O(n^2)의 시간복잡도를 갖는다.

```java
DFS-ALL(G)
  for each v in V
    visited[v] <- NO;
  for each v in V
    if (visited[v] = no) then
      DFS(G, v)
```



# **> 위상정렬(Topological Sort)**

---

> **어떤 일을 하는 순서를 찾는 알고리즘**
>
> => 즉, 방향 그래프에 존재하는 각 정점들의 선행 순서를 위배하지 않으면서 모든 정점을 나열하는 것

## 위상정렬의 특징

![image-20200911102426695](https://user-images.githubusercontent.com/58545240/92840886-9daf6280-f41c-11ea-99cd-f2b586831c2d.png)

- 하나의 방향 그래프에는 여러 위상 정렬이 가능하다.
- 위상 정렬의 과정에서 선택되는 정점의 순서를 위상 순서(Toplogical Order)라 한다.
- 위상 정렬의 과정에서 그래프에 남아 있는 정점 중에 진입 차수가 0인 정점이 없다면, 위상 정렬 알고리즘은 중단되고 이러한 그래프로 표현된 문제는 실행이 불가능한 문제가 된다.

=> 위상정렬이 가능하려면 ***DAG(Directed Acyclic Graph, 방향성이 있으며 사이클이 없는 그래프)***여야 한다.

1. 말 그대로 두 노드 `A`, `B` 사이에 A->B 같은 관계가 성립되어야 하며
2. A->B, B<-A 처럼 그래프들 사이에 사이클이 없어야 한다.

=> 위상정렬은 DFS를 사용하여 구현하거나 `indegree`배열을 사용하여 구현할 수 있다.(**indegree**가 가장 많이 쓰이고 간단하다)

- `List<List<Integer>> array` : 그래프의 관계를 표현하기 위한 2차원 인접 리스트
- `int[] indegree` : 해당 노드를 가리키는 간선 갯수를 담기 위한 배열
- `Queue<Integer> q` : `indegree` 값이 0이 된 노드들을 담기 위한 `Queue`
- `Queue<Integer> result` : `Queue`에서 꺼내져 결과로 출력하기 위해 담는 결과 `Queue`

## 위상정렬의 동작방식

![image-20200911104241195](https://user-images.githubusercontent.com/58545240/92840908-a4d67080-f41c-11ea-9f6e-861b6abf33da.png)

1. 진입차수가 0인 정점(즉, 들어오는 간선의 수가 0)을 선택
   - 진입차수가 0인 정점이 여러개 존재할 경우 어느 정점을 선택해도 무방하다.
   - 초기에 간선의 수가 0인 모든 정점을 큐에 삽입
2. 선택된 정점과 여기에 부속된 모든 간선을 삭제
   - 선택된 정점을 큐에서 삭제
   - 선택된 정점에 부속된 모든 간선에 대해 간선의 수를 감소
3. 위의 과정을 반복해서 모든 정점이 선택, 삭제되면 알고리즘 종료



- 위상 정렬은 정해진 결과 값이 없다. **중요한 점은 화살표가 가리키는 순서는 꼭 지켜져야 한다는 것!**

```bash
1 - 2 - 5 - 4 - 6
1 - 2 - 4 - 6
1 - 3 - 4 - 6
1 - 3 - 7
```

이 순서는 어떤 정렬 결과가 나오더라도 변하지 않을 것이다.

## 코드

```java
import java.util.*;
public class TopologicalSort{
    static int n;
    static int e;
    
    public static void main(String[] args){
        n = 7;	// 정점 갯수
        e = 9;	// 간선 갯수
        int[] indegree = new int[n+1];
        List<List<Integer>> array = new ArrayList<List<Integer>>();
        
        for(int i=0; i<n+1; i++){
            array.add(new ArrayList<Intger>());
        }
        
        // 간선목록 v1 -> v2
        int[] v1 = {1, 1, 2, 4, 3, 3, 5, 2, 5};
        int[] v2 = {2, 3, 5, 6, 4, 7, 6, 4, 4};
        
        /*
        1. v1 -> v2 인접리스트 생성
        2. v2를 가리키는 노드 갯수 indegree 증가
        */
        for(int i=0; i<e; i++){
            int c1 = v1[i];
            int c2 = v2[i];
            
            array.get(c1).add(c2);
            indegree[c2]++;
        }
        topologicalSort(indegree, array);
    }
    
    public static void topologicalSort(int[] indegree, List<List<Integer>> graph){
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> result = new LinkedList<Integer>();
        
        // 큐에 indegree가 0인 노드 담기
        for(int i=1; i<n; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        
        /*
        1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree를 1 감소
        2. 만약 indegree가 0이 된다면 큐에 넣기
        3. 큐가 빌때까지 반복
        */
        while(!q.isEmpty()){
            int node = q.poll();
            result.offer(node);
            for(Integer i : graph.get(node)){
                indegree[i]--;
                
                if(indegree[i] == 0){
                    q.offer(i);
                }
            }
        }
        System.out.println(result);
    }
}
```

## 위상정렬 관련 문제

1. 각각의 작업이 완료되어야만 끝나는 프로젝트

2. 선수 과목

   - 큐를 이용한 위상정렬

     [줄 세우기 - 백준 2252번](https://www.acmicpc.net/problem/2252)

   - 우선순위 큐를 이용한 위상정렬

     [문제집 - 백준 1766번](https://www.acmicpc.net/problem/1766)

   - 여러 위상순서 중 가장 짧게 걸리는 위상 정렬 방법 구하기

     [작업 - 백준 2056번](https://www.acmicpc.net/problem/2056)

     [게임개발 - 백준 1516번](https://www.acmicpc.net/problem/1516)

## 참고

https://bcp0109.tistory.com/entry/%EC%9C%84%EC%83%81%EC%A0%95%EB%A0%AC-Topological-Sort-Java?category=848939

https://gmlwjd9405.github.io/2018/08/27/algorithm-topological-sort.html



# **> 유니온 파인드(Union-Find)**

---

> 그래프 알고리즘으로 **합집합 찾기 알고리즘**이다.
>
> **상호 배타적 집합(Disjoint-set)**이라고도 한다.

*Disjoint Set이란 서로 중복되지 않는 부분집합들로 나눠진 원소들에 대한 정보를 저장하고 조작하는 자료구조이다.*

## 유니온파인드의 특징

**Union-Find**란 Disjoint Set을 표현할 때 사용하는 알고리즘이다.

- 집합을 구현하는 데는 비트 벡터, 배열, 연결 리스트를 이용할 수 있으나 그중 가장 효율적인 **트리 구조**를 이용하요 구현한다.
- **`make-set(x)`**
  - 초기화
  - x를 유일한 원소로 하는 새로운 집합을 만든다.
- **`union(x,y)`**
  - 합하기
  - x가 속한 집합과 y가 속한 집합을 합친다.
- **`find(x)`**
  - 찾기
  - x가 속한 집합의 대표값(루트 노드값)을 반환한다. 즉 x가 어떤 집합에 속해 있는지 찾는 연산



여러 노드가 존재 할 때 두 개의 노드를 선택해서, 현재 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘이다.

총 2가지의 연산으로 이루어져 있다.

- **Find** : x가 어떤 집합에 포함되어 있는지 찾는 연산
- **Union** : x와 y가 포함되어 있는 집합을 합치는 연산

## 유니온 파인드의 동작방식

![image-20200911110302450](https://user-images.githubusercontent.com/58545240/92844373-bde12080-f420-11ea-8fc9-3855c3cf3399.png)

위와 같이 모두 연결되지 않고 각자 자기 자신만을 집합의 원소로 가지고 있을 때, 모든 값이 자기 자신을 가리키도록 만든다.

**`i` : 노드번호, `P[i]`: 부모 노드 번호**를 의미하며, 즉 자기 자신이 어떤 부모에 포함되어 있는지를 의미한다.

정리하면 **`Parent[i] = i`**

![image-20200911110501896](https://user-images.githubusercontent.com/58545240/92844392-c3d70180-f420-11ea-8789-6ec6c622624c.png)

**`Union(1,2), Union(3,4)`**를 해주어 위와 같이 노드를 연결해준다.

![image-20200911110550615](https://user-images.githubusercontent.com/58545240/92844412-c8031f00-f420-11ea-9f4e-68a91a05871d.png)

위와 같이 표로 표현이 된다. 2번째 인덱스에 '1'이 들어가고, 4번인덱스에 '3'이 들어간다.

**이와 같이 부모를 합칠 때는 일반적으로 더 작은 값 쪽으로 합친다.** 이것을 **합칩(Union) 과정**이라고 한다.

![image-20200911110639947](https://user-images.githubusercontent.com/58545240/92844427-cc2f3c80-f420-11ea-903e-2c60e02518a4.png)

![image-20200911110704352](https://user-images.githubusercontent.com/58545240/92844438-d05b5a00-f420-11ea-8154-8b330530555e.png)

1,2,3이 연결될 때는 위와 같은 표로 표현이 된다.

1과 3은 부모가 다르기 때문에 1과 3이 연결되었는지 파악하기 힘들다. 따라서 **재귀함수**가 사용된다.

3인 부모인 2를 먼저 찾고, 2인 부모인 1을 찾아 결과적으로 3의 부모는 1이되는 것을 파악하는 것이다.

**Union**의 과정이 수행된 후에는 다음과 같은 표로 바뀌게 된다.

![image-20200911110828805](https://user-images.githubusercontent.com/58545240/92844456-d6e9d180-f420-11ea-8b72-6d6ca2dc68e9.png)

결국 1,2,3의 부모는 모두 1이기 때문에 이 세가지 노드는 모두 같은 그래프에 속한다는 것을 알 수 있다.

해당 경로를 바꿔주는 과정은 아래와 같은 그림으로 변하게 된다.

![image-20200911110902955](https://user-images.githubusercontent.com/58545240/92844470-db15ef00-f420-11ea-8c16-644d71962d64.png)

## 유니온파인드의 최적화

### -최악의 상황

![image-20200911112959528](https://user-images.githubusercontent.com/58545240/92856933-fdaf0480-f42e-11ea-9fe9-9b83690f09a3.png)

- **트리구조가 완전 비대칭인 경우**
- 연결 리스트 형태
- 트리의 높이가 최대가 된다
- 원소의 개수가 N일 때, 트리의 높이가 N-1이므로 union과 find(x)의 시간 복잡도가 모두 O(N)이 된다.
- 배열로 구현하는 것 보다 비효율적이다.

### - find 연산 최적화

![image-20200911113107274](https://user-images.githubusercontent.com/58545240/92856954-01db2200-f42f-11ea-9091-eaf36fca95ec.png)

- **경로 압축(Path Compression)**
- 시간 복잡도 : O(logN)

```java
// 초기화
int root[MAX_SIZE];
for(int i=0; i<MAX_SIZE; i++){
    root[i]= i;
}

// find(x) : 재귀 이용
int find(int x){
    if(root[x] == x)
        return x;
    else P
        // 경로 압축
        // find하면서 만난 모든 값의 부모 노드를 root로 만든다.
        return root[x] = find(root[x]);
}
```

### - union 연산 최적화

- **union-by-rank(union-by-height)**
- rank에 트리의 높이를 저장한다.
- 항상 높이가 더 낮은 트리를 높은 트리 밑에 넣는다.

```java
//초기화
int root[MAX_SIZE];
int rank[MAX_SIZE];	// 트리의 높이를 저장할 배열
for(int i=0; i<MAX_SIZE; i++){
    root[i] = i;
    rank[i] = 0;	// 트리의 높이 초기화
}

// find(x): 재귀이용
int find(int x){
    // 위와 동일
}

// union1(x, y) : union-by-rank 최적화
void union(int x, int y){
    int x = find(x);
    int y = find(y);
    
    // 두 값의 root가 같으면(이미 같은 트리) 합치지 않는다.
    if(x == y)
        return;
    
    // "union-by-rank 최적화"
    // 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다. 즉 높이가 더 높은 쪽을 rank로 삼음
    if(rank[x] < rank[y]){
        root[x] = y;	// x의 root를 y로 변경
    } else {
        root[y] = x;	// y의 root를 x로 변경
        
        if(rank[x] == rank[y])
            rank[x] ++;	// 만약 높이가 같다면 합친 후 (x의 높이 + 1)
    }
}
```

**두 원소가 속한 트리의 전체 노드의 수를 구하는 경우**

```java
// union2(x, y): 두원소가 속한 트리의 전체 노드의 수 구하기
int nodeCount[MAX_SIZE];
for(int i=0; i<MAX_SIZE; i++){
    nodeCount[i] = i;
}

int union2(int x, int y) {
    int x = find(x);
    int y = find(y);
    
    // 두 값의 root가 같지 않으면
    if(x != y){
        root[y] = x;	// y의 root를 x로 변경
        nodeCount[x] += nodeCount[y];	// x의 node 수에 y의 node 수를 더한다.
        nodeCount[y] = 1;	// x에 붙은 y의 node 수는 1로 초기화
    }
    return nodeCount[x];	// 가장 root의 node 수 반환
}
```



## 코드

```java
public class UnionFind{
    public static int[] parent = new int[1000001];
    
    public static int find(int x){
        if(x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }
    
    public static void union(int x, int y){
        int x = find(x);
        int y = find(y);
        //같은 부모를 가지고 있지 않을 때
        if(x != y){
            //y가 x보다 크다는 것을 가정하면
            parent[y] = x;
            // 더 작은 값으로 넣어 줄 때 다음과 같이 표현
            /*
            if(x < y) parent[y] = x;
            else parent[x] = y;
            */
        }
    }
    
    //같은 부모 노드를 가지는지 확인
    public static boolean isSameParent(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args){
        for(int i=1; i<=8; i++){
            parent[i] = i;
        }
        union(1,2);
        union(2,3);
        System.out.println("1과 3은 연결되어 있나요? -> "+isSameParent(1,3));
    }
}
```

## 유니온파인드 관련 문제

1. 전체 집합이 있을 때 구성원소들이 겹치지 않도록 **분할하는 데** 자주 사용된다.

   - **Kruskal MST 알고리즘**에서 새로 추가할 간선의 양끝 정점이 같은 집합에 속해 있는지(사이클 형성 여부 확인)에 대해 검사하는 경우

   - 초기에 {0}, {1}, {2}, ... {n}이 각각 n+1개의 집합을 이루고 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하는 경우

     [집합의 표현 - 백준 1717번](**[https://www.acmicpc.net/problem/](https://www.acmicpc.net/problem/1717)1717**)

   - 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 가입한 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하는 경우

     [친구 네트워크 - 백준 4195번](https://www.acmicpc.net/problem/4195)

그외

[바이러스 - 백준 2606번](https://www.acmicpc.net/problem/2606)

[섬 연결하기 - 프로그래머스 42861번](https://programmers.co.kr/learn/courses/30/lessons/42861)

## 참고

https://brenden.tistory.com/33

# **> 스패닝 트리(ST)**

---

> **그래프 내의 모든 정점을 포함하는 트리**

- **Spanning Tree = 신장 트리 = 스패닝 트리**
- Spanning Tree는 그래프의 최소 연결 부분 그래프이다.
  - 최소 연결 = 간선의 수가 가장 적다.
  - n개의 정점을 가지는 그래프의 최소 간선 수는 (n-1)개이고, (n-1)개의 간선으로 연결되어 있으면 필연적으로 트리 형태가 되고 이것이 바로 Spanning Tree가 된다.
- 즉, 그래프에서 일부 간선을 선택해서 만든 트리

## 스패닝 트리의 특징

![image-20200911140355155](https://user-images.githubusercontent.com/58545240/92866780-e2e28d00-f43a-11ea-90c1-646b51f53c81.png)

- DFS, BFS를 이용하여 그래프에서 신장 트리를 찾을 수 있다.
  - 탐색 도중에 사용된 간선만 모으면 만들 수 있다.
- 하나의 그래프에는 많은 신장 트리가 존재할 수 있다.
- Spanning Tree는 트리의 특수한 형태이므로 **모든 정점들이 연결**되어 있어야 하고 **사이클을 포함해서는 안된다.**
- 따라서 Spanning Tree는 그래프에 있는 **n개의 정점을 정확히 (n-1)개의 간선으로 연결**한다.

## 스패닝 트리의 사용 사례

*통신 네트워크 구축*

![image-20200911140530755](https://user-images.githubusercontent.com/58545240/92866800-e70eaa80-f43a-11ea-9a87-534613ba6c65.png)

- 예를 들어, 회사 내의 모든 전화기를 가장 적은 수의 케이블을 사용하여 연결하고자 하는 경우
- n개의 위치를 연결하는 통신 네트워크를 최소의 링크(간선)를 이용하여 구축하고자 하는 경우, 최소 링크의 수는 `(n-1)`개가 되고, 따라서 Spanning Tree가 가능해진다.

# **> 최소 신장 트리(MST)**

---

> **Minimum Spanning Tree**
>
> **Spanning Tree 중에서 사용된 간선들의 가중치 합이 최소인 트리**

- 각 간선의 가중치가 동일하지 않을 때 단순히 가장 적은 간선을 사용한다고 해서 최소 비용이 얻어지는 것은 아니다.
- MST는 간선에 가중치를 고려하여 최소 비용의 Spanning Tree를 선택하는 것이다.
- 즉, 네트워크(가중치를 간선에 할당한 그래프)에 있는 모든 정점들을 가장 적은 수의 간선과 비용으로 연결하는 것이다.

## MST의 특징

1. 간선의 가중치의 합이 최소여야 한다.
2. `n`개의 정점을 가지는 그래프에 대해 반드시 `(n-1)`개의 간선만을 사용해야 한다.
3. 사이클이 포함되어서는 안된다.

## MST의 사용사례

*통신망, 도로망, 유통망에서 길이, 구축비용, 전송시간 등을 최소로 구축하려는 경우*

- 도로건설
  - 도시들을 모두 연결하면서 도로의 길이가 최소가 되도록 하는 문제
- 전기회로
  - 단자들을 모두 연결하면서 전선의 길이가 가장 최소가 되도록 하는 문제
- 통신
  - 전화선의 길이가 최소가 되도록 전화 케이블 망을 구성하는 문제
- 배관
  - 파이프를 모두 연결하면서 파이프의 총 길이가 최소가 되도록 하는 문제

## MST의 구현방법

### 1. Kruskal MST 알고리즘

> **탐욕적인 방법(greedy method)**을 이용하여 네트워크(가중치를 간선에 할당한 그래프)의 모든 정점을 최소 비용으로 연결하는 최적해를 구하는 것.

- MST가

  1. 최소 비용의 간선으로 구성되고
  2. 사이클을 포함하지 않음의 조건에 근거하여

  - **각 단계에서 사이클을 이루지 않는 최소 비용 간선을 선택** 하는 것이다.

- 간선 선택을 기반으로 하는 알고리즘

- 이전단계에서 만들어진 신장트리와는 상관없이 무조건 최소 간선만을 선택

- *그래프 내에 적은 숫자의 간선만을 가지는 '희소 그래프'에서 사용하기 적합하다*



***과정***

1. 그래프의 간선들을 가중치의 오름차순으로 정렬
2. 정렬된 간선 리스트에서 순서대로 사이클을 형성하지 않는 간선을 선택
   1. 즉, 가장 낮은 가중치를 먼저 선택
   2. 사이클을 형성하는 간선을 제외
3. 해당 간선을 현재의 MST의 집합에 추가



***주의!***

- 다음 간선을 이미 선택된 간선들의 집합에 추가할 때 **사이클을 생성하는지를 체크해야한다.**
  - 새로운 간선이 이미 다른 경로에 의해 연결되어 있는 정점들을 연결할 때 사이클이 형성된다.
  - 즉, 추가할 새로운 간선의 양끝 정점이 같은 집합에 속해 있으면 사이클이 형성된다.
- 사이클 생성 여부를 확인하는 방법은
  - 추가하고자 하는 간선의 양끝 정점이 같은 집합에 속해있는지를 먼저 검사해야한다.( **union-find 알고리즘 이용!** )

### 2. Prim MST 알고리즘

> **시작 정점에서부터 출발하여 신장트리 집합을 단계적으로 확장 해나가는 방법**

- 정점 선택을 기반으로 하는 알고리즘
- 이전 단계에서 만들어진 신장 트리를 확장하는 방법
- *그래프 간선이 많이 존재하는 '밀집 그래프'에서 사용하기 적합하다*



***과정***

1. 시작 단계에서는 시작 정점만이 MST 집합에 포함된다.
2. 앞 단계에서 만들어진 MST집합에 인접한 정점들 중에서 최소 간선으로 연결된 정점을 선택하여 트리를 확장한다.
   1. 즉, 가장 낮은 가중치를 먼저 선택한다.
3. 위의 과정을 트리가 `(n-1)`개의 간선을 가질 때까지 반복한다.

## MST 관련 문제

- [최소 스패닝 트리 - 백준 1197번](https://www.acmicpc.net/problem/1197)
- [네트워크 연결 - 백준 1922번](https://www.acmicpc.net/problem/1922)