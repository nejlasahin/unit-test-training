package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.exercise.FibonacciNumber;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("(Basic Level) Fibonacci Number Test")
class FibonacciNumberTest {

    @Test
    @DisplayName("Find Fibonacci Numbers for Spesific Order")
    void findFibonacciNumbers() {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        // 1 1 2 3 5 8
        assertThrows(IllegalArgumentException.class, () -> fibonacciNumber.find(0));
        assertAll("Fibonacci Numbers",
                () -> assertEquals(1, fibonacciNumber.find(1)),
                () -> assertEquals(1, fibonacciNumber.find(2)),
                () -> assertEquals(2, fibonacciNumber.find(3)),
                () -> assertEquals(3, fibonacciNumber.find(4)),
                () -> assertEquals(5, fibonacciNumber.find(5))
        );
    }
}