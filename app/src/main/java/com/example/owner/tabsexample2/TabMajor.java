package com.example.owner.tabsexample2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Owner on 10/18/2017.
 */

public class TabMajor extends Fragment implements Serializable {

    private PopupWindow popupWindow;
    private DrawerLayout mainLayout;
    private DegreePlan majorDegreePlan;
    private int i;
    private int credsEarned;
    private int creditsLeft;
    private String mMajorName;
    private StudentRecord record;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_major, container, false);

        Intent intent = getActivity().getIntent();

        // get the students record
        record = (StudentRecord) intent.getSerializableExtra("StudentRecord");
        mMajorName = record.getMajorName(); // set the major for the student
        majorDegreePlan = record.getMajor(); // set the degree plan for the student

        // set the text in the header card to show the users current major
        TextView majorTV = (TextView) rootView.findViewById(R.id.majorName);
        majorTV.setText(mMajorName);
        majorTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // set up the view with the retrieved data
        setView(rootView, container);

        return rootView;
    }

    // this method creates the proper view based on the users
    // data that has been retrieved and stored in the student record class.
    // Sets up for the student current degree
    public void setView(View rootView, ViewGroup container) {

        // set the credits values
        credsEarned = (int) majorDegreePlan.getCreditsEarned();
        creditsLeft = (int) majorDegreePlan.getCreditsNeeded();

        // get the main layout that the view will be added to
        LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.major_main_LinLayout);


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

        int index;

        Set<String> noCredit = new HashSet<String>(Arrays.asList(new String[] {"NotTaken", "Enrolled", "TD", "D", "F"}));

        credsEarned = 0;

        // CREATING A CARD FOR REQUIREMENTS
        for (index = 0; index < majorDegreePlan.getNumberOfRequirement(); index++) {

            // create the card view for every main requirement
            // each card will contain a requirement for the students
            // degree and all of its sub requirements and the respective courses
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

            // implement the collapsible sub requirements
//            LinearLayout collapsible = new LinearLayout(container.getContext());
//            collapsible.setLayoutParams(paramsMatchWrap);
//            collapsible.setOrientation(LinearLayout.HORIZONTAL);
//
//            ImageButton collapseBtn = new ImageButton(container.getContext());
//            collapseBtn.setLayoutParams(paramsWrap);
//            collapseBtn.setImageResource(R.drawable.ic_keyboard_arrow_down_black_48dp);

            // get the next requirement
            DegreeRequirement newReq = majorDegreePlan.getRequirement(index);
            paramsMatchReqHeader.setMargins(0, 0, 0, 10);

            // requirement header
            TextView requirementTV = new TextView(container.getContext());
            requirementTV.setLayoutParams(paramsMatchReqHeader);
            requirementTV.setText(newReq.getName());
            requirementTV.setTypeface(null, Typeface.BOLD);
            requirementTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            requirementTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            requirementTV.setGravity(Gravity.CENTER_HORIZONTAL);

            // create the table for the sub requirement and courses
            TableLayout courseTable = new TableLayout(container.getContext());
            courseTable.setLayoutParams(paramsMatchTable);
            courseTable.setStretchAllColumns(true);

            // add the requirement text view to the course table (top)
            courseTable.addView(requirementTV);

            int mindex;

            // GETTING SUB REQUIREMENTS
            for (mindex = 0; mindex < newReq.getNumberOfSubReq(); mindex++) {

                // get the next sub requirement
                DegreeSubRequirement newSubReq = newReq.getSubRequirement(mindex);
                paramsMatchTableRow.setMargins(0, 15, 0, 0);

                // used as a darker line
                View outDivider = new View(container.getContext());
                outDivider.setLayoutParams(paramsLineBig);
                outDivider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                outDivider.setElevation(5);

                // sub requirement header
                TextView subRequirementTV = new TextView(container.getContext());
                paramsMatchTableRow.setMargins(0, 15, 0, 0);
                subRequirementTV.setLayoutParams(paramsMatchTableRow);
                subRequirementTV.setText(newSubReq.getLnCode());
                subRequirementTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                subRequirementTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                subRequirementTV.setGravity(Gravity.CENTER_HORIZONTAL);
                paramsMatchTableRow.setMargins(0, 0, 0, 0);

                paramsMatchTableHeader.setMargins(0, 25, 0, 0);
                // headerRow contains "Course", "Description", "Grade"
                // for labeling of columns
                TableRow headerRow = new TableRow(container.getContext());
                headerRow.setLayoutParams(paramsMatchTableRow);

                TextView courseTV = new TextView(container.getContext());
                courseTV.setLayoutParams(paramsMatchTableHeader);
                courseTV.setText("Course");
                courseTV.setTypeface(null, Typeface.BOLD);
                courseTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                courseTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                courseTV.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView descTV = new TextView(container.getContext());
                descTV.setLayoutParams(paramsMatchTableHeader);
                descTV.setText("Description");
                descTV.setTypeface(null, Typeface.BOLD);
                descTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                descTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                descTV.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView gradeTV = new TextView(container.getContext());
                gradeTV.setLayoutParams(paramsMatchTableHeader);
                gradeTV.setText("Grade");
                gradeTV.setTypeface(null, Typeface.BOLD);
                gradeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gradeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                // add the three text views to the header
                headerRow.addView(courseTV);
                headerRow.addView(descTV);
                headerRow.addView(gradeTV);

                courseTable.addView(subRequirementTV); // add the sub requirement title to the table
                courseTable.addView(outDivider); // add the dark line to the table
                courseTable.addView(headerRow); // add the column headers to the table

                // CREATING ROWS FOR EACH COURSE
                for (i = 0; i < newSubReq.getNumberOfCourses(); i++) {

                    // get the next course for the current sub requirement
                    final Course newCourse = newSubReq.getCourse(i);

                    if (!noCredit.contains(newCourse.getStatus().toString())) {
                        credsEarned += newCourse.getUnits();
                    }

                    // create a row to put the course info in
                    TableRow newRow = new TableRow(container.getContext());
                    newRow.setLayoutParams(paramsMatchTableRow);
                    if (i == 0) { newRow.setPadding(0, 10, 0, 50); } // first course
                    else { newRow.setPadding(0, 50, 0, 50); } // any other course
                    newRow.setGravity(Gravity.CENTER);

                    // used as a light line
                    View inDivider = new View(container.getContext());
                    inDivider.setLayoutParams(paramsLine);
                    inDivider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    inDivider.setElevation(5);

                    paramsWrapTableRow.weight = 1;

                    // the course code (ex: "COSC 1312")
                    TextView newCourseTV = new TextView(container.getContext());
                    newCourseTV.setLayoutParams(paramsWrapTableRow);
                    newCourseTV.setText(newCourse.getCode());
                    newCourseTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    newCourseTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    newCourseTV.setEllipsize(TextUtils.TruncateAt.END);
                    newCourseTV.setGravity(Gravity.CENTER);

                    // the course name (ex: "Software Development")
                    TextView newDescTV = new TextView(container.getContext());
                    newDescTV.setLayoutParams(paramsWrapTableRow);
                    newDescTV.setText(newCourse.getName());
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
                            final CourseStatus status = newCourse.getStatus(); // students current status for the course

                            // handles when the sessions button is clicked
                            sessionBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("<SESSIONS BUTTON CLICKED>");

                                    // if the sessions view isn't already showing
                                    if (sessionTV.getVisibility() == View.GONE) {

                                        // header for a course that has not been taken
                                        if ( status.equals(CourseStatus.NotTaken) ) {

                                            sessionHeaderTV.setText("Offered: ");
                                        }
                                        // header for a course the student is currently taking
                                        else if (status.equals(CourseStatus.Enrolled) ) {

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
                                        if(!newCourse.gotSessions())
                                            newCourse.retrieveSessionsFromServer(); //Get the sessions for this class if they have not been gotten already.

                                        if (newCourse.getNumberOfSessions() > 0) {

                                            String sessionText = "";

                                            // for all sessions
                                            for(int i = 0; i < newCourse.getNumberOfSessions(); i++)

                                                // display if there is a professor field
                                                if (newCourse.getSession(i).getInstructor() != null) {
                                                    sessionText += "Professor: " + newCourse.getSession(i).getInstructor()
                                                            + "\nRoom: " + newCourse.getSession(i).getRoom()
                                                            + "\nSchedule: " + newCourse.getSession(i).getSchedule()
                                                            + "\nSemester: " + newCourse.getSession(i).getSemester() + "\n\n";
                                                } else {
                                                    sessionText += "Room: " + newCourse.getSession(i).getRoom()
                                                            + "\nSchedule: " + newCourse.getSession(i).getSchedule()
                                                            + "\nSemester: " + newCourse.getSession(i).getSemester() + "\n\n";
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
                            courseCode.setText(newCourse.getCode());
                            courseName.setText(newCourse.getName());
                            courseUnits.setText(String.valueOf(newCourse.getUnits()));
                            courseWhen.setText(newCourse.getWhen());
                            courseDesc.setText(newCourse.getDescription());

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
                    newGradeTV.setText(newCourse.getStatus().toString());
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
                    if ( (i != newSubReq.getNumberOfCourses() - 1) ) {
                        courseTable.addView(inDivider);
                    }
                } // end of adding courses

                // set the ratio for the header based on the credits earned
                TextView ratio = (TextView) rootView.findViewById(R.id.ratio);
                ratio.setText(credsEarned + "/" + 120 + " Credits");
                ProgressBar mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                mProgressBar.setMax(120);
                mProgressBar.setProgress(credsEarned);
            } // end of adding sub requirements

            insideLinLayout.addView(courseTable); // add the filled in table to the inside layout
            relativeLayout.addView(insideLinLayout); // add the layout in the relative layout
            newCard.addView(relativeLayout); // add the relative layout to the card view
            mLinearLayout.addView(newCard); // add the finished card view  to the main layout
        } // end of requirements
    } // end of setView
} // end of TabMajor
