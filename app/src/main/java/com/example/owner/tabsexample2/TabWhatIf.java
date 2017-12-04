package com.example.owner.tabsexample2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by j_spu on 12/2/2017.
 */

public class TabWhatIf  extends Fragment {

    private Spinner spMajor;

    //Possible Majors
    String majors[] = {"Art", "Accounting", "Chemistry", "Finance",
            "History", "Math", "Physics", "Speech Communication"};

    ArrayAdapter<String> adapterMajor;
    String sMajor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.what_if, container, false);

        spMajor = (Spinner) rootView.findViewById(R.id.whatIf_Spinner);

        adapterMajor = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, majors);

        adapterMajor.setDropDownViewResource(R.layout.spinner_dropdown_items);

        spMajor.setAdapter(adapterMajor);

        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sMajor = adapter.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(getActivity().getApplicationContext(),
                        "Selected Major : " + sMajor, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        return rootView;
    }
}
