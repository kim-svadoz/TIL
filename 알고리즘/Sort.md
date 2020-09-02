

# Sort :fist_oncoming:

# **> 기본 정렬 알고리즘**

> selection, bubble, insertion sort

- **simple, slow**
  - Bubble sort (버블정렬)
  - Insertion sort (삽입정렬)
  - Selection sort (선택정렬)
- **fast**
  - Quick sort (퀵 정렬)
  - Merge sort (병합정렬)
  - Heap sort (힙정렬)
- **O(n)**
  - Redix sort

## Selection Sort

- 각 루프마다
  - 최대 원소를 찾는다
  - 최대 원소와 맨 오른쪽 원소를 교환한다
  - 맨 오른쪽 원소를 제외한다.
- 하나의 원소만 남을 때까지 위의 루프를 반복한다.

![image-20200902113150888](https://user-images.githubusercontent.com/58545240/91940992-644b6880-ed33-11ea-9129-6857a5c62820.png)

- **pseudo code**

```java
selectionSort(A[], n){
    for last <- downto 2{	// 1
        A[1, ... , last] 중 가장 큰 수 A[k]를 찾는다; // 2
        A[k] <-> A[last]; // A[k]와 A[last]의 값을 교환	// 3
    }
}
```

- 실행시간
  - 1의 for 루프는 `n-1`번 반복
  - 2에서 가장 큰 수를 찾기 위한 비교 횟수 : `n-1`, `n-2`, `...`, `2`, `1`
  - 3의 교환은 상수시간 작업
- 시간복잡도 T(n) = `(n-1) + (n-2) + ... + 2 + 1 = **O(n<sup>2</sup>)**
  - 최악, 최선, 평균 항상 `n(n-1)/2`번의 비교연산을 수행하게 되므로 O(n<sup>2</sup>)dlek.
- 구현

```java
public class Selection {
    private static int[] input = {5, 6, 2, 8, 7, 23, 4, 1};
    
    public static void main(String[] args){
        selectionSortMin(input, input.length);
        for(int a : input){
            System.out.print(a + " ");
        }
    }
    
    private static void selectionSortMin(int[] input, int length){
        int min;
        int tmp;
        for (int i=0; i<length; i++){
            min = 1;
            for(int j=1+1, j<length; j++){
                if(input[j] < input[min]) min = j;
            }
            tmp = input[i];
            input[i] = input[min];
            input[min]; = tmp;
        }
    }
    
    private static void selectionSortMax(int[] input, int length){
        int max;
        int tmp;
        for (int i=length-1; i>0; i--){
            max = i;
            for(int j=i-1; j>=0; j--){
                if(input[j] > input[max]) max = j;
            }
            tmp = input[i];
            input[i] = input[max];
            input[max] = tmp;
        }
    }
}
```

```bash
# 실행결과
1 2 3 4 5 6 7 8 23
```

## Bubble Sort

![image-20200902124517769](https://user-images.githubusercontent.com/58545240/91941002-6b727680-ed33-11ea-99b5-8ca8b2bfa4a7.png)

- 실행시간
  - `(n-1) + (n-2) + ... + 2 + 1 =` **O(n<sup>2</sup>)**
- pseudocode

```java
bubbbleSort(A[], n) {
    for last <- n downto 2 {	// 1
        for i <- to last-1{		// 2
            if(A[i] > A[i+1]) the A[i] <-> A[i+1]; // 3
        }
    }
}
```

- 수행 시간
  - 1의 for루프는 `n-1`번 반복
  - 2의 for루프는 각각 `n-1`, `n-2`, `...`, `2`, `1`번 반복
  - 3의 교환은 상수시간 작업
- `T(n) = (n-1) + (n-2) + ... + 2 + 1 =` **O(n<sup>2</sup>)**
  - 최악, 최선, 평균 항상 `n(n-1)/2`번의 비교연산을 수행하게 되므로 O(n<sup>2</sup>)이다.
- 구현

```java
public class Bubble{
    private static int[] input = {5, 6, 2, 8, 7, 23, 4, 1};
    
    public static void main(String[] args){
        bubbleSort(input, input.length);
        for(int a : input){
            System.out.println(a + " ");
        }
    }
    
    private static void bubbleSort(int[] input, int length) {
        inti tmp;
        for (int i=length-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                tmp = input[j];
                input[j] = input[j+1];
                input[j+1] = tmp;
            }
        }
    }
}
```

```bash
# 실행 결과
1 2 3 4 5 7 8 23
```

## Insertion Sort

- 맨 처음 인덱스에 있는 원소를 정렬되어 있는 상태라고 보고, 두 번째 인덱스에 있는 데이터를 이 정렬된 배열에 삽입하면서, 두 개의 데이터가 다시 정렬된 상태가 되도록 만드는 정렬  방법이다. 반복적으로!

![image-20200902131815837](https://user-images.githubusercontent.com/58545240/91941012-71685780-ed33-11ea-80ba-e5a653c5e7bc.png)

- insertion의 일반적인 one step

  - `k-1`까지 정렬된 배열에 k번째 인덱스를 추가하는데, `k`번째 인덱스가 위치해야할 적절한 위치에 끼워 넣어서,
  - 배열의 k번째 까지 정렬되도록 하는 흐름을 가진다.

- 어떻게 k번째 인덱스가 들어갈 위치를 찾는가?

  - 앞에서부터 비교하면서 찾는방법, 뒤에서부터 비교하면서 찾는 방법이 있다.
  - 얼핏 생가갛면 같은 방법인 것처럼 보이나, 다른점이 있다.
  - 앞에서부터 인덱스의 요소들을 비교하면서 자리를 찾는다면, `k`번째 인덱스보다 작은 값들을 일일히 검사해야한ㄷ. 이것은 뒤에서 부터 자리를 찾는 작업에서도 똑같이 이루어진다.
  - 하지만, 들어갈 위치를 찾았을 때, 배열이라는 자료구조의 특성상 해당 위치부터 `k-1`인덱스까지의 요소들을 한칸씩 `shift`시키는 작업이 필요핟.,
  - 결과적으로 앞에서부터 비교를 하면, 모든 데이터를 한번씩은 적어도 봐야한다.
  - 그러나, **뒤에서부터 비교한다면 비교와 동시에 `switch`하는 작업을 행해줌으로써 자동으로 `shift`작업을 수행**한다.
  - 해당 데이터가 자리를 찾는 순간 앞의 데이터들은 비교할 필요가 없다. 따라서, 뒤에서부터 비교해서 자리를 찾는 작업이 더 효율적인 작업이다.
  - 비교와 동시에 `switch`작업을 하기 위해 임시변수 `tmp`를 유지한다. 그리고 `k-1`부터 `0`번 인덱스까지 `compare`작업과 `shift`작업을 반복적으로 수행한다.

  ![image-20200902132223369](https://user-images.githubusercontent.com/58545240/91941021-762d0b80-ed33-11ea-9c5d-d60d4459a539.png)

- pseudocode

```java
insertionSort(A[], n) {
	for i <- 2 to n 		// 1
        A[1...i]의 적당한 자리에 A[i]를 삽입한다 // 2
}
```

- 수행 시간
  - 1의 for루프는 `n-1`번 반복
  - 2의 삽입은 최약의 경우 `i-1`번 비교
- 최악의 경우
  - `T(n) = (n-1) + (n+2) + ... + 2 + 1 =` **O(n<sup>2</sup>)**
- 최선의 경우
  - 즉, 배열이 정렬되어 있는 경우에는 **O(n)**의 시간복잡도를 가진다.
- 따라서, `selection sort`나 `bubble sort`와 비교했을 때, 최악의 경우에는 차이가 없지만 평균적으로는 대략 절반 정도의 정렬 시간을 필요로 한다.
- 구현

```java
public class Insertion {
	private static int[] input = {5, 6, 2, 8, 7, 23, 4, 1, 44};
    
