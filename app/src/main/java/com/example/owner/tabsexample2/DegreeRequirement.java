package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/17/2017.
 */

public class DegreeRequirement extends AppCompatActivity implements AsyncResponse
{
    private final String name;
    private final String rqCode;
    private ArrayList<DegreeSubRequirement> subRequirements;
    private float creditsNeeded;
    private float creditsEarned;
    private boolean done = false;

    public DegreeRequirement(String code, String n, StudentRecord allCourses)
    {
        System.out.println("Creating new requirement\nRQ Code: " + code);
        rqCode = code;
        System.out.println("Requirement Name: " + n);
        name = n;

        //Get sub-requirements:
        String type = "subrequirements";
        DBConnector dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type, rqCode);
        String result = null;
        try {
            result = dbConnector.get();
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            subRequirements = new ArrayList();
            for(int i = 1; i < fields.length - 1; i += 2)//Create each degree requirement object.
            {
                if(getSubRequirementByCode(fields[i]) == null)
                    subRequirements.add(new DegreeSubRequirement(fields[i], fields[i + 1], allCourses));
                else
                    getSubRequirementByCode(fields[i]).addCourseByCode(fields[i + 1], allCourses);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (DegreeSubRequirement sr : subRequirements)
            sr.calculateCreditsEarned();//Now that all the sub-requirements are built, calculate the credits earned in each.

        calculateCreditsNeeded();
    }

    private float calculateCreditsNeeded()
    {
        creditsNeeded = 0;
        for (DegreeSubRequirement s : subRequirements)
        {
            creditsNeeded = s.getCreditsNeeded();
        }

        return creditsNeeded;
    }
    
    public float calculateCreditsEarned()
    {
        creditsEarned = 0;
        for (DegreeSubRequirement s : subRequirements)
        {
            creditsEarned = s.calculateCreditsEarned();
        }
        if(creditsEarned >= creditsNeeded)
            done = true;
        return creditsEarned;
    }

    public String getName()
    {
        return name;
    }

    public String getRqCode()
    {
        return rqCode;
    }

    public DegreeSubRequirement getSubRequirementByIndex(int i)
    {
        return subRequirements.get(i);
    }

    public DegreeSubRequirement getSubRequirementByCode(String code)
    {
        int match = -1;

        for(int i = 0; i < subRequirements.size(); i++)
        {
            if(subRequirements.get(i).getLnCode().matches(code))//Look for a sub-requirement with the matching LN code.
                match = i;
        }

        if(match >= 0)
            return subRequirements.get(match);
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
