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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by j_spu on 12/2/2017.
 */

public class TabWhatIf  extends Fragment {

    private Spinner spMajor;

    //Possible Majors
    String degreeNames[];
    DegreePlan whatIfDegreePlan;
    ArrayAdapter<String> adapterMajor;
    String sMajor;
    private PopupWindow popupWindow;
    private DrawerLayout mainLayout;
    private int i;
    private int credsEarned;
    private int creditsLeft;
    private String mMajorName;
    private StudentRecord record;
    private final String DEFAULT = "Choose one...";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.what_if, container, false);

        Intent intent = getActivity().getIntent();

        record = (StudentRecord) intent.getSerializableExtra("StudentRecord");
        degreeNames = record.getDegreeNames();
        sMajor = degreeNames[0];

        spMajor = (Spinner) rootView.findViewById(R.id.whatIf_Spinner);

        adapterMajor = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, degreeNames);

        adapterMajor.setDropDownViewResource(R.layout.spinner_dropdown_items);

        spMajor.setAdapter(adapterMajor);

        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sMajor = adapter.getItemAtPosition(position).toString();

                    // Showing selected spinner item
//                    Toast.makeText(getActivity().getApplicationContext(),
//                            "Selected Major : " + sMajor, Toast.LENGTH_SHORT).show();

                    whatIfDegreePlan = record.setWhatIf(sMajor);
                System.out.println("\n \n \n <WHAT IF DEGREE PLAN: " + sMajor + ">\n \n \n ");
                if (!sMajor.equalsIgnoreCase(DEFAULT)) { setView(rootView, container); }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        if (!sMajor.equalsIgnoreCase(DEFAULT)) { setView(rootView, container); }

            return rootView;
    }

    public void setView(View rootView, ViewGroup container) {

        credsEarned = (int) whatIfDegreePlan.getCreditsEarned();
        creditsLeft = (int) whatIfDegreePlan.getCreditsNeeded();

        TextView ratio = (TextView) rootView.findViewById(R.id.ratio);
        ratio.setText(credsEarned + "/" + 120 + " Credits");
        ProgressBar mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mProgressBar.setMax(120);
        mProgressBar.setProgress(credsEarned);

        LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.whatIf_main_LinLayout);

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

        TableRow.LayoutParams paramsWrapTableRow = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f ) ;

        TableRow.LayoutParams paramsLine = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                2 , 1f);

        int index;

        for (index = 0; index < whatIfDegreePlan.getNumberOfRequirement(); index++) {

            CardView newCard = new CardView(container.getContext());
            newCard.setLayoutParams(paramsWrapCARD);

            newCard.setRadius(50);
            newCard.setContentPadding(10, 10 , 10 , 10 );
            newCard.setMaxCardElevation(20);
            newCard.setElevation(15);

            RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
            relativeLayout.setLayoutParams(paramsWrap);

            LinearLayout insideLinLayout = new LinearLayout((container.getContext()));
            insideLinLayout.setLayoutParams(paramsMatchWrap);
            insideLinLayout.setOrientation(LinearLayout.VERTICAL);

            DegreeRequirement newReq = whatIfDegreePlan.getRequirement(index);
            paramsMatchTableRow.setMargins(0, 0, 0, 40);

            // requirement header
            TextView requirementTV = new TextView(container.getContext());
            requirementTV.setLayoutParams(paramsMatchTableRow);
            requirementTV.setText(newReq.getName());
            requirementTV.setTypeface(null, Typeface.BOLD);
            requirementTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            requirementTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            requirementTV.setGravity(Gravity.CENTER_HORIZONTAL);
            paramsMatchTableRow.setMargins(0, 0, 0, 0);

            TableLayout courseTable = new TableLayout(container.getContext());
            courseTable.setLayoutParams(paramsMatchTable);
            courseTable.setStretchAllColumns(true);

            courseTable.addView(requirementTV);

            int mindex;

            for (mindex = 0; mindex < newReq.getNumberOfSubReq(); mindex++) {

                DegreeSubRequirement newSubReq = newReq.getSubRequirement(mindex);
                paramsMatchTableRow.setMargins(0, 15, 0, 0);

                // sub requirement header
                TextView subRequirementTV = new TextView(container.getContext());
                paramsMatchTableRow.setMargins(0, 15, 0, 0);
                subRequirementTV.setLayoutParams(paramsMatchTableRow);
                subRequirementTV.setText(newSubReq.getLnCode());
                subRequirementTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                subRequirementTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                subRequirementTV.setGravity(Gravity.CENTER_HORIZONTAL);
                paramsMatchTableRow.setMargins(0, 0, 0, 0);

                TableRow headerRow = new TableRow(container.getContext());
                paramsMatchTableRow.setMargins(0, 10, 0, 0);
                headerRow.setLayoutParams(paramsMatchTableRow);
                headerRow.setPadding(0, 0, 0, 0);
                paramsMatchTableRow.setMargins(0, 0, 0, 0);
//            headerRow.setGravity(Gravity.CENTER);

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

                headerRow.addView(courseTV);
                headerRow.addView(descTV);
                headerRow.addView(gradeTV);

                courseTable.addView(subRequirementTV);
                courseTable.addView(headerRow);

                for (i = 0; i < newSubReq.getNumberOfCourses(); i++) {

                    final Course newCourse = newSubReq.getCourse(i);

                    TableRow newRow = new TableRow(container.getContext());
                    newRow.setLayoutParams(paramsMatchTableRow);
                    newRow.setPadding(0, 50, 0, 50);
                    newRow.setGravity(Gravity.CENTER);

                    View divider = new View(container.getContext());
                    divider.setLayoutParams(paramsLine);
                    divider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    divider.setElevation(5);

                    paramsWrapTableRow.weight = 1;

                    TextView newCourseTV = new TextView(container.getContext());
                    newCourseTV.setLayoutParams(paramsWrapTableRow);
                    newCourseTV.setText(newCourse.getCode());
                    newCourseTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    newCourseTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    newCourseTV.setEllipsize(TextUtils.TruncateAt.END);
                    newCourseTV.setGravity(Gravity.CENTER);

                    TextView newDescTV = new TextView(container.getContext());
                    newDescTV.setLayoutParams(paramsWrapTableRow);
                    newDescTV.setText(newCourse.getName());
                    newDescTV.setTextColor(getResources().getColor(R.color.linkColor));
                    newDescTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    newDescTV.setGravity(Gravity.CENTER);
                    newDescTV.setTag(i);
                    newDescTV.setClickable(true);
                    newDescTV.setOnClickListener(new View.OnClickListener() {

                        //this is where the popup is
                        @Override
                        public void onClick(View v) {
//        startActivity(new Intent(MainActivity.this,Pop.class));

                            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                            mainLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                            View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow, null);

                            int index = (int) v.getTag();
                            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                            boolean focusable = true;
                            popupWindow = new PopupWindow(popupView, width, height, focusable);

                            TextView courseCode = popupView.findViewById(R.id.courseCode);
                            TextView courseName = popupView.findViewById(R.id.courseName);
                            TextView courseUnits = popupView.findViewById(R.id.courseUnits);
                            TextView courseWhen = popupView.findViewById(R.id.courseWhen);
                            TextView courseDesc = popupView.findViewById(R.id.courseDesc);
                            Button sessionBtn = popupView.findViewById(R.id.sessionButton);

                            sessionBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("<SESSIONS BUTTON CLICKED>");
                                }
                            });

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
                    });

                    TextView newGradeTV = new TextView(container.getContext());
                    newGradeTV.setLayoutParams(paramsWrapTableRow);
                    newGradeTV.setText(newCourse.getStatus().toString());
                    newGradeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    newGradeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    newGradeTV.setGravity(Gravity.CENTER);

                    newRow.addView(newCourseTV);
                    newRow.addView(newDescTV);
                    newRow.addView(newGradeTV);
                    courseTable.addView(newRow);

                    if (i != record.getNumberOfCourses() - 1) {
                        courseTable.addView(divider);
                    }
                }
            }

            insideLinLayout.addView(courseTable);
            relativeLayout.addView(insideLinLayout);
            newCard.addView(relativeLayout);
            mLinearLayout.addView(newCard);
        }

    }
}
