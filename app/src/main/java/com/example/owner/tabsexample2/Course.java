package com.example.owner.tabsexample2;

import java.util.ArrayList;

public class Course
{

    private final String courseCode;
    private final String name;
    private final float units;
    private final CourseStatus status;
    private final String when;
    private final String description;
    private ArrayList<ClassSession> classes;

    public Course(String code, String smstr, String grade, String sId, String nm, String desc, String un, String rm, String schdl, String fname, String lname)
    {
        System.out.println("Creating new class\nCourse Code: " + code);
        courseCode = code;
        System.out.println("Semester: " + smstr);
        when = smstr;
        System.out.println("Grade: " + grade);
        switch(grade)//Convert the grade to a status value.
        {
            case "F":
                status = CourseStatus.F;
                break;
            case "D":
                status = CourseStatus.D;
                break;
            case "TD":
                status = CourseStatus.TD;
                break;
            case "C":
                status = CourseStatus.C;
                break;
            case "TC":
                status = CourseStatus.TC;
                break;
            case "B":
                status = CourseStatus.B;
                break;
            case "TB":
                status = CourseStatus.TB;
                break;
            case "A":
                status = CourseStatus.A;
                break;
            case "TA":
                status = CourseStatus.TA;
                break;
            default:
                status = CourseStatus.Enrolled;//If no grade, then they are still taking the class.
        }
        System.out.println("Name: " + nm);
        name = nm;
        System.out.println("Units: " + un);
        units = Float.parseFloat(un);
        System.out.println("Description: " + desc);
        description = desc;
        classes = new ArrayList<>();
        classes.add(new ClassSession((fname + " " + lname), smstr, rm, schdl, sId));//Only class added is the one the student took.
        System.out.println("Done creating new entry for " + courseCode + " " + name);
    }

    public String getCode()
    {
        return courseCode;
    }

    public String getName()
    {
        return name;
    }

    public float getUnits()
    {
        return units;
    }

    public CourseStatus getStatus()
    {
        return status;
    }

    public String getWhen()
    {
        return when;
    }

    public String getDescription()
    {
        return description;
    }

    public ClassSession getClassByIndex(int i)
    {
        return classes.get(i);
    }
}
