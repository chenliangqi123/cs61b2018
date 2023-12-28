import java.util.Comparator;
public class test01 {
    public static Comparable max(Comparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }
    public static void main(String[] args) {
        Dog[] dogs = {new Dog("A", 3), new Dog("B", 9), new Dog("C", 15)};
        Dog biggestDog = (Dog) max(dogs);
        biggestDog.bark();
        Comparator<Dog> alphabeticalComparator = Dog.getNameComparator();
        Dog d1 = new Dog("A", 3);
        Dog d2 = new Dog("B", 2);
        if (alphabeticalComparator.compare(d1, d2) > 0) {
            d1.bark();
        } else {
            d2.bark();
        }
    }
}
