package HomeWork6;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class CalculatorTest {
    private Calculator calculator;

    static Stream<Arguments> dataA() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}),
                Arguments.arguments(new int[]{9, 8, 4, 7, 2, 5, 6, 7, 1}, new int[]{7, 2, 5, 6, 7, 1}),
                Arguments.arguments(new int[]{9, 8, 4, 7, 2, 5, 6, 7, 1}, new int[]{5, 6, 7, 2})
        );
    }

    static Stream<Arguments> dataB() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1}),
                Arguments.arguments(new int[]{4, 4, 4, 4}),
                Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3})
        );
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @DisplayName("ParameterizedTest methodA")
    @ParameterizedTest
    @MethodSource("dataA")
    void methodA1(int[] arrA, int[] arrB) {
        Assertions.assertArrayEquals(arrB, calculator.methodA(arrA));
    }

    @Test
    void methodA2() {
        Assertions.assertThrows(RuntimeException.class, () -> calculator.methodA(new int[]{9, 8, 5, 7, 2, 5, 6, 7, 0}));
    }

    @Test
    void methodA3() {
        Assertions.assertThrows(RuntimeException.class, () -> calculator.methodA(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }


    @Test
    void methodB1() {
        Assertions.assertTrue(calculator.methodB(new int[]{1, 1, 1, 4, 4, 1, 4, 4}));
    }

    @DisplayName("ParameterizedTest methodB")
    @ParameterizedTest
    @MethodSource("dataB")
    void methodB2(int[] arr) {
        Assertions.assertFalse(calculator.methodB(arr));
    }

    @Test
    void methodB3() {
        Assertions.assertTrue(calculator.methodB(new int[]{2, 3, 2, 6, 8, 9, 0, 0}));
    }


}