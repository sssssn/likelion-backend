package sort;

import java.util.Arrays;

public class SelectionSortRecursive {
    public static void main(String[] args) {
        int[] arr = {25, 12, 18, 24, 2, 21};

        new SelectionSortRecursive().sort(arr, 0);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr, int start) {
        // 모든 원소를 확인했다.
        if (!(start < arr.length)) return;
        // 가장 앞의 원소를 가장 작다고 설정
        int minIndex = start;
        // start + 1부터 마지막 원소까지 비교한다.
        for (int i = start + 1; i < arr.length; i++) {
            // 가장 작은 숫자를 찾는다.
            if (arr[i] < arr[minIndex]) minIndex = i;
        }

        // 가장 앞의 원소와 가장 작은 원소를 교환한다.
        int temp = arr[start];
        arr[start] = arr[minIndex];
        arr[minIndex] = temp;

        // 남은 영역에 대해서 동일한 작업을 진행한다.
        sort(arr, start + 1);
    }
}
