# Algorithm Pseudo Code

> Algorith Pseudo Code Library

## ForEach

```java
Map<String, String> map = new HashMap<>();
map.put("korea", "korean");
map.put("usa", "english");
map.put("japan", "japanese");

map.forEach((key, value) -> System.out.println(key + " : " + value));

List<String> list = new ArrayList<>();
list.forEach(s -> System.out.println(list.indexOf(s) + " : " + s));
```

## 정렬

```java
///////////////////// MAP 정렬 방법 /////////////////////
HashMap<String, String> map = new HashMap<String, String>();
// 1
Iterator<String> it = map.keySet().iterator();
while(it.hasNext()){
    String key = it.next();
}
// 2
for(Map.Entry<String, String> elem : map.entrySet()) {
    String key = elem.getKey();
    String value = elem.getValue();
}
// 3
for(String key : map.keySet()){
    String key = key;
    String value = map.get(key);
}
// 4
map.forEach((key, value) -> System.out.println("key: "+key+", value: "+value));


///////////////////// LIST 정렬 방법 /////////////////////
ArrayList<Integer> list = new ArrayList<Integer>();

Iterator<Integer> it = list.iterator();
while(it.hasNext()){
    list.get(it.next());
}
```

## 최소공배수 / 공약수

```java
int gcd(int p, int q) {
    if (q == 0) return p;
    
    return gcd(q, p % q);
}

int lcm(int m, int n) {
    int bigger = 0;
    bigger = (m > n) ? m : n;
    while (true) {
        if ((bigger % m == 0) && (bigger % n ==0)) {
            return bigger;
        }
        bigger++;
    }
}
```

## Combination

```java
void comb(int idx, int cnt) {
    if (cnt == 0) {
        ...
            return ;
    }

    if (idx == k) {
        ...
            return;
    }

    for (int i = idx; i <= n; i++) {
        visited[i] = true;
        comb(i + 1, cnt - 1);
        visited[i] = false;
    }
}
```

## Dfs + dp

```java
int dfs(int start) {
    if (dp[start] != 0) {
        return dp[start];
    }
    dp[start] = 1;
    for () {
        ...
        dfs(start + 1);
        ...
    }
    return dp[start];
}

// BOJ 내리막길 - 해당 칸(최우측 최하단) 으로 들어 오는 가지 수
dfs(0, 0);

int dfs(int r, int c) {
    if (r == m - 1 && c == n - 1) {
        return 1;
    }
    
    if (dp[r][c] != -1) {
        return dp[r][c];
    }
    
    dp[r][c] = 0;
    
    for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if (OOB(nr, nc)) continue;
        
        // memoization
        if (map[r][c] <= map[nr][nc]) continue;
        
        dp[r][c] += dfs(nr, nc);
    }
    return dp[r][c];
}

// BOJ 욕심쟁이 판다 - 경로 별 최댓값
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        max = Math.max(dfs(i, j), max);
    }
}

int dfs(int r, int c) {
    if (dp[r][c] != 0) {
        return dp[r][c];
    }
    dp[r][c] = 1;
    for (int i  = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if (OOB(nr, nc)) continue;
        
        // memoization
        if (map[r][c] >= map[nr][nc]) continue;
        
        dp[r][c] = Math.max(dfs(nr, nc) + 1, dp[r][c]);
    }
    return dp[r][c];
}
```

## Two pointer

```java
void twoPointer() {
    int lo = 0, hi = 0; sum = 0, min = 0;
    while (true) {
        if (sum >= k) {
            sum -= arr[lo++];
        } else if (hi >= n) {
            break;
        } else {
            sum += arr[hi++];
        }
    }
}
```

## Binary search

```java
void binarySearch() {
    int left = 0, right = n;
    while (left <= right) {
        int mid = (left + right) / 2;

        if (arr[mid] > cond()) {
            left = mid + 1;
        } else {
            right = mid -1;
        }
    }
}
```

## Levenshtein

```java
void Solution() {
    dist = new long[n + 1][m + 1];
    long cnt = levenshtein(origin, pattern);
}
long levenshtein(String origin, String pattern) {
    for (int i = 1; i <= origin.length(); i++) {
        dist[i][0] = i;
    }
    for (int j = 1; j <= pattern.length(); j++) {
        dist[0][j] = j;
    }
    
    for (int j = 1; j <= pattern.length(); j++) {
        for (int i = 1; i <= origin.length(); i++) {
            if (origin.charAt(i - 1) == pattern.charAt(j - 1)) {
                dist[i][j] = dist[i - 1][j - 1];
            } else {
                dist[i][j] = Math.min(dist[i - 1][j - 1], Math.min(dist[i - 1][j], dist[i][j - 1])) + 1;
            }
        }
    }
    
    return dist[origin.length()][pattern.length()];
}
```

## LCS

