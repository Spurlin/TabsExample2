package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Course extends AppCompatActivity implements AsyncResponse, Serializable
{

    private final String courseCode;
    private final String name;
    private final float units;
    private final CourseStatus status;
    private final String when;
    private final String description;
    private ArrayList<ClassSession> classes = null;

    public Course(String code, String smstr, String grade, String sId, String nm, String desc, String un, String rm, String schdl, String fname, String lname)
    {
        System.out.println("Creating new course\nCourse Code: " + code);
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

    public Course(String code, String nm, String desc, String spec, String un)
    {
        System.out.println("Creating new class\nCourse Code: " + code);
        courseCode = code;
        System.out.println("Description: " + desc);
        description = desc;
        System.out.println("Name: " + nm);
        name = nm;
        System.out.println("Units: " + un);
        units = Float.parseFloat(un);
        System.out.println("Specification: " + spec);
        when = spec;

        status = CourseStatus.NotTaken;//Since it's being initialized this way, it hasn't been taken.
        System.out.println("Done creating new entry for " + courseCode + " " + name);
    }

    public void retrieveClassesFromServer()
    {
        if(isTaken())
        {
            System.out.println(courseCode + " has been taken; classes have not been retrieved");
            return;
        }

        System.out.println("Retrieving classes for " + courseCode);
        String type = "classes";
        DBConnector dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type, courseCode);
        String result = null;
        try {
            result = dbConnector.get();
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            classes = new ArrayList<>();
            for(int i = 1; i < fields.length - 5; i += 6)//Create each class session object.
                classes.add(new ClassSession((fields[i] + " " + fields[i+1]), fields[i+2], fields[i+3],
                        fields[i+4], fields[i+5]));//Constructor takes arguments in same order they were sent over from database.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Done retrieving classes for " + courseCode);
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

    public ClassSession getClass(int i)
    {
        if(i >= 0 && i < classes.size())
            return classes.get(i);
        else
            return null;
    }

    public ClassSession getClass(String smstr, String sId)
    {
        int match = -1;

        for(int i = 0; i < classes.size(); i++)
        {
            if(classes.get(i).getSemester().matches(smstr) && classes.get(i).getSession().equals(sId))//Look for a class with the matching semester and session ID.
                match = i;
        }

        if(match >= 0)
            return classes.get(match);
        else
            return null;
    }

    public boolean isTaken()
    {
        if(status != CourseStatus.NotTaken)
            return true;
        else
            return false;
    }

    @Override
    public void processFinish(boolean res)
    {
    }
}
