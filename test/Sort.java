public class Sort {
    public static void sort(String[] s){
        sort(s, 0);
    }
    private static void sort(String[] s, int start) {
        if (start == s.length) {
            return;
        }
        int smallIndex = findSmallest(s, start);
        swap(s, start, smallIndex);
        sort(s, start+1);
    }
    public static int findSmallest(String[] s, int start) {
        int smallest = start;
        for (int i = start; i < s.length; i++) {
            int cmp = s[i].compareTo(s[smallest]);
            if (cmp < 0) {
                smallest = i;
            }
        }
        return smallest;
    }
    public static void swap(String[] s, int x, int y) {
        String temp = s[x];
        s[x] = s[y];
        s[y] = temp;
    }
}
