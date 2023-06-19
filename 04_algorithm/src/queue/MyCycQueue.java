package queue;

public class MyCycQueue {
    private final int size = 4;
    private final int[] arr = new int[size + 1];
    private int front = 0;
    private int rear = 0;

    public MyCycQueue() {}

    // 데이터  추가
    public void enQueue(int x) {
        // 다음에 데이터가 들어가는 곳: (rear + 1) % (size + 1)
        // 다음에 데이터가 나올 곳과 일치할 경우 Full
        if ((rear + 1) % (size + 1) == front) {
            throw new RuntimeException("queue is full");
        }

        rear = (rear + 1) % (size + 1);
        arr[rear] = x;
    }

    // 데이터 회수
    public int deQueue() {
        if (front == rear) {
            throw new RuntimeException("queue is empty");
        }

        front = (front + 1) % (size + 1);
        return arr[front];
    }

    public static void main(String[] args) {
        MyCycQueue queue = new MyCycQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.deQueue());
        queue.enQueue(5);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        queue.enQueue(6);
        queue.enQueue(7);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
