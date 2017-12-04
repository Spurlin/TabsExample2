package com.example.owner.tabsexample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
=======
import android.widget.TextView;
>>>>>>> origin/master

/**
 * Created by Owner on 10/18/2017.
 */

public class TabMajor extends Fragment implements Serializable {

    private StudentRecord record;
    private PopupWindow popupWindow;
    private DrawerLayout mainLayout;
    private int i;
    private float credsEarned;
    private float creditsLeft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        View rootView = inflater.inflate(R.layout.tab_major, container, false);

        Intent intent = getActivity().getIntent();

        final StudentRecord record = (StudentRecord) intent.getSerializableExtra("StudentRecord");
        TextView majorTV = (TextView) rootView.findViewById(R.id.majorName);
        majorTV.setText("B.S. Computer Science");

        TextView ratio = (TextView) rootView.findViewById(R.id.ratio);
        ProgressBar mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);


=======
        View rootView = inflater.inflate(R.layout.tab_all, container, false);
        TextView title = (TextView)rootView.findViewById(R.id.pageName);
        title.setText("Courses for Major");
>>>>>>> origin/master
        return rootView;
    }
}
