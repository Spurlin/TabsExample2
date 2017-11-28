package com.example.owner.tabsexample2;

import java.util.ArrayList;

/**
 * Created by wamtu on 11/22/2017.
 */

public class StudentRecord
{
    String studentName;
    ArrayList<Course> allCourses;
    DegreePlan major;
    DegreePlan whatIf;

    public StudentRecord(String stu_id, String majorName, String fname, String lname)
    {
        studentName = fname + " " + lname;
        System.out.println(studentName);
        System.out.println(stu_id);
        System.out.println(majorName);
        //Use stu_id to pull enrolled courses, including class.
        /*for(int i = 0; i < enrolledTuples.length; i++)
            allCourses.add(new Course(...));
        */
    }
}