    public static void main(String[] args){
        insertionSort(input, input.length);
        for (int a : input) {
            System.out.print(a + " ");
        }
    }
    
    private static void insertionSort(int[] input, int length) {
		int tmp;
        for (int i = 1; i < length; i++){
            for (int j = i; j > 0; j--) {
                if (input[j-1] > input[j]) {
                    tmp = input[j-1];
                    input[j-1] = input[j];
                    input[j] = tmp;
                }
            }
        }
    }
}
```

```bash
# 실행결과
1 2 4 5 6 7 8 23 44
```

# **> 분할정복법**

> Divide and Conquer

- `merge sort`와 `quick sort`는 분할 정복 알고리즘을 사용한다
- 기본적으로 recursion을 사용하여 문제를 해결하는 기법이다.
- 아래의 세가지 단계를 거쳐서 문제를 해결한다.

1. 분할
   - 해결하고자 하는 문제를 작은 크기의 동일한 문제들로 분할
   - 크기는 작은 사이즈의 문제지만, 문제 자체는 전체 문제와 동일한 문제들
2. 정복
   - 각각의 작은 문제를 순환적으로 해결
3. 합병
   - 작은 문제의 해를 합하여(merge) 원래 문제에 대한 해를 구합

## Merge Sort

- divide : 데이터가 저장된 배열을 절반으로 나눔
- recursively sort : 각각을 순환적으로 정렬
- merge : 정렬된 두 개의 배열을 합쳐 전체를 정렬
- 주어진 배열을 계속해서 반으로 나누다 보면 결국 길이가 1인 리스트로 쭉 나뉘어진다.
- 길이가 1인 리스트가 된 순간 그 각각을 정렬된 리스트로 본다.
- 이것을 한 단계씩 `merge`하면서 다시 정렬된 리스트를 만드는 방식으로 정렬이 이루어진다.

![image-20200902133229534](https://user-images.githubusercontent.com/58545240/91941039-804f0a00-ed33-11ea-8dc9-494a8acbd142.png)

- `merge sort`에서 가장 중요한 과정이 다음과 같이 `merge`하는 과정이다. 정렬된 두 배열을 다시 하나의 정렬된 배열로 만드는 과정이다.
  - 정렬 된 두 배열을 합치기 위해, 추가배열을 이용한다
  - 두개의 리스트에서 두 배열의 첫 번째 값중, 작은 값 하나가 추가배열의 가장 작은 값이 된다.

![image-20200902133333276](https://user-images.githubusercontent.com/58545240/91941050-8513be00-ed33-11ea-955a-becca74902c3.png)

### - Algorithm

- `recursive`한 호출을 하기 위해서 `recursion` 함수를 기술할 때는 ,매개변수를 **명시적**으로 지정하라

```java
mergeSort(A[], p, r0) {
    base case 정의;	// p >= r 인 경우
    if(p < r) then {
        q <- (p + q)/2;		// p, q의 중간 지점 계산
        mergeSort(A, p, q);		// 전반부 정렬
        mergeSort(A, q+1, r);	// 후반부 정렬
        merge(A, p, q, r);		// 합병
    }
}

