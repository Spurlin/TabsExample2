package com.example.owner.tabsexample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by j_spu on 11/5/2017.
 */

public class Log_In extends AppCompatActivity {

    // Global Variables
    private String userName;
    private String pswd;
    private Button logIn;
    private EditText usrName_Btn;
    private EditText pswd_Btn;
    private String defaultEmail = "Email";
    private int failedAttemptCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        usrName_Btn = (EditText) findViewById(R.id.name);
        pswd_Btn = (EditText) findViewById(R.id.password);

        logIn = (Button) findViewById(R.id.logIn);

        // clears the defaulted value for the user name box
        // when user taps on it to enter something
        usrName_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrName_Btn.setText("");;
            }});

        // if the user tries to log in
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered values for username and password
                userName = usrName_Btn.getText().toString();
                pswd = pswd_Btn.getText().toString();

                // if username entered successful credentials
                if (checkCredentials(userName, pswd) ) {
                    Toast.makeText(Log_In.this, "Logged In", Toast.LENGTH_SHORT).show();
                    successLogIn();
                } else {
                    // if the user has entered wrong info for the first two times
                    if (failedAttemptCount < 2) {
                        Toast.makeText(Log_In.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        clearPassword();
                    } else if (failedAttemptCount == 2) {
                        // if user entered wrong info and has one try left for that username
                        Toast.makeText(Log_In.this, "Incorrect Credentials. You are 1 try away from being locked out of this account.", Toast.LENGTH_SHORT).show();
                        clearPassword();
                    } else {
                        // if user enterd wrong credentials all four times
                        Toast.makeText(Log_In.this, "Incorrect Credentials. You are currently locked out of this account.", Toast.LENGTH_SHORT).show();
                        clearAllCredentials();
                    }


                }
        }});


    }

    private boolean checkCredentials(String userName, String password) {

        // will be changed to the username and password for the student from
        // the database
        String name = "jspurlin";
        String pass = "test";

        // checks if the username and password are correct
        if (userName.equals(name) && password.equals(pass)) { return true; }
        else { return false; }

    }

    // user performed a successful log in
    // clear the username and password box for security purposes
    // and start the app at the current courses page
    private void successLogIn() {

        clearAllCredentials();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // clears all credentials if the user entered wrong
    // info more than four times
    private void clearAllCredentials() {
        usrName_Btn.setText(defaultEmail);
        pswd_Btn.setText("");
    }

    // clears password if the user entered wrong info but still has
    // more tries
    private void clearPassword() { pswd_Btn.setText(""); failedAttemptCount++;}

}
