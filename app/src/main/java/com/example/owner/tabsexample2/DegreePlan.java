package com.example.owner.tabsexample2;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by wamtu on 11/17/2017.
 */

public class DegreePlan extends AppCompatActivity implements AsyncResponse
{
    private final String name;
    private ArrayList<DegreeRequirement> requirements;
    private float creditsNeeded;
    private float creditsEarned;

    public DegreePlan(String n, StudentRecord allCourses)
    {
        System.out.println("Creating degree plan: " + n);
        name = n;

        //Get requirements:
        String type = "requirements";
        DBConnector dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type, name);
        String result = null;
        try {
            result = dbConnector.get();
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            requirements = new ArrayList();
            for(int i = 1; i < fields.length - 1; i += 2)//Create each degree requirement object.
                requirements.add(new DegreeRequirement(fields[i], fields[i+1], allCourses));//Constructor takes arguments in same order they were sent over from database.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        calculateCreditsEarned();
    }

    private float calculateCreditsEarned()
    {
        creditsEarned = 0;
        for (DegreeRequirement r : requirements)
        {
            creditsEarned += r.calculateCreditsEarned();
        }
        return creditsEarned;
    }

    public String getName()
    {
        return name;
    }

    public DegreeRequirement getRequirementByIndex(int i)
    {
        return requirements.get(i);
    }

    public DegreeRequirement getRequirementByCode(String code)
    {
        int match = -1;

        for(int i = 0; i < requirements.size(); i++)
        {
            if(requirements.get(i).getRqCode().matches(code))//Look for a requirement with the matching RQ code.
                match = i;
        }

        if(match >= 0)
            return requirements.get(match);
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

    @Override
    public void processFinish(boolean res)
    {

    }
}
