package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.nejlasahin.unittest.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("(Intermediate Level) Student Test With Test Lifecycle")
class StudentTestWithTestLifecycle {

    private Student student = new Student("1", "Nejla", "Sahin");

    @BeforeAll
    void setUp() {

    }

    @Test
    void stateCannotChangeWhenLifecycleIsPerMethod() {
        assertEquals("Nejla", student.getName());
        student = new Student("2", "John", "Doe");
    }

    @Test
    void stateCanChangeWhenLifecycleIsPerClass() {
        assertEquals("Nejla", student.getName());
        student = new Student("2", "John", "Doe");
    }
}
