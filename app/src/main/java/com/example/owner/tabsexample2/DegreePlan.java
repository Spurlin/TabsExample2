package com.example.owner.tabsexample2;

/**
 * Created by wamtu on 11/17/2017.
 */

public class DegreePlan
{
    public final String name;
    public DegreeRequirement[] requirements;
    public final int creditsNeeded;
    public int creditsEarned;

    public DegreePlan(String n, int creds)
    {
        name = n;
        creditsNeeded = creds;
    }

    public int CalculateCreditsEarned()
    {
        creditsEarned = 0;
        for (DegreeRequirement r : requirements)
        {
            creditsEarned += r.CalculateCreditsEarned();
        }
        return creditsEarned;
    }
}
