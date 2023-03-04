package org.nejlasahin.unittest.model;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private String id;
    private String name;
    private String surname;
    private Set<CourseRecord> courseRecords = new HashSet<>();

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void addCourse(CourseRecord courseRecord) {
        if (courseRecord == null) {
            throw new IllegalArgumentException("Can't add course with null course record");
        }

        courseRecords.add(courseRecord);
    }

    public void dropCurse(CourseRecord courseRecord) {
        if (courseRecord == null) {
            throw new IllegalArgumentException("Can't drop course with null course record");
        }

        courseRecords.stream()
                .filter(course -> course.getCourse().getCode().equals(courseRecord.getCourse().getCode()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no student course record for given course record"));

        courseRecords.remove(courseRecord);
    }

    public boolean isTakeCourse(Course course) {
        return courseRecords.stream()
                .map(CourseRecord::getCourse)
                .anyMatch(course1 -> course1.equals(course));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<CourseRecord> getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(Set<CourseRecord> courseRecords) {
        this.courseRecords = courseRecords;
    }
}
