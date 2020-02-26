package it.discovery.junit.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void sum_TwoPositiveNumbers_ResultPositive() {
        assertEquals(3, calculator.sum(1, 2));
    }

    @Test
    public void sum_TwoNegativeNumbers_ResultNegative() {
        assertEquals(-3, calculator.sum(-1, -2));
    }

    @Test
    public void sum_TwoZeros_ResultZero() {
        assertEquals(0, calculator.sum(0, 0));
    }

    @Test
    public void subtract_TwoPositiveNumbers_ResultPositive() {
        assertEquals(3, calculator.subtract(5, 2));
    }

    @Test
    public void subtract_OneArgumentZero_ResultDoesntChange() {
        assertEquals(4, calculator.subtract(4, 0));
    }

    @Test
    public void multiple_TwoPositiveNumbers_ResultPositive() {
        assertEquals(10,calculator.multiply(5, 2));
    }

    @Test
    public void multiple_OneArgumentZero_ResultZero() {
        assertEquals(0, calculator.multiply(4, 0));
    }

    @Test
    public void divide_TwoEqualNumbers_ResultOne() {
        assertEquals(1, calculator.divide(5, 5));
    }

    @Test
    public void divide_SecondArgumentZero_ExceptionThrown() {
        try {
            calculator.divide(4, 0);
            fail("Divide operation doesn't throw exception");
        } catch (Exception e) {
        }

    }


}
