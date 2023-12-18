public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }


    private IntNode sentinel;
    private int size;

    public SLList(){
        sentinel = new IntNode(69, null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(69, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }
    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst(){
        return sentinel.next.item;
    }

    public static int size(IntNode p){
        if(p.next == null){
            return 0;
        }
        return 1+size(p.next);
    }

    public int size(){
//        return size(sentinel);
        return size;
    }

    public void addLast(int x){
        size += 1;
        IntNode p = sentinel;

        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }
}
