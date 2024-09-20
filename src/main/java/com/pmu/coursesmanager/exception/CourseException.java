package com.pmu.coursesmanager.exception;


public class CourseException extends RuntimeException {


    public CourseException(String message, Throwable e) {
        super(message,e);
    }

    public CourseException(String message) {
    }
}