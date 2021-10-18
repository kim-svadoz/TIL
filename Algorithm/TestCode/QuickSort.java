public class QuickSort {
    private static int[] input = {5, 6, 2, 1, 8, 24, 5, 3};

    public static void main(String[] args) {
        quickSort(input);
        for (int i : input) {
            System.out.print(i+" ");
        }
    }

    public static void quickSort(int[] a) {
        leftPivotSort(a, 0, a.length - 1);
    }

    /**
     * 가장 왼쪽 피벗 선택 경우
     * @param a     정렬할 배열
     * @param lo    현재 부분배열의 가장 왼쪽
     * @param hi    현재 부분배열의 가장 오른쪽
     */
    private static void leftPivotSort(int[] a, int lo, int hi) {

        /**
         * lo가 hi보다 크거나 같다면 정렬할 원소가 1개 이하이므로 정렬없이 return
         */
        if (lo >= hi) {
            return;
        }

        int pivot = partition(a, lo, hi);

        leftPivotSort(a, lo, pivot - 1);
        leftPivotSort(a, pivot + 1, hi);
    }

    /**
     * 피벗을 기준으로 파티션을 나누기 위해 약한 정렬을 수행한다.
     * @param a     정렬 배열
     * @param left  현재 배열외 가장 왼쪽
     * @param right 현재 배열의 가장 오른쪽
     * @return      최종적으로 위치한 피벗의 위치(lo)를 반환
     */
    private static int partition(int[] a, int left, int right) {
        int lo = left;
        int hi = right;
        int pivot = a[left];        // 처음엔 sublist의 가장 왼쪽 요소를 피벗으로 선정

        // lo가 hi보다 작을때가지만
        while (lo < hi) {

            /**
             * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은원소를 찾을떄까지
             * hi를 감소시킨다.
             */
            while (a[hi] > pivot && lo < hi) {
                hi--;
            }
    
            /**
             * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를 찾을떄까지
             * lo++
             */
            while (a[lo] <= pivot && lo < hi) {
                lo++;
            }
    
            swap(a, lo, hi);
        }

        /**
         * 마지막으로 맨 처음 pivot으로 설정했던 위치(a[left])의 원소가 lo가 가리키는 원소를 바꾼다.
         */
        swap(a, left, lo);

        return lo;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