merge(A[], p, q, r){
    정렬되어 있는 두 배열 A[p...q]와, A[q+1...r]을 합하여 정렬된 하나의 배열 A[p...r]을 만든다.
}
```

- 실제 mrege() 작성

```java
void merge(int[] data, int p, int q, int r) {
    int i = p;
    int j = q+1;
    int k = p;
    int[] tmp = new int[data.length];
    while (i <= q && j <= r) {
		if (data[i] <= data[j])
            tmp[k++] = data[i++];
        else
            tmp[k++] = data[j++0;]
    }
    while (i <= q)
        tmp[k++] = data[i++];
    while (j <= r)
        tmp[k++] = data[j++];
    for (int i=p; i<=r; i++) 
        data[i] = tmp[i]
}
```

### - 시간복잡도

- n개의 데이터를 정렬하는 시간을 `T(n)`이라고 했을 때, N을 반으로 나누어서 정렬하는 시간은 `T(n/2)`이다. 따라서, 반으로 나눈 배열을 `merge sort`하는 시간 `T(n/2)`를 두번 더하고
- 다시 이 정렬된 두 개의 배열을 `merge`하는 과정에서 두 개의 정렬된 배열의 원소를 한번 비교할 때마다, 정렬된 원소를 저장할 추가 배열에 하나씩 원소가 추가되므로 비교연산의 횟수는 n을 넘지 않는다. 총 원소의 수는 `n`개이다.
- 그리고 base case로, 정렬할 데이터가 1개라면 비교 연산의 횟수는 0이다.
- 따라서 아래와 같이 시간복잡도를 표현할 수 있다.
- T(n) =
  - if n = 1
    - 0
  - otherwise
    - T(n/2) + T(n/2) + n
  - => **O(nlogn)**

### - O(nlogn)

- 길이가 n인 배열을 둘로 쪼개서 다시 정렬된 배열로 병할할 때는 비교의 횟수가 n번을 넘지 않는다.
- 길이가 n/2인 배열을 정렬하는 데에는, n/4의 길이를 가지는 배열 두개를 정렬하므로, 이것의 비교연산 횟수는 n/2이다.
  - 전체로 보면 길이가 n/4인 배열을 두개씩 merge하여 정렬된 n/2배열을 두개 만드는 작업을 수생하는 데 드는 비교연산의 횟수는, `2 * (n/2) = n`이다.
- 아래로 내려갈수록 똑같이 비교연산의 횟수는 n이다.
- 길이가 n인 배열을 길이가 1인 각각의 리스트로 쪼개려면, 아래의 표에서 봤을 때 트리의 레벨은 `logN`이 된다.
- 따라서, merge sort에 필요한 비교연산의 횟수는 총 `n*logn`이다.
- O(nlogn)

![image-20200902134345652](https://user-images.githubusercontent.com/58545240/91941060-8ba23580-ed33-11ea-818e-35da33f27917.png)

## Quick Sort

- 정렬할 배열이 주어진다. 마지막 수를 기준(pivot)으로 삼는다.

![image-20200902134538707](https://user-images.githubusercontent.com/58545240/91941084-978df780-ed33-11ea-8e75-997d78625851.png)

- 기준(15)보다 작은 수는 기준의 왼쪽에 나머지는 기준의 오른쪽에 오도록 재배치(분할)한다.

![image-20200902134552235](https://user-images.githubusercontent.com/58545240/91941099-9d83d880-ed33-11ea-8ea5-7c84728e12ee.png)

- 기준의 왼쪽과 오른쪽을 각각 순환적으로 정렬한다(정렬 완료)

![image-20200902134605645](https://user-images.githubusercontent.com/58545240/91941111-a2488c80-ed33-11ea-9eca-440c93939865.png)

### - Algorithm

- 마찬가지로 `quicksort`도 `recursion`이므로 매개변수를 **명시화**한다.

```java
quickSort(A[], p, r) {
    base case;		// p >= r일 때, 정렬할 데이터가 0개 또는 1개이므로 할 일 없음
    if(p<r) then {
        q = partition(A, p, r);		// pivot의 위치
        quickSort(A, p, q-1);
        quickSort(a, q+1, r);
    }
}
```

```java
partition(A[], p, r) {
    배열 A[p...r]의 원소들을 A[r]을 기준으로 양쪽으로 재배치하고 A[r]이 자리한 위치를 return 한다.
}
```

### - Partition

![image-20200902134846536](https://user-images.githubusercontent.com/58545240/91941148-b3919900-ed33-11ea-8da5-208c5c4b1036.png)

- 순환적으로 pivot을 기준으로 정렬을 진행하다가, 마지막에 pivot과 큰 값들 중 첫번째 값을 바꾼다.
- 아래와 같은 방법으로 pivot보다 작은 값, 큰값들로 나눈다.
  - 새로운 값(A[j])를 봤더니, pivot보다 크다면 j를 1증가시켜서 큰 값들의 범위를 하나 늘린다.
  - 반대로, pivot보다 작다면, i를 하나 증가시키고 새로운값 A[j]과 교환하면, 여전히 맨 앞 부터 i까지는 pivot보다 작은 값들이 위치하게 된다. 
  - 다음으로 j를 1증가시키면 pivot보다 큰 값들의 범위도 유지가 된다.

```java
if A[j] >= x
  j <- j+1;
