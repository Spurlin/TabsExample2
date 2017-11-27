package com.example.owner.tabsexample2;

/**
 * Created by wamtu on 11/17/2017.
 */

public class ClassSession
{
    public final String instructorName;
    public final String semester;
    public final String room;
    public final String scheduleDay;
    public final String scheduleTime;

    public ClassSession(String instr, String sem, String r, String schDay, String schTime)
    {
        instructorName = instr;
        semester = sem;
        room = r;
        scheduleDay = schDay;
        scheduleTime = schTime;
    }
}
