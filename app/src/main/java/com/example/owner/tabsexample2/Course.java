package com.example.owner.tabsexample2;

public class Course
{

    public final String courseCode;
    public final String name;
    public final int units;
    public final CourseStatus status;
    public final String when;
    public final String description;
    public ClassSession[] sessions;

    public Course(String code, String n, int u, CourseStatus stat, String w, String desc)
    {
        courseCode = code;
        name = n;
        units = u;
        status = stat;
        when = w;
        description = desc;
    }
}
