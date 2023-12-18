public class test01 {
    public static void main(String[] args) {
        Inlist L = new Inlist(5, null);
        Inlist L1 = new Inlist(10, L);
        SLList L2 = new SLList(5);
        L2.addFirst(10);

        System.out.println(L2.getFirst());
        System.out.println(L2.size());
//        System.out.println(SLList.size(new SLList.IntNode(4, null)));
        L2.addLast(20);
        System.out.println(L2.size());

    }
}
