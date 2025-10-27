public class SelectionSort {
    private static int[] input = {5, 6, 2, 1, 8, 24, 5, 3};

    public static void main(String[] args) {
        selectionSortMin(input, input.length);
        for (int i : input) {
            System.out.print(i+" ");
        }
    }

    /* 오름차순 */
    private static void selectionSortMin(int[] a, int len) {
        int min;
        for  (int i = 0; i < len - 1; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    /* 내림차순 */
    private static void selectionSortMax(int[] a, int len) {
        int max;
        for (int i = 0; i < len - 1; i++) {
            max = i;
            for (int j = i + 1; j < len; j++) {
                if (a[j] > a[max]) {
                    max = j;
                }
            }
            swap(a, i, max);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
