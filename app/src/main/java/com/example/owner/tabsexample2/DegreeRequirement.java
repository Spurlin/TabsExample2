package com.example.owner.tabsexample2;

/**
 * Created by wamtu on 11/17/2017.
 */

public class DegreeRequirement
{
    public final String name;
    public Course[] courses;
    public final int creditsNeeded;
    private int creditsEarned;
    private boolean done = false;

    public DegreeRequirement(String n, int creds)
    {
        name = n;
        creditsNeeded = creds;
    }
    
    public int CalculateCreditsEarned()
    {
        creditsEarned = 0;
        for (Course c : courses)
        {
            if(c.status.ordinal() >= CourseStatus.C.ordinal())
                creditsEarned += c.units;
        }
        if(creditsEarned >= creditsNeeded)
            done = true;
        return creditsEarned;
    }

    public int getCreditsEarned()
    {
        return creditsEarned;
    }

    public boolean isDone()
    {
        return done;
    }
}
