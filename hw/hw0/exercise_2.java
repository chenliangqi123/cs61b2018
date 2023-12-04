public class exercise_2 {
    public static int max(int[] m) {
        int i =0;
        int max_value = m[0];
        while (i<m.length) {
            if (m[i] > max_value){
                max_value = m[i];
            }
            i = i+1;

        }
        return max_value;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
