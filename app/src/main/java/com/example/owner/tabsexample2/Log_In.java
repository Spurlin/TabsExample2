package com.example.owner.tabsexample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.concurrent.ExecutionException;

/**
 * Created by j_spu on 11/5/2017.
 */

public class Log_In extends AppCompatActivity implements AsyncResponse {
    // Global Variables
    private String userName;
    private String pswd;
    private Button logIn;
    private EditText usrName_Btn;
    private EditText pswd_Btn;
    private String defaultEmail = "Email";
    private int failedAttemptCount = 0;

    private StudentRecord record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        usrName_Btn = (EditText) findViewById(R.id.name);
        pswd_Btn = (EditText) findViewById(R.id.password);

        logIn = (Button) findViewById(R.id.logIn);

        // if the user tries to log in
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered values for username and password
                userName = usrName_Btn.getText().toString();
                pswd = pswd_Btn.getText().toString();
                checkCredentials(userName,pswd);

        }});


    }

    private void checkCredentials(String userName, String password) {

        String type = "login";
        DBConnector dbConnector = new DBConnector(this);
        dbConnector.delegate = this;
        dbConnector.execute(type, userName, password);
        try {
            String result = dbConnector.get();
            System.out.println(result);
            String[] fields = result.split("~");
            System.out.println("Fields: " + fields.length);
            if(fields.length >= 5) { //Don't try this if we didn't get enough fields to set up the student.
                record = new StudentRecord(fields[1], fields[2], fields[3], fields[4]);//Start building the student record.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String result = dbConnector.getResults();

        System.out.println("<-RESULTS->");
        System.out.print(result);
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
        usrName_Btn.setText("");
        pswd_Btn.setText("");
    }

    // clears password if the user entered wrong info but still has
    // more tries
    private void clearPassword() { pswd_Btn.setText(""); failedAttemptCount++;}

    @Override
    public void processFinish(boolean res) {
        if (res) {
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
    }
}
