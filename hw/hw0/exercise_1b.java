public class exercise_1b {
    public static void drawTriangle(int N){
        int n = 0;
        while (n < N) {
            n += 1;
            for (int i = 0; i < n; i++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        drawTriangle(10);

    }
}
