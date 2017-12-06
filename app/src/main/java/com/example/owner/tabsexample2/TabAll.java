package com.example.owner.tabsexample2;

/**
 * Created by Owner on 10/18/2017.
 */

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class TabAll extends Fragment implements Serializable {

    private StudentRecord record;
    private PopupWindow popupWindow;
    private DrawerLayout mainLayout;
    private int credsEarned = 0;
    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_all, container, false);
        super.onCreate(savedInstanceState);

        // set up the view with the retrieved data
        setView(rootView, container);

        return rootView;
    }

    // this method creates the proper view based on the users
    // data that has been retrieved and stored in the student record class.
    // Sets up for all courses ( only showing the courses the student has taken
    // in their college career
    public void setView(View rootView, ViewGroup container) {

        Intent intent = getActivity().getIntent();

        final StudentRecord record = (StudentRecord) intent.getSerializableExtra("StudentRecord");

        // get the main layout that the view will be added to
        LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.all_main_LinLayout);


        // Defined Layout Parameters that are used
        // firs layoutparams is for the width, second for the height
        LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT ) ;

        LinearLayout.LayoutParams paramsWrapCARD = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT ) ;

        paramsWrapCARD.setMargins(0, 50, 0, 50);

        LinearLayout.LayoutParams paramsMatchWrap = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT ) ;

        TableLayout.LayoutParams paramsMatchTable = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT ) ;

        LinearLayout.LayoutParams paramsMatch = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT ) ;

        TableRow.LayoutParams paramsMatchTableRow = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f ) ;

        TableRow.LayoutParams paramsMatchTableHeader = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f ) ;

        LinearLayout.LayoutParams paramsMatchReqHeader = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT ) ;

        TableRow.LayoutParams paramsWrapTableRow = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f ) ;

        TableRow.LayoutParams paramsLine = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                2 , 1f);

        TableRow.LayoutParams paramsLineBig = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                4 , 1f);

        // create the card view
        // only one is needed since all tab
        // will not show requirements
        CardView newCard = new CardView(container.getContext());
        newCard.setLayoutParams(paramsWrapCARD);

        newCard.setRadius(50);
        newCard.setContentPadding(10, 10 , 10 , 10 );
        newCard.setMaxCardElevation(20);
        newCard.setElevation(15);

        // relative layout inside the card
        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
        relativeLayout.setLayoutParams(paramsWrap);

        // linear layout inside the card that will hold the
        // headers and table layouts
        LinearLayout insideLinLayout = new LinearLayout((container.getContext()));
        insideLinLayout.setLayoutParams(paramsMatchWrap);
        insideLinLayout.setOrientation(LinearLayout.VERTICAL);

        // create the table for the taken courses
        TableLayout courseTable = new TableLayout(container.getContext());
        courseTable.setLayoutParams(paramsMatchTable);
        courseTable.setStretchAllColumns(true);

        // headerRow contains "Course", "Description", "Grade"
        // for labeling of columns
        TableRow headerRow = new TableRow(container.getContext());
        headerRow.setLayoutParams(paramsMatchTableRow);
        headerRow.setPadding(0, 0, 0, 0);

        TextView courseTV = new TextView(container.getContext());
        courseTV.setLayoutParams(paramsMatchTableRow);
        courseTV.setText("Course");
        courseTV.setTypeface(null, Typeface.BOLD);
        courseTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        courseTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        courseTV.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView descTV = new TextView(container.getContext());
        descTV.setLayoutParams(paramsMatchTableRow);
        descTV.setText("Description");
        descTV.setTypeface(null, Typeface.BOLD);
        descTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        descTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        descTV.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView gradeTV = new TextView(container.getContext());
        gradeTV.setLayoutParams(paramsMatchTableRow);
        gradeTV.setText("Grade");
        gradeTV.setTypeface(null, Typeface.BOLD);
        gradeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        gradeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        descTV.setGravity(Gravity.CENTER_HORIZONTAL);

        // add the three text views to the header
        headerRow.addView(courseTV);
        headerRow.addView(descTV);
        headerRow.addView(gradeTV);
        courseTable.addView(headerRow); // add the column headers to the table

        // used to know how many cards have been added to the view
        int addedIndicator = 1;

        credsEarned = 0;

        Set<String> noCredit = new HashSet<String>(Arrays.asList(new String[] {"Enrolled", "TD", "D", "F"}));

        for (i = 0; i < record.getNumberOfCourses(); i++) {

            // only display the courses the student has taken
            // in their college career
            if (record.getCourseStatus(i) != "NotTaken") {

                // used as a light line
                View divider = new View(container.getContext());
                divider.setLayoutParams(paramsLine);
                divider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                divider.setElevation(5);

                if (addedIndicator >= 3) { courseTable.addView(divider); }

                if (!noCredit.contains(record.getCourseStatus(i))) {
                    credsEarned += record.getCourseUnits(i);
                }

                // create a row to put the course info in
                TableRow newRow = new TableRow(container.getContext());
                newRow.setLayoutParams(paramsMatchTableRow);
                newRow.setPadding(0, 50, 0, 50);
                newRow.setGravity(Gravity.CENTER);

                paramsWrapTableRow.weight = 1;

                TextView newCourseTV = new TextView(container.getContext());
                newCourseTV.setLayoutParams(paramsWrapTableRow);
                newCourseTV.setText(record.getCourseCode(i));
                newCourseTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                newCourseTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                newCourseTV.setEllipsize(TextUtils.TruncateAt.END);
                newCourseTV.setGravity(Gravity.CENTER);

                TextView newDescTV = new TextView(container.getContext());
                newDescTV.setLayoutParams(paramsWrapTableRow);
                newDescTV.setText(record.getCourseName(i));
                newDescTV.setTextColor(getResources().getColor(R.color.linkColor));
                newDescTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                newDescTV.setGravity(Gravity.CENTER);
                newDescTV.setTag(i);
                newDescTV.setClickable(true);
                // handles when the user clicks on the course name;
                // opens a pop up window and shows the respective info
                newDescTV.setOnClickListener(new View.OnClickListener() {

                    //this is where the popup is
                    @Override
                    public void onClick(View v) {

                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                        // the main layout that the pop up window will be over
                        mainLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                        // create the view based on the defined popup window layout
                        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow, null);

                        // get the tag of the course that was clicked on
                        // used so that the proper info can be presented
                        final int index = (int) v.getTag();
                        System.out.println("<< " + index + " >>");
                        System.out.println("<< " + record.getCourse(index).getName() + " >>");
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = true;
                        popupWindow = new PopupWindow(popupView, width, height, focusable);

                        // fill the info fields for the course info
                        TextView courseCode = popupView.findViewById(R.id.courseCode); // ex: COSC 1213
                        TextView courseName = popupView.findViewById(R.id.courseName); // ex: "Software Development"
                        TextView courseUnits = popupView.findViewById(R.id.courseUnits); // ex: 3
                        TextView courseWhen = popupView.findViewById(R.id.courseWhen); // ex: Fall 2017
                        TextView courseDesc = popupView.findViewById(R.id.courseDesc); // ex: "This course is about.."
                        final Button sessionBtn = popupView.findViewById(R.id.sessionButton); // button to reveal the sessions for the course

                        // Example:
                        // Professor: John Doe
                        // Room: RBN 3038
                        // Schedule: M/W/F 2-3
                        // Semester: Fall 2017
                        final TextView sessionTV = popupView.findViewById(R.id.sessionTV);

                        // Displays according to if the user has taken the course, is taking the course, or
                        // needs to take it
                        final TextView sessionHeaderTV = popupView.findViewById(R.id.sessionHeaderTV);
                        final String status = record.getCourseStatus(index); // students current status for the course

                        // handles when the sessions button is clicked
                        sessionBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("<SESSIONS BUTTON CLICKED>");

                                // if the sessions view isn't already showing
                                if (sessionTV.getVisibility() == View.GONE) {

                                    // header for a course that has not been taken
                                    if ( status.equalsIgnoreCase("NotTaken") ) {

                                        sessionHeaderTV.setText("Offered: ");
                                    }
                                    // header for a course the student is currently taking
                                    else if (status.equalsIgnoreCase("Enrolled") ) {

                                        sessionHeaderTV.setText("Currently Enrolled in Session: ");
                                    }
                                    // header for a course already completed
                                    else {
                                        sessionHeaderTV.setText("Session taken: ");
                                    }

                                    // show the session info with header
                                    sessionHeaderTV.setVisibility(View.VISIBLE);
                                    sessionTV.setVisibility(View.VISIBLE);

                                    // if course has sessions in database
                                    if(!record.getCourse(index).gotSessions()) {
                                        record.getCourse(index).retrieveSessionsFromServer();//Get the sessions for this class if they have not been gotten already.
                                    }

                                    if (record.getCourse(index).getNumberOfSessions() > 0) {

                                        String sessionText = "";

                                        for(int i = 0; i < record.getCourse(index).getNumberOfSessions(); i++)

                                            if (record.getCourse(index).getSession(i).getInstructor() != null) {
                                                sessionText += "Professor: " + record.getCourse(index).getSession(i).getInstructor()
                                                        + "\nRoom: " + record.getCourse(index).getSession(i).getRoom()
                                                        + "\nSchedule: " + record.getCourse(index).getSession(i).getSchedule()
                                                        + "\nSemester: " + record.getCourse(index).getSession(i).getSemester() + "\n\n";
                                            } else {
                                                sessionText += "Room: " + record.getCourse(index).getSession(i).getRoom()
                                                        + "\nSchedule: " + record.getCourse(index).getSession(i).getSchedule()
                                                        + "\nSemester: " + record.getCourse(index).getSession(i).getSemester() + "\n\n";
                                            }
                                        sessionTV.setText(sessionText);

                                    }
                                    // the course doesn't have any info in database
                                    // for its sessions
                                    else {
                                        sessionTV.setText("Not Available");
                                    }

                                    // set the button to say close sessions
                                    sessionBtn.setText("Close Sessions");

                                }
                                // if the sessions view is already being shown
                                else {
                                    // close the sessions view and reset button to say sessions
                                    sessionHeaderTV.setVisibility(View.GONE);
                                    sessionTV.setVisibility(View.GONE);
                                    sessionBtn.setText("Sessions");
                                }
                            }
                        }); // end of sessionsBtn click handler

                        // display the info for the course in the pop up window
                        courseCode.setText(record.getCourseCode(index));
                        courseName.setText(record.getCourseName(index));
                        courseUnits.setText(String.valueOf(record.getCourseUnits(index)));
                        courseWhen.setText(record.getCourseWhen(index));
                        courseDesc.setText(record.getCourseDesc(index));

                        // show in the center of the screen
                        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                        // greys the layout that the pop up window is on top of
                        mainLayout.getForeground().setAlpha(220);

                        // handles when the pop up window is closed via touch outside of window or
                        // via back button
                        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                mainLayout.getForeground().setAlpha(0);
                                popupWindow.dismiss();
                            }
                        });

                    }
                }); // end of descTV click handler

                // displays the students grade for the course
                TextView newGradeTV = new TextView(container.getContext());
                newGradeTV.setLayoutParams(paramsWrapTableRow);
                newGradeTV.setText(record.getCourseStatus(i));
                newGradeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                newGradeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                newGradeTV.setGravity(Gravity.CENTER);

                // add the course, course name, and grade to the row,
                // and add the row to the table
                newRow.addView(newCourseTV);
                newRow.addView(newDescTV);
                newRow.addView(newGradeTV);
                courseTable.addView(newRow);

                // if the current course is not the last one being added,
                // insert a light line for readability
                if (addedIndicator == 1) { courseTable.addView(divider); }
                addedIndicator++;
            }
        } // end of adding courses

        insideLinLayout.addView(courseTable); // add the filled in table to the inside layout
        relativeLayout.addView(insideLinLayout); // add the layout in the relative layout
        newCard.addView(relativeLayout); // add the relative layout to the card view
        mLinearLayout.addView(newCard); // add the finished card view  to the main layout

        // set the ratio for the header based on the credits earned
        TextView ratio = (TextView) rootView.findViewById(R.id.ratio);
        ratio.setText(credsEarned + "/" + 120 + " Credits");
        ProgressBar mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mProgressBar.setMax(120);
        mProgressBar.setProgress(credsEarned);

    } // end of setView

} // end of TabAll