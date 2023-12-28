public class VengefulSLList<T> extends LinkedListDeque<T> {
    LinkedListDeque<T> deletedItems;
    public VengefulSLList() {
        deletedItems = new LinkedListDeque<>();
    }

    @Override
    public T removeLast() {
        T item = super.removeLast();
        deletedItems.addLast(item);
        return item;
    }
    public void printLostItems() {
        deletedItems.printDeque();
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> rs1 = new VengefulSLList<>();
        rs1.addLast(10);
        rs1.addLast(11);
        rs1.addLast(12);
        rs1.addLast(13);
        rs1.removeLast();
        rs1.removeLast();
        rs1.printLostItems();
    }
}
