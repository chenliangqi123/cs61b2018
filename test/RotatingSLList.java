public class RotatingSLList<T> extends LinkedListDeque<T> {
    public void rotateRight() {
        T item = removeLast();
        addFirst(item);
    }
    public static void main(String[] args) {
        RotatingSLList<Integer> rs1 = new RotatingSLList<>();
        rs1.addLast(10);
        rs1.addLast(11);
        rs1.addLast(12);
        rs1.addLast(13);
        rs1.rotateRight();
        rs1.printDeque();
    }
}
