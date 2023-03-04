package org.nejlasahin.unittest.model;

import java.time.LocalDate;

public class Course {

    private String code;
    private String name;
    private String description;
    private CourseType courseType;
    private LocalDate date;

    public enum CourseType {
        MANDATORY,
        ELECTIVE
    }

    public Course() {

    }

    public Course(String code, CourseType courseType) {
        this.code = code;
        this.courseType = courseType;
    }

    public Course(String code, LocalDate date) {
        this.code = code;
        this.date = date;
    }

    public Course(String code) {
        this.code = code;
    }

    public static Course createNewCourse(String code) {
        return new Course(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}