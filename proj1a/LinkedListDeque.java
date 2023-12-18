public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node{
        public Node prev;
        public T item;
        public Node next;
        public Node(Node prev, T x, Node next){
            this.prev = prev;
            item = x;
            this.next = next;
        }
        public Node(Node prev, Node next){
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(T item){
        Node addNode = new Node(null, item, null);
        sentinel.next.prev = addNode;
        addNode.next = sentinel.next;
        addNode.prev = sentinel;
        sentinel.next = addNode;
        size++;
    }

    public void addLast(T item){
        Node addNode = new Node(null, item, null);
        addNode.next = sentinel;
        addNode.prev = sentinel.prev;
        sentinel.prev.next = addNode;
        sentinel.prev = addNode;
        size++;
    }

    public void printDeque(){
        Node p = sentinel.next;
        while(p != sentinel){
            System.out.println(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        Node removedNode = sentinel.next;
        sentinel.next = removedNode.next;
        removedNode.next.prev = sentinel;
        size--;
        return removedNode.item;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        Node removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size--;
        return removeNode.item;
    }

    public T get(int index){
        if(index > size-1 || index < 0){
            return null;
        }
        Node ptr = sentinel;
        int current_index = -1;
        while(current_index != index){
            ptr = ptr.next;
            current_index++;
        }
        return ptr.item;
    }
}
