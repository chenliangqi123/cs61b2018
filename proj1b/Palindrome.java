public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int i = 0;
        while (i < word.length() / 2) {
            if (word.charAt(i) != word.charAt(word.length()-1-i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int i = 0;
        while (i < word.length() / 2) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length()-1-i))) {
                return false;
            }
            i++;
        }
        return true;
    }
}
