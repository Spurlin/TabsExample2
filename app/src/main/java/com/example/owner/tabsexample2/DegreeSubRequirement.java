package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/29/2017.
 */

public class DegreeSubRequirement
{
    private final String lnCode;
    private ArrayList<Course> courses;
    private float creditsNeeded;
    private float creditsEarned;
    private boolean done = false;

    public DegreeSubRequirement(String ln, String course, String nm, String desc, String spec, String un, StudentRecord allCourses)
    {
        System.out.println("Creating new sub-requirement\nLN Code: " + ln);
        lnCode = ln;
        creditsNeeded = 0;//Gets added to when we add new courses.
        courses = new ArrayList<>();
        addCourse(course, nm, desc, spec, un, allCourses);
    }

    public void addCourse(String code, String nm, String desc, String spec, String un, StudentRecord allCourses)
    {
        if(allCourses.getCourse(code) != null)//Check if this course has already been pulled from the database.
        {
            System.out.println("Adding " + code + " to " + lnCode);
            Course newCourse = allCourses.getCourse(code);
            courses.add(newCourse);//Give this sub-requirement a pointer to the existing course.
            creditsNeeded += newCourse.getUnits();//Add this course's units to the credits needed.
        }
        else//If we don't already have this course, create a new object based off given data:
        {
            Course newCourse = new Course(code, nm, desc, spec, un);
            System.out.println("Adding " + code + " to " + lnCode);
            courses.add(newCourse);
            allCourses.addCourse(newCourse);
            creditsNeeded += newCourse.getUnits();
        }

    }

    public String getLnCode()
    {
        return lnCode;
    }

    public float calculateCreditsEarned()
    {
        creditsEarned = 0;
        for (Course c : courses)
        {
            if(c.getStatus().ordinal() >= CourseStatus.C.ordinal())
                creditsEarned += c.getUnits();
        }
        if(creditsEarned >= creditsNeeded)
            done = true;
        return creditsEarned;
    }

    public Course getCourse(int i)
    {
        if(i >= 0 && i < courses.size())
            return courses.get(i);
        else
            return null;
    }

    public Course getCourse(String code)
    {
        int match = -1;

        for(int i = 0; i < courses.size(); i++)
        {
            if(courses.get(i).getCode().matches(code))//Look for a course with the matching course code.
                match = i;
        }

        if(match >= 0)
            return courses.get(match);
        else
            return null;
    }

    public float getCreditsNeeded()
    {
        return creditsNeeded;
    }

    public float getCreditsEarned()
    {
        return creditsEarned;
    }

    public boolean isDone()
    {
        return done;
    }
}
