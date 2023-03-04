package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInstance;
import org.nejlasahin.unittest.model.Course;
import org.nejlasahin.unittest.model.CourseRecord;
import org.nejlasahin.unittest.model.Lecturer;
import org.nejlasahin.unittest.model.Student;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("(Intermediate Level) Student Test With Repeated Test")
class StudentTestWithRepeatedTest implements TestLifecycleReporter {

    private Student student;

    @BeforeAll
    void setUp() {
        student = new Student("1", "Nejla", "Sahin");
    }

    @DisplayName("Add Course to Student")
    @RepeatedTest(value = 5, name = "{displayName} => Add one course to student and student has {currentRepetition} courses")
    void addCourseToStudent(RepetitionInfo repetitionInfo) {
        CourseRecord courseRecord = new CourseRecord(new Course(String.valueOf(repetitionInfo.getCurrentRepetition())), new Lecturer());
        student.addCourse(courseRecord);
        Assertions.assertEquals(repetitionInfo.getCurrentRepetition(), student.getCourseRecords().size());
    }
}
