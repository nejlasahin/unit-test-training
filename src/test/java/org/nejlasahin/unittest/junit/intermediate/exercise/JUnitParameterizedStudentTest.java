package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.nejlasahin.unittest.model.Student;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("(Intermediate Level) Student Test with TestInfo and TestReporter Parameters")
class JUnitParameterizedStudentTest {

    private Student student;

    public JUnitParameterizedStudentTest(TestInfo testInfo) {
        assertEquals("Student Test with TestInfo and TestReporter Parameters", testInfo.getDisplayName());
    }

    @BeforeEach
    void setStudent(TestInfo testInfo) {
        if (testInfo.getTags().contains("createStudent")) {
            student = new Student("1", "Nejla", "Sahin");
        } else {
            student = new Student("2", "John", "Doe");
        }
    }

    @Test
    @DisplayName("Create Student")
    @Tag("createStudent")
    void createStudent(TestInfo testInfo) {
        assertTrue(testInfo.getTags().contains("createStudent"));
        assertEquals("Nejla", student.getName());
    }

    @Test
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    void addCourseToStudent(TestReporter testReporter) {
        testReporter.publishEntry("startTime", LocalDateTime.now().toString());
        assertEquals("John", student.getName());
        testReporter.publishEntry("endtTime", LocalDateTime.now().toString());
    }
}
