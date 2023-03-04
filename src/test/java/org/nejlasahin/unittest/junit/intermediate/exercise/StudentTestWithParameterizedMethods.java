package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.nejlasahin.unittest.model.Course;
import org.nejlasahin.unittest.model.CourseRecord;
import org.nejlasahin.unittest.model.Lecturer;
import org.nejlasahin.unittest.model.Student;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("(Intermediate Level) Student Test With Parameterized Methods")
class StudentTestWithParameterizedMethods {

    private Student student;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ValueSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        void addCourseToStudent(String courseCode) {
            Course course = new Course(courseCode);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class EnumSourceDemo {

        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @ParameterizedTest
        @EnumSource(Course.CourseType.class)
        void addCourseToStudent(Course.CourseType courseType) {
            Course course = new Course(String.valueOf(new Random().nextInt(200)), courseType);
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, names = "MANDATORY")
        void addMandatoryCourseToStudent(Course.CourseType courseType) {
            Course course = new Course(String.valueOf(new Random().nextInt(200)), courseType);
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.MANDATORY, course.getCourseType());
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, mode = EnumSource.Mode.EXCLUDE, names = "MANDATORY")
        void addElectiveCourseToStudent(Course.CourseType courseType) {
            Course course = new Course(String.valueOf(new Random().nextInt(200)), courseType);
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.ELECTIVE, course.getCourseType());
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class MethodSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @ParameterizedTest
        @MethodSource
        void addCourseToStudent(String courseCode) {
            Course course = new Course(courseCode);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
        }

        Stream<String> addCourseToStudent() {
            return Stream.of("101", "103", "105");
        }

        @ParameterizedTest
        @MethodSource("courseWithCodeAndType")
        void addCourseToStudent(String courseCode, Course.CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
            Assumptions.assumingThat(courseCode.equals("101"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("103"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("105"),
                    () -> assertEquals(Course.CourseType.ELECTIVE, courseType)
            );
        }

        Stream<Arguments> courseWithCodeAndType() {
            return Stream.of(
                    Arguments.of("101", Course.CourseType.MANDATORY),
                    Arguments.of("103", Course.CourseType.MANDATORY),
                    Arguments.of("105", Course.CourseType.ELECTIVE)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class CsvSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @DisplayName("Add Course to Student")
        @ParameterizedTest(name = "{index} ==> Parameters: first:{0}, second:{1}")
        @CsvSource({"101, MANDATORY", "103, MANDATORY", "105, ELECTIVE"})
        void addCourseToStudent(String courseCode, Course.CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
            Assumptions.assumingThat(courseCode.equals("101"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("103"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("105"),
                    () -> assertEquals(Course.CourseType.ELECTIVE, courseType)
            );
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/courseCodeAndTypes.csv", numLinesToSkip = 1)
        void addCourseToStudentWithCsvFile(String courseCode, Course.CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
            Assumptions.assumingThat(courseCode.equals("101"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("103"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("105"),
                    () -> assertEquals(Course.CourseType.ELECTIVE, courseType)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ArgumentsSourceDemo {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @ParameterizedTest
        @ArgumentsSource(CourseCodeAndTypeProvider.class)
        void addCourseToStudent(String courseCode, Course.CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            CourseRecord courseRecord = new CourseRecord(course, new Lecturer());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getCourseRecords().size());
            assertTrue(student.isTakeCourse(course));
            Assumptions.assumingThat(courseCode.equals("101"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("103"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            Assumptions.assumingThat(courseCode.equals("105"),
                    () -> assertEquals(Course.CourseType.ELECTIVE, courseType)
            );
        }
    }

    static class CourseCodeAndTypeProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of("101", Course.CourseType.MANDATORY),
                    Arguments.of("103", Course.CourseType.MANDATORY),
                    Arguments.of("105", Course.CourseType.ELECTIVE)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class TypeConversionAndCustomDisplayNameDemo {

        // enum conversion
        @BeforeAll
        void setUp() {
            student = new Student("1", "Nejla", "Sahin");
        }

        @ParameterizedTest
        @ValueSource(strings = {"MANDATORY", "ELECTIVE"})
        void addCourseToStudent(Course.CourseType courseType) {
            Course course = new Course(String.valueOf(new Random().nextInt(200)), courseType);
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // user guide for other options

        // factory method or constructor conversion
        @ParameterizedTest
        @ValueSource(strings = {"101", "103"})
        void addCourseToStudent(Course course) {
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // conversion using SimpleConverter with @ConvertWith
        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        void addCourseToStudentWithConverter(@ConvertWith(CourseConvert.class) Course course) {
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // conversion with @JavaTimeConversionPattern
        @DisplayName("Add course with LocalDate info")
        @ParameterizedTest(name = "{index} ==> Parameters: {arguments}")
        @ValueSource(strings = {"01.01.2023", "01.02.2023", "01.03.2023"})
        void addCourseToStudentWithLocalDate(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate localDate) {
            Course course = new Course(String.valueOf(new Random().nextInt(200)), localDate);
            student.addCourse(new CourseRecord(course, new Lecturer()));
            assertFalse(student.getCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }
    }


    static class CourseConvert extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            return new Course((String) o);
        }
    }
}
