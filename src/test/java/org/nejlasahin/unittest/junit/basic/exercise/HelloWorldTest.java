package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.exercise.HelloWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("(Basic Level) Hello World Test")
class HelloWorldTest {

    @Test
    void sayHelloToWorld() {
        HelloWorld helloWorld = new HelloWorld();
        assertEquals("Hello World!", helloWorld.sayHello(), "Say Hello to World!");
    }
}
