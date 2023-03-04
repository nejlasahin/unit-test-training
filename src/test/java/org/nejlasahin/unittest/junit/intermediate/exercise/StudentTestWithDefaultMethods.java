package org.nejlasahin.unittest.junit.intermediate.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nejlasahin.unittest.model.Student;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("(Intermediate Level) Student Test With Default Methods")
class StudentTestWithDefaultMethods implements CreateDomain<Student>, TestLifecycleReporter {

    @Override
    public Student createDomain() {
        return new Student("1", "Nejla", "Sahin");
    }

    @Test
    void createStudent() {
        Student student = createDomain();
        assertAll("Student information",
                () -> assertEquals("1", student.getId()),
                () -> assertEquals("Nejla", student.getName()),
                () -> assertEquals("Sahin", student.getSurname())
        );
    }
}
