package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.exercise.PrimeFactor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("(Basic Level) Prime Factors Test")
class PrimeFactorsTest {

    @Test
    @DisplayName("Prime Factors")
    void findPrimeFactors() {
        PrimeFactor primeFactor = new PrimeFactor();
        assertAll("Prime Factors",
                () -> assertEquals(List.of(2, 2, 5), primeFactor.generate(20)),
                () -> assertEquals(List.of(5), primeFactor.generate(5)),
                () -> assertEquals(List.of(2, 2, 3), primeFactor.generate(12))
        );
    }
}
