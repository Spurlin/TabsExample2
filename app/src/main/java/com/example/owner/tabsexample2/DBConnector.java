package com.example.owner.tabsexample2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by lwu on 11/10/2017.
 */

public class DBConnector extends AsyncTask<String,Void,String> implements Serializable {
    public AsyncResponse delegate = null;
    private String result = "";
    Context context;
    AlertDialog alertDialog;
    DBConnector (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://cosc5384.us/teamas/login.php";
        String allcourses_url = "http://cosc5384.us/teamas/allcourses.php";
        String requirements_url = "http://cosc5384.us/teamas/requirements.php";
        String subrequirements_url = "http://cosc5384.us/teamas/subrequirements.php";
        String degreenames_url = "http://cosc5384.us/teamas/degreenames.php";
        String classes_url = "http://cosc5384.us/teamas/classes.php";

        if(type.equals("login")) {
            try {
                String email = params[1];
                String password = params[2];
                URL url = new URL(login_url);

                System.out.println("<EMAIL> " + email);
                System.out.println("<PASSWORD>" + password);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("allcourses")) {
            try {
                String stuId = params[1];
                URL url = new URL(allcourses_url);

                System.out.println("<STUID> " + stuId);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("stu_id","UTF-8")+"="+URLEncoder.encode(stuId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("requirements"))
        {
            try {
                String majorName = params[1];
                URL url = new URL(requirements_url);

                System.out.println("<MAJORNAME> " + majorName);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("majorName","UTF-8")+"="+URLEncoder.encode(majorName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("subrequirements"))
        {
            try {
                String rqCode = params[1];
                URL url = new URL(subrequirements_url);

                System.out.println("<RQCODE> " + rqCode);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("rqCode","UTF-8")+"="+URLEncoder.encode(rqCode,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("degreenames"))
        {
            try {
                URL url = new URL(degreenames_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("classes"))
        {
            try {
                String courseCode = params[1];
                URL url = new URL(classes_url);

                System.out.println("<COURSECODE> " + courseCode);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("courseCode","UTF-8")+"="+URLEncoder.encode(courseCode,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;

                    System.out.println("<CLASSES RESULT> " + result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

        //alertDialog.setMessage(result);
        //alertDialog.show();
        //if connection error occurs, throw an error message
        if(result.contentEquals("connecterror")) {
            delegate.processFinish(false);
            //Toast toast= Toast.makeText(context, "Login Error", Toast.LENGTH_SHORT);
            //toast.show();
        }

        //if connection succeeds, move to your next activity
        else
        {
            delegate.processFinish(true);
            //Toast toast= Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT);
            //toast.show();
            //context.startActivity(new Intent(context, MainActivity.class));
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public String getResults() { return result; }
}