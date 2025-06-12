package com.company.project.templateservice.domain.src.test.java.com.company.project.templateservice;

import com.company.project.templateservice.DummyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyServiceTest {

    private final DummyService dummyService = new DummyService();

    @Test
    void greetShouldReturnGreetingMessage() {
        String name = "Marcos";
        String result = dummyService.greet(name);

        assertEquals("Hello, Marcos", result, "Should greet properly");
    }

    @Test
    void isPositiveShouldReturnTrueForPositiveNumbers() {
        int positiveNumber = 5;
        assertTrue(dummyService.isPositive(positiveNumber), "Should return true for positive numbers");
    }

    @Test
    void isPositiveShouldReturnFalseForZero() {
        int zero = 0;
        assertFalse(dummyService.isPositive(zero), "Should return false for zero");
    }

    @Test
    void isPositiveShouldReturnFalseForNegativeNumbers() {
        int negativeNumber = -3;
        assertFalse(dummyService.isPositive(negativeNumber), "Should return false for negative numbers");
    }
}
