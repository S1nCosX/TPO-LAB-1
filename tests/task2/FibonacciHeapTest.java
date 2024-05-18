package tests.task2;

import main.task2.FibonacciHeap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciHeapTest {
    @Test
    @DisplayName("Check is heap contains values")
    void checkHeapContainsValues(){
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(2);
        assertTrue(heap.contains(1) && heap.contains(3) && heap.contains(5) && heap.contains(2));
    }

    @Test
    @DisplayName("Check is heap poll correctly")
    void checkHeapPollCorrectly(){
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.add(1432);
        assertTrue(heap.poll() == 1432 && heap.isEmpty());
    }

    @Test
    @DisplayName("Check is heap peek correctly")
    void checkHeapPeekCorrectly(){
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.add(1234);
        heap.add(123);
        heap.add(32);
        assertTrue(heap.peek() == 1234 && !heap.isEmpty());
    }

    @Test
    @DisplayName("Check is heap clear correctly")
    void checkHeapClearCorrectly(){
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.add(1234);
        heap.add(123);
        heap.add(32);
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    @DisplayName("Check is heap size correct")
    void checkHeapSizeIsCorrect(){
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.add(1234);
        heap.add(123);
        heap.add(32);
        assertTrue(heap.size() == 3);
    }
}
