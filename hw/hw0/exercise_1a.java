public class exercise_1a {
    public static void main(String[] args) {
        int n = 0;
        while (n < 5) {
            n += 1;
            for (int i = 0; i < n; i++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
