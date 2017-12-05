package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/22/2017.
 */

public class StudentRecord extends AppCompatActivity implements AsyncResponse, Serializable
{
    private String studentName;
    private ArrayList<Course> allCourses;
    private DegreePlan major;
    private DegreePlan whatIf = null;
    private String[] degreeNames;
    private String mDegreeName;

    public StudentRecord(String stu_id, String majorName, String fname, String lname)
    {
        studentName = fname + " " + lname;//Assemble the student's name.
        System.out.println(studentName);
        System.out.println(stu_id);
        System.out.println(majorName);

        mDegreeName = majorName;

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

        //Pull all degree names to use for selecting a "What-If":
        type = "degreenames";
        dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type);//We don't need to send anything to the server this time; all we want is every degree name.
        result = null;
        try {
            result = dbConnector.get();
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            degreeNames = fields;
            degreeNames[0] = "Choose one...";//Replace "connectsuccess" with a blank option.

            /*degreeNames = new String[fields.length - 1];
            for(int i = 1; i < fields.length; i++)
                degreeNames[i - 1] = fields[i];//We need to discard the first one, as it's just going to be "connectsuccess."*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Test the class-loading function:
        //getCourse(0).retrieveClassesFromServer();//First class will be a taken one; should do nothing.
        //getCourse(allCourses.size() - 2).retrieveClassesFromServer();//Last class will be a non-taken one; should load classes.
    }

    public String getStudentName() { return studentName; }

    public void addCourse(Course newCourse)
    {
        allCourses.add(newCourse);
    }

    public Course getCourse(int i)
    {
        return allCourses.get(i);
    }

    public int getNumberOfCourses() {
        return allCourses.size();
    }

    public Course getCourse(String code)
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

    public DegreePlan getMajor()
    {
        return major;
    }

    public DegreePlan getWhatIf()
    {
        return whatIf;
    }

    //Both of these replace the current "What-If" with a new one:
    public DegreePlan setWhatIf(int i)
    {
        //Choose degree name using an index.
        if(i >= 0 && i < degreeNames.length)
        {
            whatIf = new DegreePlan(degreeNames[i], this);
            return whatIf;
        }
        else
            return null;//Do nothing if an invalid index.
    }

    public DegreePlan setWhatIf(String name)
    {
        int match = -1;
        for(int i = 0; i < degreeNames.length; i++)
        {
            if(degreeNames[i].equals(name))//Make sure the given degree name exists.
                match = i;
        }

        if(match >= 0)//If the degree name exists, build that degree.
        {
            whatIf = new DegreePlan(degreeNames[match], this);
            return whatIf;
        }
        else//Otherwise, do nothing.
            return null;
    }

    public String getDegreeName(int i)
    {
        if(i >= 0 && i < degreeNames.length)
            return degreeNames[i];
        else
            return null;
    }

    @Override
    public void processFinish(boolean res)
    {

    }

    public String getCourseName(int i) { return allCourses.get(i).getName(); }

    public String getCourseDesc(int i) { return allCourses.get(i).getDescription(); }

    public String getCourseStatus(int i) { return allCourses.get(i).getStatus().toString(); }

    public String getCourseCode(int i) { return allCourses.get(i).getCode(); }

    public float getCourseUnits(int i) { return allCourses.get(i).getUnits(); }

    public String getCourseWhen(int i) { return allCourses.get(i).getWhen(); }

    public String[] getDegreeNames() { return degreeNames; }

    public float getCreditsEarned() { return major.getCreditsEarned(); }

    public float getCreditsNeeded() { return major.getCreditsNeeded(); }

    public String getMajorName() { return mDegreeName; }

    public DegreePlan getWhatIfDegreePlan() { return whatIf; }
}
