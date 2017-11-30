package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/29/2017.
 */

public class DegreeSubRequirement extends AppCompatActivity implements AsyncResponse
{
    private final String lnCode;
    private ArrayList<Course> courses;
    private float creditsNeeded;
    private float creditsEarned;
    private boolean done = false;

    public DegreeSubRequirement(String ln, String course, StudentRecord allCourses)
    {
        System.out.println("Creating new sub-requirement\nLN Code: " + ln);
        lnCode = ln;
        creditsNeeded = 0;//Gets added to when we add new courses.
        courses = new ArrayList<>();
        addCourseByCode(course, allCourses);
    }

    public void addCourseByCode(String code, StudentRecord allCourses)
    {
        if(allCourses.getCourseByCode(code) != null)//Check if this course has already been pulled from the database.
        {
            System.out.println("Adding " + code + " to " + lnCode);
            Course newCourse = allCourses.getCourseByCode(code);
            courses.add(newCourse);//Give this sub-requirement a pointer to the existing course.
            creditsNeeded += newCourse.getUnits();//Add this course's units to the credits needed.
        }
        else//If we don't already have this course, pull it from the database.
        {
            //Get the missing course:
            String type = "singlecourse";
            DBConnector dbConnector = new DBConnector(this);
            dbConnector.delegate = this;
            dbConnector.execute(type, code);
            String result = null;
            try {
                result = dbConnector.get();
                String[] fields = result.split("~");
                System.out.println("Fields: " + fields.length);
                if(fields.length >= 5)
                    allCourses.addCourse(new Course(code, fields[1], fields[2], fields[3], fields[4]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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

    public Course getCourseByIndex(int i)
    {
        return courses.get(i);
    }

    public Course getCourseByCode(String code)
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

    @Override
    public void processFinish(boolean res)
    {

    }
}
