public class test02 {
    public static String longest(List61B<String> list) {
        int maxDex = 0;
        for (int i = 0; i < list.size(); i += 1) {
            String longestString = list.get(maxDex);
            String thisString = list.get(i);
            if (thisString.length() > longestString.length()) {
                maxDex = i;
            }
        }
        return list.get(maxDex);
    }
    public static void main(String[] args) {
        List61B<String> L1 = new LinkedListDeque<>();
        L1.addLast("something");
        L1.addFirst("great");
        System.out.println(longest(L1));
        L1.printDeque();
    }
}
