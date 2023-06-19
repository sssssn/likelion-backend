package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JavaQueue {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        // enqueue
        // offer, add - enQueue 에 해당하는 메서드
        // Queue 가 가득 차있다면 offer - false 반환, add - 예외 발생
        queue.offer(1);
        queue.add(2);

        // dequeue
        // remove, poll - deQueue 에 해당하는 메서드
        // Queue 가 비어있다면 poll - null 반환, remove - 예외 발생
        System.out.println(queue.remove());
        System.out.println(queue.poll());

        // peek
        // element, peek - peek 에 해당하는 메서드
        // Queue 가 비어있다면 peek - null 반환, element - 예외 발생
        System.out.println(queue.peek());
        System.out.println(queue.element());
    }
}