```JAVA
void LCS() {
    char[] str1;
    char[] str2;
    int[][] dp = new int[len1 + 1][len2 + 1];
    
    for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    System.out.println(dp[len1][len2]);
    Stack<Character> stack = new Stack<>();
    while (len1 >= 1 && len2 >= 1) {
        if (dp[len1][len2] == dp[len1 - 1][len2]) {
            len2--;
        } else if (dp[len1][len2] == dp[len1][len2 - 1]) {
            len1 --;
        } else {
            stack.push(str1[len1 - 1]);
            len1--;
            len2--;
        }
    }
    while (!stack.isEmpty()) {
        sb.append(stack.pop());
    }
}
```

## LIS

```JAVA
void LIS() {
    int[] arr;
    int[] lis = new int[n];
    list[0] = arr[0];
    int idx = 1;
    int tmp = 0;
    for (int i = 1; i < n ; i++) {
        if (lis[idx - 1] < arr[i]) {
            lis[idx++] = arr[i]
        } else if (lis[0] > arr[i]) {
            lis[0] = arr[i];
        } else {
            tmp = Arrays.binarySearch(lis, 0, idx, arr[i]);
            list[tmp < 0 ? -tmp-1 : tmp] = arr[i];
        }
    }
    System.out.println(idx);

}
```

## 수열 부분합

```java
// 두 리스트의 성분 을 합하여 k가 되는 경우의 수
List<Integer> list1;
List<Integer> list2;
Collections.sort(list1);
Collections.sort(lsit2);

int result = 0;
int left = 0;
int right = list2.size() - 1;
while (left < list1.size() && right >= 0) {
    int leftVal = list1.get(left);
    int rightVal = list2.get(right);
    
    // k : 목표 값
    if (leftVal + rightVal == k) {
        int leftCnt = 0;
        int rightCnt = 0;
        
        while (left < list1.size() && list1.get(left) == leftVal) {
            leftCnt++;
            left++;
        }
        
        while (right >= 0 && list2.get(right) == rigtVal) {
            rightCnt++;
            right--;
        }
        
        result += leftCnt * rightCnt;
    }
}

for (int i = 0; i < list1.size(); i++) {
    if (list1.get(i) == k) {
        result++;
    }
}
for (int i = 0; i < list2.size(); i++) {
    if (list2.get(i) == k) {
        result++;
    }
}
System.out.println(result);
```



## Trie

```java
class TrieNode {
    Map<Character, TrieNode> childNodes = new HashMap<>();
    boolean lastChar;
}
class Trie {
    TrieNode rootNode;
    public Trie {
        rootNode = new TrieNode();
    }
    void insert(String str) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            thisNode = thisNode.childNodes.computeIfAbsent(c, key -> new TrieNode());
        }
        thisNode.lastChar = true;
    }
    void find(String str) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (thisNode.childNodes.get(c) != null) {
                thisNode = thisNode.childNodes.get(c);
                if (thisNode.lastChar) {
                    // 마지막 문자
                }
            }
        }
    }
    
}
```

## MST + Union-Find

```java
class Edge {
    int s, e, cost;
    public Edge(int s, int e, int cost) {
        this.s = s;
        this.e = e;
        this.cost = cost;
    }
}
void mst() {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    ...
    pq.add(new Edge(s, e, cost));
    ...
}

void union(int u, int v) {
    u = find(u);
    v = find(v);
    if (u != v) {
        if (rank[u] < rank[v]) {
            parent[u] = v;
        } else {
            parent[v] = u;
            
            if (rank[u] == rank[v]) {
                rank[u]++;
            }
        }
    }
}

int find(int x) {
    if (x == parent[x]) return x;
    
    return find(parent[x]);
}
```

## Topological Sort

```java
class Node {
    int e, int cost;
    public Node (int e, int cost) {
        this.e = e;
        this.cost = cost;
    }
}
void topologicalSort() {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    list.get(u).add(v);
    indegree[v]++;
    
    for (int i = 1; i <= n; i++) {
        if (indegree[i] == 0) {
            pq.add(i);
        }
    }
    while (!pq.isEmpty()) {
        int cur = pq.poll();
        for (Node next : list.get(cur)) {
            indegree[next]--;
            
            if (indegree[next] == 0) {
                pq.add(next);
            }
        }
    }
}
```

## SCC

```JAVA
void Solution() {
    root = new int[n + 1]; // i번째 노드의 조상
    scc = new int[n + 1]; // i번째 노드가 속한 SCC 그룹
    size = new int[n + 1]; // i번째 SCC그룹의 크기
    stack = new Stack<Integer>();
    indegree = new int[n + 1];
    
    graph.get(u).add(v);
    
    for (int i = 1; i <= n; i++) {
        if (root[i] == 0) {
            Tarjan(i);
        }
    }
}

void Tarjan(int start) {
    cnt++;
    root[start] = cnt;
    stack.push(start);
    
    int parent = cnt;
    for (int next : graph.get(start)) {
        if (root[next] == 0) {
            parent = Math.min(parent, Tarjan(next));
        } else if (scc[next] == 0) {
            parent = Math.min(parent, root[next]);
        }
    }
    
    if (parent == root[start]) {
        groupNum++;
        while (true) {
            int t = stack.pop();
            scc[t] = groupNum;
            size[groupNum]++;
            
            if (t == start) break;
        }
    }
    
    return parent;
}
```



