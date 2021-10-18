public class BubbleSort {
    public static int[] input = {5, 12, 6, 7, 12, 2, 4, 9, 754};

    public static void main(String[] args) {
        bubbleSort(input, input.length);
        for (int i : input) {
            System.out.print(i+" ");
        }
    }

    public static void bubbleSort(int[] a, int len) {
        for (int i = 1; i < len; i++) {
            boolean swapped = false;

            for (int j = 0; j < len - i; j++) {
                /**
                 * 현재 원소가 다음 원소 보다 클 때는
                 * 서로 원소의 위치를 바꾸고
                 * swapped 변수를 true로 변경
                 */
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }

            /**
             * 만약 swap된적이 없다면 이미 정렬되엇다는 것이므로 바로 반복문을 종료 -> 정렬 성능 개선
             */
            if (swapped == false) {
                break;
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
