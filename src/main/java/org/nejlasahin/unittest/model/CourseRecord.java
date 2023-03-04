package org.nejlasahin.unittest.model;

public class CourseRecord {

    private Course course;
    private Lecturer lecturer;

    public CourseRecord(Course course, Lecturer lecturer) {
        this.course = course;
        this.lecturer = lecturer;
    }

    public CourseRecord() {

    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}