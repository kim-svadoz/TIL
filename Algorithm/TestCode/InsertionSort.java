public class InsertionSort {
    public static int[] input = {1, 2, 6, 8, 23, 1, 7, 3};

    public static void main(String[] args) {
        insertionSort(input, input.length);
        for (int i : input) {
            System.out.print(i + " ");
        }
    }

    private static void insertionSort(int[] a, int len) {
        for (int i = 1; i < len; i++) {
            /* 두번째 원소부터 타겟 시작 */
            int target = a[i];

            int j = i - 1;

            /**
             * 타겟이 이전 원소보다 클때 까지 반복
             */
            while (j >= 0 && target < a[j]) {
                a[j + 1] = a[j]; // 원소를 한칸씩 뒤로
                j--;
            }

            /**
             * while에서 탈출하는 경우는 앞의 원소가 타겟보다 작다는 의미므로
             * 타겟 원소는 j번째 뒤에 삽입되야 한다.
             * 그러므로 타겟은 j + 1에 위치한다.
             */
            a[j + 1] = target;
        }
    }
}
