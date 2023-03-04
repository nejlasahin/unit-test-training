package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.model.CourseRecord;
import org.nejlasahin.unittest.model.Student;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayName("(Intermediate Level) Student With Nested Test")
@Tag("student")
class StudentWithNestedTest {

    @Nested
    @DisplayName("Create Student")
    class CreateStudent {
        @Test
        @DisplayName("Test every student have an id, name and surname")
        @Tag("createStudent")
        void shouldCreateStudentWithIdNameAndSurname() {
            Student nejla = new Student("1", "Nejla", "Sahin");

            assertEquals("Nejla", nejla.getName());
            assertEquals("Nejla", nejla.getName(), "Student's name");
            assertNotEquals("Nejla-", nejla.getName(), "Student's name");

            assertTrue(nejla.getName().startsWith("N"));
            assertTrue(nejla.getName().startsWith("N"), () -> "Student's name " + "starts with N");

            assertFalse(() -> {
                Student student = new Student("2", "Name", "Surname");
                return student.getName().endsWith("a");
            }, () -> "Student's name " + "ends with e");

            Student student1 = new Student("2", "Name", "Surname");
            assertArrayEquals(new String[]{"Nejla", "Name"}, Stream.of(nejla, student1).map(Student::getName).toArray());

            Student sameStudent = student1;

            assertSame(sameStudent, student1);
            assertNotSame(nejla, student1);

            /**
             * grouped assertions
             * failed grouped assertions
             * dependent assertions
             */

            assertAll("Student's name check",
                    () -> assertEquals("Nejla", nejla.getName()),
                    () -> assertEquals("Nejla", nejla.getName(), "Student's name"),
                    () -> assertNotNull(nejla.getName())
            );

            assertAll("Student's name character check",
                    () -> assertTrue(nejla.getName().startsWith("N")),
                    () -> assertTrue(nejla.getName().startsWith("N"), () -> "Student's name " + "starts with N"),
                    () -> assertFalse(() -> {
                        Student student = new Student("2", "Name", "Surname");
                        return student.getName().endsWith("a");
                    }, () -> "Student's name " + "ends with e")
            );

            assertAll(
                    () -> assertArrayEquals(new String[]{"Nejla", "Name"}, Stream.of(nejla, student1).map(Student::getName).toArray()),
                    () -> {
                        assertSame(sameStudent, student1);
                        assertNotSame(nejla, student1);
                    }
            );
        }

        @Test
        @DisplayName("Test student creation at only development machine")
        @Tag("createStudent")
        void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {

            assumeTrue(System.getProperty("ENV") != null, "Aborting Test: System property ENV doesn't exist");
            assumeTrue(System.getProperty("ENV").equals("dev"), "Aborting Test: Not on a developer machine");

            final Student student = new Student("1", "Nejla", "Sahin");
            assertAll("Student information",
                    () -> assertEquals("Nejla", student.getName()),
                    () -> assertEquals("Sahin", student.getSurname()),
                    () -> assertEquals("1", student.getId())
            );
        }

        @Test
        @DisplayName("Test student creation at different enviroments")
        @Tag("createStudent")
        void shouldCreateStudentWithNameAndSurnameWithSpecificEnviroment() {

            final Student student = new Student("1", "Nejla", "Sahin");

            String env = System.getProperty("ENV");
            assumingThat(env != null && env.equals("dev"), () -> {
                CourseRecord courseRecord = new CourseRecord();
                student.addCourse(courseRecord);
                assertEquals(1, student.getCourseRecords().size());
            });
            assertAll("Student information",
                    () -> assertEquals("Nejla", student.getName()),
                    () -> assertEquals("Sahin", student.getSurname()),
                    () -> assertEquals("1", student.getId())
            );
        }

        @Disabled("No more valid scenario")
        @Test
        @DisplayName("Test that student must have only number id")
        @Tag("createStudent")
        void shouldCreateStudentWithNumberId() {
            assertThrows(IllegalArgumentException.class, () -> new Student("1", "Nejla", "Sahin"));
        }
    }

    @Nested
    @DisplayName("Add Course to Student")
    class AddCourse {
        @Test
        @DisplayName("Add course to a student less than 10ms")
        @Tag("addCourse")
        void addCourseToStudentWithATimeConstraint() {
            assertTimeout(Duration.ofMillis(10), () -> {

            });

            final String result = assertTimeout(Duration.ofMillis(10), () -> {
                return "some string result";
            });
            assertEquals("some string result", result);

            final Student student = new Student("1", "Nejla", "Sahin");
            CourseRecord courseRecord = new CourseRecord();
            assertTimeout(Duration.ofMillis(5), () -> student.addCourse(courseRecord));

            assertTimeoutPreemptively(Duration.ofMillis(5), () -> {
                Thread.sleep(1);
                student.addCourse(courseRecord);
            });
        }

        @Nested
        @DisplayName("Add Course to Student(Exceptional)")
        class AddCourseToStudentExceptionScenario {
            @Test
            @DisplayName("Got an exception when add a null lecturer course record to student")
            @Tags({@Tag("addCourse"), @Tag("exceptional")})
            void throwsExceptionWhenAddToNullCourseToStudent() {
                Student student = new Student("1", "Nejla", "Sahin");
                assertThrows(IllegalArgumentException.class, () -> student.addCourse(null));
                assertThrows(IllegalArgumentException.class, () -> student.addCourse(null), "Throws IllegalArgumentException");
                IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> student.addCourse(null));
                assertEquals("Can't add course with null course record", illegalArgumentException.getMessage());
            }
        }

    }
}