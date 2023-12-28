import org.junit.Test;
import static org.junit.Assert.*;
public class TestSort {
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        assertArrayEquals(input, expected);
    }
    @Test
    public void testFindSmallest(){
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;
        int actual = Sort.findSmallest(input, 0);
        assertEquals(expected, actual);
    }


}
