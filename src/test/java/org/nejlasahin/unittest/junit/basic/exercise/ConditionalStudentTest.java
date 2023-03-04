package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.nejlasahin.unittest.junit.basic.annotations.TestOnWindowsWithJRE11;
import org.nejlasahin.unittest.model.Student;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("(Basic Level) Conditional Student Test")
class ConditionalStudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("1", "Nejla", "Sahin");
    }

    @EnabledOnOs({OS.WINDOWS})
    @Test
    void shouldCreateStudentOnlyOnWindows() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @DisabledOnOs({OS.MAC})
    @Test
    void shouldCreateStudentOnlyOnNonMac() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @EnabledOnJre({JRE.JAVA_11})
    @Test
    void shouldCreateStudentOnlyOnJRE11() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_11})
    @Test
    void shouldCreateStudentOnlyOnJRE10orJRE11() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @DisabledOnJre({JRE.JAVA_11})
    @Test
    void shouldCreateStudentOnlyOnNonJRE11() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @DisabledIfSystemProperty(named = "ENV", matches = "dev")
    @Test
    void shouldCreateStudentOnlyOnDevMachine() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @EnabledIf("returnTrue")
    @Test
    void shouldCreateStudentIfMethodReturnTrue() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    @TestOnWindowsWithJRE11
    @Test
    void shouldCreateStudentOnlyOsWindowsWithJRE11() {
        assertAll("Student information",
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname()),
                () -> assertEquals("1", student.getId())
        );
    }

    private boolean returnTrue() {
        return true;
    }
}
