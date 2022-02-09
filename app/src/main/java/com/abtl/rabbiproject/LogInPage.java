package com.abtl.rabbiproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogInPage extends AppCompatActivity {
    TextInputEditText edt_phone_number, edt_Password;
    View parentLayout;
    private ProgressDialog pd;
    FloatingActionButton fab_log_in;
    public static String UrlPath = "http://103.91.54.60/OAA/";
    String str_Login_status="",loginUrl = UrlPath+"userlogin.php";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        sharedPreferences = getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        str_Login_status=sharedPreferences.getString("LOGIN_STATUS","0");

        parentLayout = findViewById(android.R.id.content);

        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_Password = findViewById(R.id.edt_Password);
        edt_Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                checkLoginInfo();
                return false;
            }
        });
        fab_log_in = findViewById(R.id.fab_log_in);
        fab_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginInfo();
            }
        });
    }

    private void checkLoginInfo(){
        if (!isInternetAvailable())
        {
            alertDialog(LogInPage.this,"Message","Check Your Internet Connection !!");
        }else {

                if (!edt_phone_number.getText().toString().trim().equals("")) {
                    if (!edt_Password.getText().toString().trim().equals("")) {

                        loginUser();
                        pd = ProgressDialog.show(LogInPage.this, "Checking Information",
                                "Please wait...");
                    } else {
                        alertDialog(LogInPage.this, "Message", "Enter Your Email or Phone No");
                    }
                } else {
                    alertDialog(LogInPage.this, "Message", "Enter The Password");
                }
            }
        }


    private void loginUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parcaeLoginResponse(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showSnankBar(error.toString());

                alertDialog(LogInPage.this,"Sorry","Connection Time Out !");
                if (pd != null) {
                    pd.dismiss();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("user_phone", edt_phone_number.getText().toString().trim());
                parameters.put("user_password", edt_Password.getText().toString().trim());
                return parameters;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void parcaeLoginResponse(String responce) {

        if (pd != null) {
            pd.dismiss();
        }
        try {
            JSONObject jsonObject1 = new JSONObject(responce);
            String status_code = jsonObject1.getString("status_code");
            String msg = jsonObject1.getString("msg");
            String values = jsonObject1.getString("values");
            if (status_code.equals("200")) {
                if (pd != null) {
                    pd.dismiss();
                }
                JSONArray jsonArray=new JSONArray(values.toString());
                for (int i =0; i< jsonArray.length();i++) {
                    JSONObject jsonObject2 =jsonArray.getJSONObject(i);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_info_id", jsonObject2.getString("user_info_id"));
                    editor.putString("user_info_name", jsonObject2.getString("user_info_name"));
                    editor.putString("user_phone", jsonObject2.getString("user_phone"));
                    editor.putString("user_email", jsonObject2.getString("user_email"));
                    editor.commit();
                    editor.apply();

                    editor.commit();
                }
                startActivity(new Intent(LogInPage.this,HomePage.class));
                finish();

            }else{
                if (pd != null) {
                    pd.dismiss();
                }

                alertDialog(LogInPage.this,msg  ,values);
            }
        } catch (JSONException e) {
            if (pd != null) {
                pd.dismiss();
            }
        }

    }

    public void showSnankBar(String value) {
        Snackbar.make(parentLayout, value, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) LogInPage.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;

    }

    public void  alertDialog(Context context, String title, String message) {new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            }).show();
    }

}