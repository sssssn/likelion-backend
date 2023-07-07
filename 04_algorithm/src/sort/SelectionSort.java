package sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {25, 12, 18, 24, 2, 21};
        int n = arr.length;

        // 가장 작은 원소를 찾아서 앞으로 보낸다.
        for (int i = 0; i < n - 1; i++) {
            // 1의 값이 총 정렬된 원소의 갯수, 가장 앞의 원소를 가장 작다고 설정
            int minIndex = i;
            // i + 1부터 마지막 원소까지 비교한다.
            for (int j = i + 1; j < n; j++) {
                // 가장 작은 숫자를 찾는다.
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 가장 앞의 원소와 가장 작은 원소를 교환한다.
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            // 다음 반복을 실행한다.
        }
        System.out.println(Arrays.toString(arr));
    }
}
