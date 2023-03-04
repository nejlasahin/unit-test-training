package org.nejlasahin.unittest.model;

import java.util.HashSet;
import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<CourseRecord> courseRecords = new HashSet<>();

    public Lecturer(String id, String name, String surname, String title, Set<CourseRecord> courseRecords) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.courseRecords = courseRecords;
    }

    public Lecturer() {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CourseRecord> getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(Set<CourseRecord> courseRecords) {
        this.courseRecords = courseRecords;
    }

    public void addCourseRecord(CourseRecord courseRecord) {
        if (courseRecord.getCourse() == null) {
            throw new IllegalArgumentException("Can't add a null course to lecturer");
        }
        this.courseRecords.add(courseRecord);
    }
}