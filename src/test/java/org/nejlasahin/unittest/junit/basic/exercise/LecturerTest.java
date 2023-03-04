package org.nejlasahin.unittest.junit.basic.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.model.Course;
import org.nejlasahin.unittest.model.CourseRecord;
import org.nejlasahin.unittest.model.Lecturer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("(Basic Level) Lecturer Test")
class LecturerTest {

    private Lecturer lecturer;

    @BeforeEach
    void setUp() {
        lecturer = new Lecturer();
    }

    @Test
    @DisplayName("When a lecturer course record is added to lecturer then lecturer course record size increase")
    void whenACourseIsAddedToLecturerThenLecturerCourseSizeIncrease() {

        assertEquals(0, lecturer.getCourseRecords().size());
        lecturer.addCourseRecord(getCourseRecord(lecturer));
        assertEquals(1, lecturer.getCourseRecords().size());
    }

    @Test
    @DisplayName("Lecturer course record has lecturer when added to lecturer")
    void lecturerCourseRecordHasLecturerInfoWhenAddedToALecturer() {

        CourseRecord courseRecord = getCourseRecord(lecturer);
        lecturer.addCourseRecord(courseRecord);
        assertEquals(lecturer, courseRecord.getLecturer());
    }

    @Test
    void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer() {
        CourseRecord courseRecord = new CourseRecord(null, lecturer);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> lecturer.addCourseRecord(courseRecord));
        assertEquals("Can't add a null course to lecturer", illegalArgumentException.getMessage());
    }

    private CourseRecord getCourseRecord(Lecturer lecturer) {
        return new CourseRecord(new Course(), lecturer);
    }

}