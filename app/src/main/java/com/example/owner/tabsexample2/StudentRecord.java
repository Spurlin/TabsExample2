package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/22/2017.
 */

public class StudentRecord extends AppCompatActivity implements AsyncResponse
{
    private final String studentName;
    private ArrayList<Course> allCourses;
    private DegreePlan major;
    private DegreePlan whatIf;

    public StudentRecord(String stu_id, String majorName, String fname, String lname)
    {
        studentName = fname + " " + lname;//Assemble the student's name.
        System.out.println(studentName);
        System.out.println(stu_id);
        System.out.println(majorName);

        //Collect all enrolled courses:
        String type = "allcourses";
        DBConnector dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type, stu_id);
        String result = null;
        try {
            result = dbConnector.get();
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            allCourses = new ArrayList<>();
            for(int i = 1; i < fields.length - 10; i += 11)//Create each course object using "taken course" constructor.
                allCourses.add(new Course(fields[i], fields[i+1], fields[i+2], fields[i+3],
                        fields[i+4], fields[i+5], fields[i+6], fields[i+7], fields[i+8],
                        fields[i+9], fields[i+10]));//Constructor takes arguments in same order they were sent over from database.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Build the degree plan for the student's major:
        major = new DegreePlan(majorName, this);//It's broken right now; don't try it.
    }

    public void addCourse(Course newCourse)
    {
        allCourses.add(newCourse);
    }

    public Course getCourseByIndex(int i)
    {
        return allCourses.get(i);
    }

    public Course getCourseByCode(String code)
    {
        int match = -1;

        for(int i = 0; i < allCourses.size(); i++)
        {
            if(allCourses.get(i).getCode().matches(code))//Look for a course with the matching course code.
                match = i;
        }

        if(match >= 0)
            return allCourses.get(match);
        else
            return null;
    }

    @Override
    public void processFinish(boolean res)
    {

    }
}
