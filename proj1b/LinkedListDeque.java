public class LinkedListDeque<T> implements Deque<T> {
    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node prev, T x, Node next) {
            this.prev = prev;
            item = x;
            this.next = next;
        }

        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void addFirst(T item) {
        Node addNode = new Node(null, item, null);
        sentinel.next.prev = addNode;
        addNode.next = sentinel.next;
        addNode.prev = sentinel;
        sentinel.next = addNode;
        size++;
    }
    @Override
    public void addLast(T item) {
        Node addNode = new Node(null, item, null);
        addNode.next = sentinel;
        addNode.prev = sentinel.prev;
        sentinel.prev.next = addNode;
        sentinel.prev = addNode;
        size++;
    }
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node removedNode = sentinel.next;
        sentinel.next = removedNode.next;
        removedNode.next.prev = sentinel;
        size--;
        return removedNode.item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size--;
        return removeNode.item;
    }
    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node ptr = sentinel;
        int currentIndex = -1;
        while (currentIndex != index) {
            ptr = ptr.next;
            currentIndex++;
        }
        return ptr.item;
    }

    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }
}
