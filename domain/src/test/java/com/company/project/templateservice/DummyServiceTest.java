package com.company.project.templateservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyServiceTest {

    private final DummyService dummyService = new DummyService();

    @Test
    void greet_shouldReturnGreetingMessage() {
        String name = "Marcos";
        String result = dummyService.greet(name);

        assertEquals("Hello, Marcos", result, "Should greet properly");
    }

    @Test
    void isPositive_shouldReturnTrueForPositiveNumbers() {
        int positiveNumber = 5;
        assertTrue(dummyService.isPositive(positiveNumber), "Should return true for positive numbers");
    }

    @Test
    void isPositive_shouldReturnFalseForZero() {
        int zero = 0;
        assertFalse(dummyService.isPositive(zero), "Should return false for zero");
    }

    @Test
    void isPositive_shouldReturnFalseForNegativeNumbers() {
        int negativeNumber = -3;
        assertFalse(dummyService.isPositive(negativeNumber), "Should return false for negative numbers");
    }
}
