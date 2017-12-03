package com.example.owner.tabsexample2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Owner on 10/18/2017.
 */

public class TabMinor extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_all, container, false);
        TextView title = (TextView)rootView.findViewById(R.id.pageName);
        title.setText("Courses for Minor");
        return rootView;
    }
}