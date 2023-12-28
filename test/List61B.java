public interface List61B<Item> {
    public void addFirst(Item x);
    public void addLast(Item y);
    public Item removeLast();
    public Item get(int i);
    public int size();
    default public void printDeque() {
        System.out.println("This is a test");
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
