import java.util.*;


public class test {
    public static List<String> getWords(String inputFileName) {
        List<String> lst = new ArrayList<>();
        In in = new In(inputFileName);
        while (!in.isEmpty()) {
            String word = in.readString();

            lst.add(word);
//            System.out.println(in.readString());
        }
        return lst;
    }
    public static int countUniqueWords(List<String> words) {
        Set<String> ss = new HashSet<>();
        for (String word : words) {
            ss.add(word);
        }
        return ss.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<>();
        for (String t : targets) {
            counts.put(t, 0);
        }
        for (String w : words) {
            if (counts.containsKey(w)) {
                counts.put(w, counts.get(w)+1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> lst1 = getWords("testFile.txt");
        List<String> lst2 = new LinkedList<>();
        lst2.add("fsdfs");
        Map<String, Integer> mapping = collectWordCount(lst1, lst2);
        System.out.println(mapping);
//        for (String word : lst1) {
//            System.out.println(word);
//        }
//        System.out.println(lst1);
//        System.out.println(countUniqueWords(lst1));
    }
}