## LCA

```JAVA
int lca(int a, int aDepth, int b, int bDepth) {
    if (aDepth > bDepth) {
        while (aDepth != bDepth) {
            aDepth--;
            a = parent[a];
        }
    } else if (aDepth < bDepth) {
        while (aDepth != bDepth) {
            bDepth --;
            b = parent[b];
        }
    }
    
    while (a != b) {
        a = parent[a];
        b = parent[b];
    }
    
    return a;
}
```

## Dijkstra

```java
class Node implements Comparable<Node> {
    int e, cost;
    public Node (int e, int cost) {
        this.e = e;
        this.cost = cost;
    }
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}
void Solution() {
    
    ...
    list[u].add(new Node(v, cost));
    
    dijkstra(0);
}

void dijkstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(start, 0));
    dist[start] = 0;
    
    while (!pq.isEmpty()) {
        Node curNode = pq.poll();
        int cur = curNode.e;
        
        if (!visit[cur]) {
            visit[cur] = true;
            
            for (Node nextNode : list[cur]) {
                int next = nextNode.e;
                int cost = nextNode.cost;
                if (visit[nextNode.e]) continue;
                if (dist[next] > dist[cur] + cost) {
                    dist[next] = dist[cur] + cost;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
    }
}
```

## Bellman Ford

```java
class Node {
    int e, cost;
    public Node (int e, int cost) {
        this.e = e;
        this.cost = cost;
    }
}

long[] dist = new long[n + 1];
Arrays.fill(dist, Integer.MAX_VALUE);
List<Node>[] list = new Arraylist[n + 1];

...
list[u].add(new Node(v, cost));
// 음의 싸이클
boolean minusCycle = false;
dist[1] = 0;
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
        for (Node node : list[j]) {
            if (dist[j] == INF) break;
            
            int next = node.e;
            int cost = node.cost;
            if (dist[next] > dist[j] + cost) {
                dist[next] = dist[j] + cost;
                
                // n번째 루프에 값이 갱신 된다면 음의 싸이클이 존재한다.
                if (i == n) minusCycle = true;
            }
        }
    }
}

```

## Floyd Warshall

```java
void floyd() {
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
            }
        }
    }
}
```

## KMP

```java
void Solution() {
    fail = new int[Pattern.length()];
    failFunc(Pattern);
    compare(Origin);
}

void failFunc(String Pattern) {
    int j = 0;
    for (int i = 1; i < P.length(); i++) {
        while (j > 0 && P.charAt(i) != P.charAt(j)) {
            j = fail[j - 1];
        }
        
        if (P.charAt(i) == P.charAt(j)) {
            fail[i] = ++j;
        }
    }
}

void compare(String Origin) {
    int j = 0;
    for (int i = 1 ; i < O.legnth(); i++) {
        while (j > 0 && O.charAt(i) != P.charAt(j)) {
            j = fail[j - 1];
        }
        
        if (O.charAt(i) == P.charAt(j)) {
            if (j == P.length() - 1) {
                sb.append(i - P.length() + 2).append(" ");
                j = fail[j];
                total++;
            } else {
                j++;
            }
        }
    }
}
```

## Bitmasking

### combination

```java
words = new int[n];
for () {
    for () {
        char c = str.charAt(j);
        int idx = c - 'a';
        // words[i]에 idx번째 비트를 킨 비트를 저장한다.
        words[i] |= 1 << idx;
    }
}

...
bit |= 1 << ('a' - 'a');
bit |= 1 << ('n' - 'a');
...
comb(0, 0, bit);
System.out.println(answer);


void comb(int index, int start, int bit) {
    if (index == k) {
        int ret = 0;
        for (int word : words) {
            // 문제에서 주어진 word와 조합하고 있는 비트의 합집합이 bit? -> 새로운 단어는 없다.
            if ((word | bit) == bit) {
                ret++;
            }
        }
        
        answer = Math.max(answer, ret);
        return;
    }
    
    for (int i = start; i < ; i++) {
        // i 번째 bit가 꺼져있으면
        if ((bit & (1<<i)) == 0) {
            // 켜본다.
            comb(index + 1, i + 1, bit | 1 << i);
        }
    }
}
```

### dp

```java
int bit = 0;
for (int i = 0; i < n; i++) {
    ...
        if (...) {
            // 비트 on
            bit |= (1 << i);
            cnt++;
        }
}
solve(cnt, bit);

void solve(int cnt, int bit) {
    if (cnt >= k) return 0;
    
    if (dp[cnt][bit] >= 0) return dp[cnt][bit];
    
    dp[cnt][bit] = Integer.MIN_VALUE;
    
    for (int i = 0; i < n; i++) {
        // i 비트가 켜져있을 때
        if ((bit & (1 << i)) != 0) {
            for (int j = 0; j < n; j++) {
                // j비트도 켜져있을 때
                if (bit & (1 << j) != 0) {
                    ...
                }
                dp[cnt][bit] = Math.min(dp[cnt][bit], solve(cnt + 1, bit | (1 << j) + cost[i][j]));
            }
        }
    }
}
```









































