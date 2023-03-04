package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.nejlasahin.unittest.model.Course;
import org.nejlasahin.unittest.model.CourseRecord;
import org.nejlasahin.unittest.model.Lecturer;
import org.nejlasahin.unittest.model.Student;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("(Intermediate Level) Student Test With Dynamic Test")
class StudentTestWithDynamicTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("1", "Nejla", "Sahin");
    }

    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType(TestReporter testReporter) {
        return Stream.of("101", "103", "105")
                .map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
                        Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE)
                                .map(courseType -> dynamicTest("Add course<" + courseType + " to student", () -> {
                                    Course course = new Course(courseCode, courseType);
                                    CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
                                    student.addCourse(courseRecord);
                                    Assertions.assertTrue(student.isTakeCourse(course));
                                    testReporter.publishEntry("Student Course Size", String.valueOf(student.getCourseRecords().size()));
                                }))
                ));
    }

    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() {
        Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();
        Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
        ThrowingConsumer<String> testExecutor = courseCode -> {
            Course course = new Course(courseCode, Course.CourseType.MANDATORY);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            Assertions.assertTrue(student.isTakeCourse(course));
        };
        return DynamicTest.stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }
}
