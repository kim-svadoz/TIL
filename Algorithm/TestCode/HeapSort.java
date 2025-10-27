import java.io.*;

public class HeapSort {

    public static void main(String[] args) throws IOException {
        int[] test = {6, 10, 4, 2, 5, 8, 9, 1, 8, 125, 91};
        sort(test);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }

    public static void sort(int[] a) {
        sort(a, a.length);
    }

    private static void sort(int[] a, int size) {
        if (size < 2) {
            return;
        }

        int parentIdx = getParent(size - 1);

        /* make max-heap */
        for (int i = parentIdx; i >= 0; i--) {
            heapify(a, i, size - 1);
        }

        
        for (int i = size - 1; i > 0; i--) {
            /**
             * 1. root node가 max인 상태이므로 0번째 index와 i번째 인덱스의 값을 바꾸고
             * 2. 다시 0 ~ (i - 1)까지의 sub tree에 대해 max heap을 구성하도록 heapify 한다.
             */
            swap(a, 0, i);
            heapify(a, 0, i - 1);
        }
    }

    /* parent node = (index - 1) / 2 : 변하지 않는 성질 */
    private static int getParent(int child) {
        return (child - 1) / 2;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void heapify(int[] a, int parentIdx, int lastIdx) {
        int leftChildIdx;
        int rightChildIdx;
        int largestIdx;

        /**
         * 현재 부모노드의 자식노드인덱스가 마지막 인덱스를 넘지 않을때까지 반복한다.
         * 
         * 만약, 마지막 부모 인덱스가 왼쪽 자식만 가질 수 있으므로
         * 왼쪽 자식노드 인덱스를 기준으로 범위를 체크한다.
         */
        while ((parentIdx * 2) + 1 <= lastIdx) {
            leftChildIdx = (parentIdx * 2) + 1;
            rightChildIdx = (parentIdx * 2) + 2;
            largestIdx = parentIdx;

            /**
             * left child node가 parent node보다 크다면
             * left가 root
             */
            if (a[leftChildIdx] > a[largestIdx]) {
                largestIdx = leftChildIdx;
            }

            /**
             * right child node가 parent node보다 크다면
             * right가 root
             * 
             * do range check
             */
            if (rightChildIdx <= lastIdx && a[rightChildIdx] > a[largestIdx]) {
                largestIdx = rightChildIdx;
            }

            /**
             * 교환이 발생한 경우에는
             * 교환이 된 자식노드와 부모노드를 교체한다.
             */
            if (largestIdx != parentIdx) {
                swap(a, parentIdx, largestIdx);
                parentIdx = largestIdx;
            } else {
                return;
            }
        }
    }
}