else
  i <- i+1;
  exchange A[i] and A[j];
  j <- j+1;
```

- 예를 들면, 아래와 같은 흐름으로 i와 j가 움직이게 된다.
  - 시작인덱스 p, 끝인덱스 r
  - 맨 처음에 pivot보다 작다고 판정된 값은 아무것도 없으므로, i는 p-1로 시작한다. j는 p로 시작한다.
    - pivot인 15보다 31이 크므로 j를 증가시킨다.
    - 다음 값인 8은 15보다 작으므로 i를 1증가시키고 i와 j를 교환한 뒤, j를 1증가 시킨다.
    - 그 다음 값은 48은 15보다 크므로 j를 1증가시킨다.
    - ...

![image-20200902134952393](https://user-images.githubusercontent.com/58545240/91941160-b7bdb680-ed33-11ea-9898-303fba05bd37.png)

![image-20200902135010623](https://user-images.githubusercontent.com/58545240/91941170-bbe9d400-ed33-11ea-9640-12081bda2efb.png)

- 마지막으로 j와 r이 같아지면, pivot값과 pivot보다 큰 값들중 첫번째 값인 73을 교환하고, 
- 결과적으로 pivot보다 작은 값들의 집합, pivot, pivot보다 큰 값들의 집합으로 partition이 형성된다. 그리고 pivot의 index를 리턴해주면 된다.
- 이것을 **psedocode**로 표현하면 다음과 같다.
  - 시작인덱스 = p
  - 마지막인덱스 = r
  - pivot = x
  - i = p-1
  - j = p
  - parition에서는 n-1번의 비교연산을 가지므로, O(n)의 시간이 걸린다.

```java
partition(A, p, r) {
  x <- A[r];
  i <- p-1;
  for j <- p to r-1
    if A[j] <= x then
      i <- i+1;
      exchange A[i+1] and A[r];
  exchange A[i+1] and A[r];
  return i+1;
}
```

### - 최악의 경우

- 항상 한 쪽은 0개, 다른 쪽은 n-1개로 분할되는 경우
  - n(n-1)/2가 되므로, 최악의 경우 매우 비효율적인 알고리즘이 된다.

![image-20200902135042106](https://user-images.githubusercontent.com/58545240/91941183-bee4c480-ed33-11ea-80ff-124616603e96.png)

- 이미 정렬된 입력 데이터 (마지막 원소를 피봇으로 선택하는 경우)를 Quick Sort하는 경우가 위의 경우이다. 항상 한 쪽은 0개, 다른쪽은 n-1개로 분할된다.
  - 이미 정렬된 배열에서 마지막 원소를 피봇으로 봤을때, 나머지 원소들은 다 피봇보다 작은 값이 된다.
  - 그  다음 1 ~ n-1 번째 까지의 원소에서도 마지막 원소인 피봇보다 나머지 원소들이 다 작은 값이다.

### - 최선의 경우

- 항상 절반으로 분할 되는 경우
  - 이 경우에는 merge sort와 같은 시간복잡도를 갖는다.
  - T(n) = 2T(n/2) + Θ(n)
  - = Θ(nlogn)

### - 극단적인 경우가 아니라면

- 실제 사용을 해보면 다른 sorting알고리즘 보다 빠르기 떄문에, Quick Sort라는 이름이 붙여졌다. 그러나 최악의 경우 Θ(n²)의 시간복잡도를 가지므로, mergesort의 O(nlogn)보다 훨씬 나쁘다. 사실 최악의 경우와 최선의 경우가 발생할 확률은 드물다.
- 아래의 그림을 통해 quick sort가 빠르다는 것에 대한 설명을 보충 한다.
  - 항상 한쪽이 적어도 1/9 이상이 되도록 분할된다면? (가정이다.)
  - 항상 1:9로 분할 되면 왼쪽 트리보다 오른쪽 트리가 훨씬더 길게 그려질 것이다.
  - 가장 오른쪽의 트리를 보면 1이 될 때까지 트리가 그려지므로
  - (9/10)<sup>k</sup> *  n = 1 을 계산하면 k = log<sub>10/9</sub>n이 된다.
  - 따라서, 층의 최대 갯수는 Θ(logn)이다.
  - 추가적으로, 한 레벨씩 아래로 내려올때매다 n번의 비교연산을 하게 되므로, 결국 quick sort의 시간복잡도는 Θ(nlogn)이 된다.
- 이 증명으로 Quick Sort 최악의 경우 시간복잡도가 Θ(n²)임에도 불구하고, 실제로는 상당히 빠른 것에 대한 직관적인 설명이 될 수 있다. 분할이 극단적으로만 일어나지 않는다면 충분히 빠른 정렬알고리즘이다.

![image-20200902135117806](https://user-images.githubusercontent.com/58545240/91941216-d02dd100-ed33-11ea-8a35-48d243204ce3.png)

### - 평균시간복잡도

- "평균" 혹은 "기대값"이란?

  - 어떤 사건이 일어날 확률 * 그 사건이 일어났을 때의 시간
  - quick sort 알고리즘에 n개의 데이터가 들어왔을때, 평균시간복잡도를 A(n)이라고 했을때,
  - 크기가 n인 모든 가능한 입력 I에 대해서 p(I)T(I)이다.

  ![image-20200902135150574](https://user-images.githubusercontent.com/58545240/91941248-dfad1a00-ed33-11ea-991e-4eeb157e75da.png)

- 그러나, p(I)를 모른다.

- p(I)에 관한 적절한 가정을 한 후 분석한다.

- 예: 모든 입력 인스턴스가 동일한 확률을 가진다면

  - 1 ~ n개의 정수가 입력으로 들어온다면, 1 ~ n개의 정수를 섞어서 만들 수 있는 순열의 수는 n!개이다. 따라서, n!개의 순열이 각각 입력으로 들어올 경우의 수는 동일하다.  
  - p(I) = 1/n!

- 1 ~ n 중 맨 마지막 원소를 pivot으로 봤을 때, 이 pivot이 가장 작은 값일 경우(rank of pivot이 1인 경우)부터 가장 큰 값일 경우(rank of pivot이 n인 경우) 총 n개의 경우의 수가 있다.

  - 그러면, 그런 경우가 발생할 probability는 1/n으로 모두 같다.
  - 만약 rank of pivot이 3이라서, result of partition이 2:n-3으로 나누어 졌을 때,
  - 추가적으로 드는 running time은 A(2) + A(n-3)만큼 이다.
  - 결국 평균 시간 복잡도는 n개의 경우의 수에 대해 running time 곱하기 확률(1/n)을 해주면 된다.
  - 아래 그림에서 시그마 앞의 상수는 2/n 이다.(n/2로 표기오류된 부분) A(0) ~ A(n-1)까지 총 두번.
  - 추가적으로 분할을 하는데 걸리는 시간은 항상 동일하게 Θ(n)이다.
  - 아래의 순환식을 풀어보면 quick sort의 평균 시간복잡도는 Θ(nlogn)이 된다.

- 따라서, 결론적으로 평균시간복잡도까지 구해본 결과, 극단적인 최악의 경우가 아니라면, 실제로 빠른 정렬 알고리즘이다라는 것에 대한 증명을 할 수 있다.

  ![image-20200902135211620](https://user-images.githubusercontent.com/58545240/91941262-e5a2fb00-ed33-11ea-9ae7-58d0096075bb.png)

### - Pivot의 선택

- 첫번째 값이나 마지막 값을 피봇으로 선택
  - 이미 정렬된 데이터 혹은 거꾸로 정렬된 데이터가 최악의 경우가 된다.
  - 현실의 데이터는 랜덤하지 않으므로 (거꾸로) 정렬된 데이터가 입력으로 들어올 가능성은 매우 높다.(사전에 다른 소프트웨어에 의해 정렬된 데이터가 넘어오는 경우)
  - 따라서 좋은 방법이라고 할 수 없다.
- "Median of Three"
  - 첫번째 값과 마지막 값, 그리고 가운데 값 중에서 중간값(median)을 피봇으로 선택
  - 최악의 경우 시간 복잡도가 달라지지는 않음
- Randomized Quicksort
  - 피봇을 랜덤하게 선택
  - no worst case instance, but worst case execution
    - 다른 방법의 경우, 어떤 입력이 최악의 경우이다 라는 것이 정해지지만, 피봇을 랜덤하게 선택하면 어떤 데이터가 들어오더라도 랜덤값에 의해 결정 되므로. 최악의 실행이 존재한다.
  - 평균 시간복잡도 O(NlogN)

# **> 힙 정렬**

---

## Heap Sort

- 최악의 경우 시간복잡도 O(nlogn)
- Sorts in place - 추가 배열 불필요
  - `merge sort`도 최악의 경우 O(nlogn)이었지만, 추가 배열이 필요햇음
- **이진 힙(binary heap) 자료 구조**를 사용
- O(nlogn)의 시간복잡도를 가지면서 `merge sort`처럼 추가적인 배열이 필요하지 않기 때문에 좋은 정렬 알고리즘 중 하나이다.

## Heap의 정의

- Heap은
  - **완전 이진 트리(complete binary tree)**이면서
  - **Heap property**를 만족해야한다.

![image-20200902135538002](https://user-images.githubusercontent.com/58545240/91941306-fd7a7f00-ed33-11ea-992f-a82283544b1e.png)

- 동일한 데이터를 가진 서로 다른 힙이 존재할 수 있다. 즉, 힙은 유일하지 않다.(같은 원소들을 가지는데 다른 위치에 가진다.)
- 힙은 일차원 배열로 표현가능하다. **`A[1...n]1**
  - 루트 노드 : `A[1]`
  - `A[i]`의 부모 : `A[i/2]`
  - `A[i]`의 왼쪽 자식 = `A[2i]`
  - `A[i]`의 오른쪽 자식 = `A[2i+1]`

