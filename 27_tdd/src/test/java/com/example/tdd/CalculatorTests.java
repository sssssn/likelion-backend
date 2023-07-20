package com.example.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }

    // 1. 테스트를 작성한다.
    @Test
    public void additionTest() {
        // 2. 테스트를 실행하고 실패한다.
//        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        assertEquals(6, calculator.add(1, 2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    @Test
    public void subtractionTest() {
//        Calculator calculator = new Calculator();
        assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void multipleTest() {
//        Calculator calculator = new Calculator();
        assertEquals(9, calculator.multiple(3, 3));
    }

    @Test
    public void divisionTest() {
//        Calculator calculator = new Calculator();
        assertEquals(3, calculator.divide(6, 2));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
        assertEquals("division by zero", exception.getMessage());
    }

    // 3. 테스트를 통과하는 코드를 작성한다.
    private class Calculator {
//        public int add(int a, int b) {
//            return a + b;
//        }
//
//        public int add(int a, int b, int c) {
//            return a + b + c;
//        }

        public int add(int ...numbers) {
            int sum = 0;
            for (int i: numbers) {
                sum += i;
            }
            return sum;
        }

        public int sub(int a, int b) {
            return a - b;
        }

        public int multiple(int a, int b) {
            return a * b;
        }

        public int divide(int a, int b) {
            if (b == 0)
                throw new IllegalArgumentException("division by zero");
            return a / b;
        }
    }
}
