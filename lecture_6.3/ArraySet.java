import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size++;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    public class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnValue = items[wizPos];
            wizPos++;
            return returnValue;
        }

    }
    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(10);
        aset.add(20);
        aset.add(30);
        for (int a : aset) {
            System.out.println(a);
        }
    }
}
