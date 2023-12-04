public class exercise_3 {
    public static int forMax(int[] m) {
        int mvalue = m[0];
        for (int j : m) {
            if (j > mvalue) {
                mvalue = j;
            }
        }
        return mvalue;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        int m = forMax(numbers);
        System.out.println(m);
    }
}
