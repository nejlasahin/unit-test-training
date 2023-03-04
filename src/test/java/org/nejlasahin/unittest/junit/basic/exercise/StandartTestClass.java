package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@DisplayName("(Basic Level) Standart Test Class")
class StandartTestClass {

    private static String oneInstancePerClass;
    private Integer oneInstancePerMethod;

    @BeforeAll
    static void initAll() {
        oneInstancePerClass = String.valueOf(new Random().nextInt());
        System.out.println("Init Before All Test Method");
    }

    @AfterAll
    static void tearDownAll() {
        oneInstancePerClass = null;
        System.out.println("Tear Down After All Test Method");
    }

    @BeforeEach
    void init() {
        oneInstancePerMethod = new Random().nextInt();
        System.out.println("Init Before Each Test Method");
    }

    @AfterEach
    void tearDown() {
        oneInstancePerMethod = null;
        System.out.println("Tear Down After Each Test Method");
    }

    @Test
    void testSomething1() {
        System.out.println("Test: testSomething1:" + oneInstancePerMethod + ":" + oneInstancePerClass);
    }

    @Test
    void testSomething2() {
        System.out.println("Test: testSomething2:" + oneInstancePerMethod + ":" + oneInstancePerClass);
    }

    @Test
    @Disabled("This test is not in scope for now")
    void testSomething3() {
        System.out.println("Test: testSomething3");
    }

    @Test
    void testSomethingToFail() {
        Assertions.fail("A failing test....");
    }
}