### - Full vs Complete Binary Trees

![image-20200902135727765](https://user-images.githubusercontent.com/58545240/91941319-023f3300-ed34-11ea-9c60-7b0f1e750d35.png)

## 기본 연산 : Max-Heapify

- 전체를 힙으로 만들어라
  - 왼쪽 자식 트리와 오른쪽 자식 트리가 모두 **heap property**를 만족하는데, `root`만이 조건을 만족하지 않을 때, 이 트리를 힙으로 만드는 연산을 **heapify**라고 한다.

![image-20200902140300184](https://user-images.githubusercontent.com/58545240/91941351-0cf9c800-ed34-11ea-8e14-9722819b27cc.png)

- **max-heapify**연산은 아래와 같은 상황전개가 이루어진다.
  - 두 자시곤드 중 큰 값을 선택하여 교환한다.
  - 4와 16을 교환하고 나면, 오른쪽 트리가 `heap property`를 만족하는지 고민할 필요가 없다. 16과 15를 비교하여 큰 노드과 교환했기 때문에, 오른쪽 트리는 조건을 만족한다.
  - 4와 16을 교환하고 나면, 4를 루트노드로 봤을 때, 그 아래의 트리들은 같은 상황을 맞게 된다.
  - 왼쪽, 오른쪽 자식 힙이 property를 만족하는데, 루트노드인 4만 조건을 만족하지 않는 상황이다.
  - 같은 방법으로 두 자식중에 큰 값인 8과 루트인 4를 교환한다.
  - 그러고나면 오른쪽 힙인 7은 `heap property`를 만족하게 되므로 신경쓸 필요가 없으며,
  - 이러한 방식으로 루트노드인 4가 더이상 비교할 자식이 없거나, 두 자식들이 루트 노드보다 작다면(루트 노드가 들어갈 자리를 찾았다면) 종료한다.

![image-20200902140535201](https://user-images.githubusercontent.com/58545240/91941360-11be7c00-ed34-11ea-96dd-258ba4ee4de3.png)

### - Max-Heapify : recursion

- heapify는 본질적으로 `recursive`한 특성을 가지고 있다.
  - 교환이 일어나고 나면, 반대쪽 힙은 생각하지 않고 반복적으로 교환이 일어난 쪽의 자식 힙만 생각하면 된다.
- root 노드에 대한 heapify는 maxHeapify(1)을 호출하면 되고, 루트노드는 i
- base case는 i의 자식이 없는 경우
- i는 heapify의 대상노드 즉, 시작노드
- k는 i의 자식노드 중 큰쪽

```java
maxHeapify(A, i) {
    if there is no child of A[i]
        return;
    k <- index of the biggest child of i;
    if A[i] >= A[k]
        return;
    exchange A[i] and A[k];
    maxHeapify(A, k);
}
```

### - Max-Heapify : iterateive

- i=k;

```java
maxHeapify(A, i) {
    while A[i] has a child do
        k<- index of the biggest child of i;
        if A[i] >= A[k]
            return;
        exchange A[i] and A[k];
        i=k;
    end.
}
```

## Heapify연산의 시간복잡도

- 두 자식들 중 더 큰쪽을 찾아서 `exchange`하는 연산을 하면, Heap의 Tree에서 한 레벨 내려온다.
- 그러므로, Heapify 알고리즘은 어떠한 경우에도 트리의 높이보다 더 많은 시간이 필요하지 않다.
- 따라서, 트리의 높이를 h라고 하면, 시간복잡도는 **O(h)**가 된다.
- 여기서 h를 구해보자.
- heap은 complete binary tree이기 때문에 노드의 수를 n이라고 했을때 h는 O(logn)이 된다.
- 따라서, **O(logn)**이며 n은 노드의 갯수이다.

## 정렬할 배열을 힙으로 만들기

- **heap**과 **heapify**연산을 이용해 정렬된 배열을 만드는 알고리즘에 대해 알아본다.
  - 시간복잡도 : **O(n)**
  - legnth[A] : 정렬할 데이터의 개수
  - for문을 length/2부터 시작하는 이유는 leaf노드가 아닌 첫번째 노드 즉, 리프노드의 부모노드부터 heapify연산의 가능 여부를 판단하기 때문이다.(아래 설명 참조)
  - Max-Heapify를 실행한다.
  - 완전 이진 트리의 형태(노드의 갯수가 n개)를 가지는 힙의 heapify연산이 O(logn)이다. 이 연산을, n/2번 수행하므로, 시간복잡도는 O(nlogn)으로 볼 수 있는데,
  - 이 경우는 일반적인 경우보다 과도하게 많이 측정한 시간이 된다. 왜냐면, 항상 루트노드에 대해서 heapify를 하는 것이 아니라, 마지막 leaf노드의 부모에서부터 heapify를 수행하므로 첫번째 heapify의 경우 노드의 갯수가 2개 또는 3개이므로
  - 좀 더 정확한 분석을 하면 정렬할 배열을 힙으로 만드는 데에는 O(n)이 된다. 
  - Heap sort에서는 실제로 힙을 정렬하는 과정에서 **O(nlogn)**의 시간복잡도를 갖기 때문에, 힙을 만드는 과정의 시간복잡도가 O(n)이던, O(nlogn)이던 전체 힙 정렬의 시간 복잡도는 **O(nlogn)**이 된다. 따라서 힙을 만드는 데 필용한 시간복잡도에 주목하지 않아도 된다!

```java
BUILD-MAX-HEAP(A)
 1. heap-size[A] <- length[A]
 2. for i <- length[A]/2 downto 1
 3.  do MAX-HEAPIFY(A, i)
```

- 먼저 주어진 1차원 배열을 complete binary tree로 해석한다. 실제로 트리를 만든다는 것이 아니라, 개념적으로 tree로 생각한다는 의미이다.
- 다음으로 complete binary tree를 heap으로 바꾼다.
  - Level order의 역순으로 노드들을 고려했을 때, leaf노드가 아닌 첫번째 노드(16)부터, 그 노드를 루트 노드로 하는 sub tree에 대해서 heapify연산을 할 수 있는 조건(양쪽 서브트리가 모두 heap인가)을 확인한다.
  - 다음 순서로 2에 대해 양쪽 sub tree에 대해 양쪽 섭즈트리가 모두 heap인가를 확인한다. 싱글노드이므로 힙이다. 따라서 2는 heapify 연산을 하기 위한 조건이 된다. 따라서, heapify연산을 수행하면 2와 14가 exchange된다.
  - 같은 방식으로 level order의 역순으로 올라가면서, heapify연산을 수행한다.
  - 결과적으로 f 단계와 같이 max heap을 얻을 수 있다.
  - 이것을 pseudo code로 작성하면 위의 코드와 같이 단순하게 작성할 수 있다.

![image-20200902141633486](https://user-images.githubusercontent.com/58545240/91941375-171bc680-ed34-11ea-844f-b4c9cea5293d.png)

## 실제 입력 배열을 힙으로 만드는 과정

![image-20200902141700573](https://user-images.githubusercontent.com/58545240/91941383-1b47e400-ed34-11ea-9a64-2bf341840ed7.png)

![image-20200902141707011](https://user-images.githubusercontent.com/58545240/91941391-1edb6b00-ed34-11ea-9133-900cda530de7.png)

![image-20200902141750213](https://user-images.githubusercontent.com/58545240/91941409-24d14c00-ed34-11ea-8331-f179d898bc71.png)

## Heap Sort

- 주어진 데이터로 힙을 만든다.
- 힙에서 최댓값(루트)을 가장 마지막 값과 바꾼다.
- 힙의 크기가 1 줄어든 것으로 간주한다. 즉, 가장 마지막 값은 힙의 일부가 아닌 것으로 간주한다.
- 루트 노드에 대해서 `HEAPIFY(1)`을 실행한다.
- 2~4번을 반복한다.
- 마지막 노드와 루트노드를 바꾸고 힙의 크기를 1 줄이면, 루트노드를 제외한 모든 곳에서 heap property를 만족하므로 `HEAPIFY(1)`을 실행해주면 된다.

### - pseudo code

- 먼저 배열 A를 max Heap으로 만든다. O(n)의 시간이 든다.
- Heap size가 2가 될 때까지 반복하고
- 루트노드와 마지막노드를 교환하고, 힙의 사이즈를 1 줄인다.
- MAX-HEAPIFY를 호출한다.

```java
HEAPSORT(A)
1. BUILD-MAX-HEAP(A)				: O(n)
2. for i<- heap_size downto 2 do	 : n-1 times
3.  	exchange A[1] <-> A[i]	 	: O(1)
4.  	heap_size <- heap_size-1	: O(1)
5.		MAX-HEAPIFY(A, 1)			: O(logn)
```



# **> 우선순위 큐**

---

> Priority queue
>
> 힙의 응용 : 우선순위 큐

- 최대 웅선순위 큐(maximum priority queue)는 다음의 두 가지 연산을 지원하는 자료구조이다.
  - INSERT(x) : 새로운 원소 x를 삽입
  - EXTRACT_MAX() : 최대값을 삭제하고 반환
- 최소 우선순위 큐(minimum priority queu)는 EXTRACT-MAX 대신 EXTRACT-MIN을 지원하는 자료구조
- MAX HEAP을 이용하여 최대 우선순위 큐를 구현

## INSERT

- max heap의 형태로 원소들이 저장되어 있고, 15가 insert되는 경우 아래와 같은 과정을 거친다.

- complete binary tree를 만족하면서, max heap property를 만족하도록 만들어 준다.

  ![image-20200902155435964](https://user-images.githubusercontent.com/58545240/91942084-47b03000-ed35-11ea-9652-b30d30ebbdef.png)

- **pseudo code**
  - Heap의 size를 하나 늘리고, key값을 배열의 마지막 노드에 삽입
  - i는 처리할 문제 노드
  - while문 -> 루트 노드가 아니고(i > 1), 부모노드의 값보다 문제 노드의 값이 더 크면, 서로 교환하고, 문제노드는 PARENT가 된다.
  - 시간복잡도 : O(h), h는 트리의 높이 이므로 시간복잡도는 O(logn). 문제 노드가 비교연산을 거칠 때마다, 위로 한 레벨 올라가거나 또는 루트노드에 도달하면 종료하므로 트리의 높이에 비례한다. Complete binart tree이므로 트리의 노드를 n개라고 했을 때, tree의 높이는 logn이다.

```java
MAX-HEAP-INSERT(A, key) {
    heap_size = heap_size + 1;
    A[heap_size] = key;
    i = heap_size;
    while (i > 1 and A[PARENT(i)] < A[i]) {
        exchange A[i] and A[PARENT(i)];
        i = PARENT(i);
    }
}
```

## EXTRACT_MAX()

- 최대값을 힙으로 부터 삭제하고, 반환해주는 메소드
- max heap에서 최대값은 항상 root에 존재한다.
- 따라서, heap으로 부터 root에 있는 값을 삭제하고 반환한다.
- Complete binary tree에서 노드 하나를 삭제하고, 그것이 다시 complete binary tree를 만족하게 하려면, 마지막 노드를 삭제하는 방법밖에 없다. 그러나 마지막 노드의 데이터를 삭제하면 안된다.
- 결과적으로,
- root노드의 값을 삭제하고 반환한 뒤, 마지막 노드의 값인 6을 root노드로 옮긴다.
- 다음으로 heap property를 만족하지 않는 힙에 대해 다시 max heap을 만드는 Heapify()연산을 수행한다. root노드 부터.
- heapify() 시간복잡도는 **O(logn)**

![image-20200902155729767](https://user-images.githubusercontent.com/58545240/91942098-4d0d7a80-ed35-11ea-8330-2d44f207d744.png)

- **pseudo code**
  - 1, 2 : heap size 예외처리
  - 3 : 최대값을 max에 저장 후 마지막에 return
  - 4 : 배열의 맨 마지막 노드를 루트노드로 카피
  - 5 : 힙의 사이즈를 1 줄이면, 마지막 노드를 제거하는 것과 같다.
  - 6 : 루트노드에 대해서 max heapify를 수행
  - 7 : 저장했던 최대 값을 리턴한다.
  - 시간복잡도는 **O(logn)**

```java
HEAP-EXTRACT-MAX(A)
1  if heap-size[A] < 1
2    then error "heap underflow"
3  max <- A[1]
4  A[1] <- A[heap-size[A]]
5  heap-size[A] <- heap-size[A] - 1
6  MAX-HEAPIFY(A, 1)
7  return max
```

# **> Locality의 관점에서 Quick sort가 Merge sort보다 빠른이유**

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