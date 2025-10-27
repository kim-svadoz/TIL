public class MergeSort {
    private static int[] input = {5, 6, 2, 1, 8, 24, 5, 3};
    private static int[] sorted;

    public static void main(String[] args) {
        sorted = new int[input.length];
        mergeSort(input, 0, input.length - 1);
        for (int i : input) {
            System.out.print(i+" ");
        }
    }
    private static void mergeSort(int[] a, int left, int right) {
        /**
         * 1 - 2 - 4 - 8 - ...
         * 의 형태로 1부터 서브리스트를 나누는 기준은 2배씩 늘어난다.
         */
        for (int size = 1; size <= right; size += size) {

            /**
             * 두개의 sublist를 순서대로 병합한다.
             * 예를들어 현재 sublist의 크기가 1(size = 1)일 때
             * left sublist(low ~ mid)와 right sublist(mid + 1 ~ high)를 생각해보면
             * left sublist는 low = mid = 0이고
             * right sublist는 mid + 1 부터 low + (2 * size) - 1 = 1 이 된다.
             * 
             * 이 떄 hi가 배열의 끝을 넘어갈 수 있으므로 right와 둘중 작은 값이 병합되도록 해야 한다.
             */
            for (int l = 0; l <= right - size; l += (size * 2)) {
                int lo = l;
                int mid = l + size - 1;
                int hi = Math.min(right, l + (size * 2) - 1);
                merge(a, lo, mid, hi); // merge
            }
        }
    }

    /**
     * 
     * @param a     정렬할 배열
     * @param left  배열의 시작점
     * @param mid   배열의 중간점
     * @param right 재열의 끝점
     */
    private static void merge(int[] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {

            /**
             * 왼쪽 sublist의 l번째 원소가 오른쪽 sublist r번째 원소보다 작거나 같을경우
             * 왼쪽의 l번째 원소를 새로운 배열에 넣고 l과 idx를 1 증가시킨다.
             */
            if (a[l] <= a[r]) {
                sorted[idx++] = a[l++];
            }
            /**
             * 오른쪽 sublist의 r번째 원소가 왼쪽 sublist l번째 원소보다 작거나 같을경우
             * 오른쪽의 r번째 원소를 새로운 배열에 넣고 r과 idx를 1 증가시킨다.
             */
            else {
                sorted[idx++] = a[r++];
            }
        }

        /**
         * 왼쪽의 sublist가 먼저 모두 채워졌을 경우에는 (l > mid)
         * = 오른쪽 sublist의 원소들이 아직 남아있으므로
         * 오른쪽 sublist의 나머지 원소들을 새 배열에 채워준다.
         */
        if (l > mid) {
            while (r <= right) {
                sorted[idx++] = a[r++];
            }
        }
        /**
         * 오른쪽의 sublist가 먼저 모두 채워졌을 경우에는 (r > right)
         * = 왼쪽 sublist의 원소들이 아직 남아있으므로
         * 왼쪽 sublist의 나머지 원소들을 새 배열에 채워준다.
         */
        else {
            while (l <= mid) {
                sorted[idx++] = a[l++];
            }
        }

        /**
         * 정렬된 배열을 기존의 배열에 복사해서 옮긴다.
         */
        for (int i = left; i <= right; i++) {
            a[i] = sorted[i];
        }
    }
}
