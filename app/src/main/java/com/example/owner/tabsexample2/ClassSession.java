package com.example.owner.tabsexample2;

import java.io.Serializable;

/**
 * Created by wamtu on 11/17/2017.
 */

public class ClassSession implements Serializable
{
    private final String instructorName;
    private final String semester;
    private final String room;
    private final String schedule;
    private final String sessionID;


    public ClassSession(String instr, String smstr, String rm, String schdl, String sId)
    {
        System.out.println("Instructor: " + instr);
        instructorName = instr;
        System.out.println("Semester: " + smstr);
        semester = smstr;
        System.out.println("Room: " + rm);
        room = rm;
        System.out.println("Schedule: " + schdl);
        schedule = schdl;
        System.out.println("Session ID: " + sId);
        sessionID = sId;
    }

    public String getInstructor()
    {
        return instructorName;
    }

    public String getSemester()
    {
        return semester;
    }

    public String getRoom()
    {
        return room;
    }

    public String getSchedule()
    {
        return schedule;
    }

    public String getSession()
    {
        return sessionID;
    }
}
