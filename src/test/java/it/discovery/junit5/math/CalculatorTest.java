package it.discovery.junit5.math;

import it.discovery.junit.math.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Nested
    class SumOperation {
        @Test
        @DisplayName("Sum of two positive number is positive")
        public void sum_TwoPositiveNumbers_ResultPositive() {
            assertEquals(3, calculator.sum(1, 2));
        }

        @Test
        void sum_TwoNegativeNumbers_ResultNegative() {
            assertEquals(-3, calculator.sum(-1, -2));
        }

        @Test
        void sum_TwoZeros_ResultZero() {
            assertEquals(0, calculator.sum(0, 0));
        }
    }

    @Nested
    class SubtractOperation {
        @Test
        void subtract_TwoPositiveNumbers_ResultPositive() {
            assertEquals(3, calculator.subtract(5, 2));
        }

        @Test
        void subtract_OneArgumentZero_ResultDoesntChange() {
            assertEquals(4, calculator.subtract(4, 0));
        }
    }

    @Test
    @EnabledIfSystemProperty(named = "enableTests", matches = ".*true.*")
    void multiple_TwoPositiveNumbers_ResultPositive() {
        assertEquals(10, calculator.multiply(5, 2));
    }

    @ParameterizedTest(name = "Multiple any value to zero is always zero")
    @MethodSource("getParams")
    void multiple_OneArgumentZero_ResultZero(int firstArg) {
        assertEquals(0, calculator.multiply(firstArg, 0));
    }

    public static Stream<Integer> getParams() {
        return Stream.of(1, 2, 3);
    }

//    @Test
//    void divide_TwoEqualNumbers_ResultOne() {
//        assertEquals(1, calculator.divide(5, 5));
//    }

    @TestFactory
    Stream<DynamicTest> divide_TwoEqualNumbers_ResultOne() {
        Random random = new Random();
        return Stream.generate(() -> random.nextInt(Integer.MAX_VALUE))
                .limit(5).map(i -> DynamicTest
                        .dynamicTest("Divide " + i + " by itself produces one",
                        () -> assertEquals(1, calculator.divide(i, i))));
    }

    @Test
    public void divide_SecondArgumentZero_ExceptionThrown() {
        assertThrows(Exception.class, () -> calculator.divide(4, 0));
    }
}
