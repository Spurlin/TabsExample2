package com.example.owner.tabsexample2;

/**
 * Created by Owner on 10/18/2017.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class TabAll extends Fragment implements Serializable {

    private StudentRecord record;
    private PopupWindow popupWindow;
    private DrawerLayout mainLayout;
    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_all, container, false);
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();

        final StudentRecord record = (StudentRecord) intent.getSerializableExtra("StudentRecord");

        LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.all_main_LinLayout);

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

            TableLayout courseTable = new TableLayout(container.getContext());
            courseTable.setLayoutParams(paramsMatchTable);
            courseTable.setStretchAllColumns(true);

            TableRow headerRow = new TableRow(container.getContext());
            headerRow.setLayoutParams(paramsMatchTableRow);
            headerRow.setPadding(0, 0, 0, 0);
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
            courseTable.addView(headerRow);

        for (i = 0; i < record.getNumberOfCourses(); i++) {

            TableRow newRow = new TableRow(container.getContext());
            newRow.setLayoutParams(paramsMatchTableRow);
            newRow.setPadding(0, 50, 0, 50);
            newRow.setGravity(Gravity.CENTER);

            View divider = new View(container.getContext());
            divider.setLayoutParams(paramsLine);
            divider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            divider.setElevation(5);

            paramsWrapTableRow.weight=1;

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
            newDescTV.setOnClickListener( new View.OnClickListener() {

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
            });

            TextView newGradeTV = new TextView(container.getContext());
            newGradeTV.setLayoutParams(paramsWrapTableRow);
            newGradeTV.setText(record.getCourseStatus(i));
            newGradeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            newGradeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14 );
            newGradeTV.setGravity(Gravity.CENTER);

            newRow.addView(newCourseTV);
            newRow.addView(newDescTV);
            newRow.addView(newGradeTV);
            courseTable.addView(newRow);

            if (i != record.getNumberOfCourses() - 1) { courseTable.addView(divider); }
        }

        insideLinLayout.addView(courseTable);
        relativeLayout.addView(insideLinLayout);
        newCard.addView(relativeLayout);
        mLinearLayout.addView(newCard);

        return rootView;
    }

}