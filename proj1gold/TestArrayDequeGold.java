import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expectedArray = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++) {
            if (expectedArray.size() == 0) {
                int addNumber = StdRandom.uniform(1000);
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    log = log + "addFirst(" + addNumber + ")\n";
                    testArray.addFirst(addNumber);
                    expectedArray.addFirst(addNumber);
                } else {
                    log = log + "addLast(" + addNumber + ")\n";
                    testArray.addLast(addNumber);
                    expectedArray.addLast(addNumber);
                }
            } else {
                int situation = 4;
                int addNumber = StdRandom.uniform(1000);
                Integer testRemoveNumber = 1;
                Integer expectedRemoveNumber = 1;
                switch (situation) {
                    case 0:
                        log = log + "addFirst(" + addNumber + ")\n";
                        testArray.addFirst(addNumber);
                        expectedArray.addFirst(addNumber);
                        break;
                    case 1:
                        log = log + "addLast(" + addNumber + ")\n";
                        testArray.addLast(addNumber);
                        expectedArray.addLast(addNumber);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        testRemoveNumber = testArray.removeFirst();
                        expectedRemoveNumber = expectedArray.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        testRemoveNumber = testArray.removeLast();
                        expectedRemoveNumber = expectedArray.removeLast();
                        break;

                }
                assertEquals(log, expectedRemoveNumber, testRemoveNumber);
            }
        }
    }
}
