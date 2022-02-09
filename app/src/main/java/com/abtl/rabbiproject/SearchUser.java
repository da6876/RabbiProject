package com.abtl.rabbiproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchUser extends AppCompatActivity {
    Button btnShowReport;
    EditText edt_phone_number;
    View parentLayout;
    ListView user_item;
    ArrayList<UserListModel> userListModels =  new ArrayList<>();
    private ProgressDialog pd;
    String P_USER_INFO_ID = "", url_get_user_service = "http://103.91.54.60/OAA/getuserlist.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        edt_phone_number = findViewById(R.id.edt_phone_number);
        btnShowReport = findViewById(R.id.btnShowReport);
        user_item = findViewById(R.id.user_item);
        parentLayout = findViewById(android.R.id.content);

        btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edt_phone_number.getText().toString().equals("")){
                    getUserService(edt_phone_number.getText().toString());
                }else{
                    showSnankBar("Enter The Phone No !!");
                }

            }
        });
    }

    private void getUserService(String phone) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_user_service, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parcaeLoginResponse(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showSnankBar(error.toString());
                if (pd != null) {
                    pd.dismiss();
                }
                Toast.makeText(SearchUser.this, "Server Time Out", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("user_phone", phone);
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void parcaeLoginResponse(String responce) {
        try {
            JSONObject jsonObject1 = new JSONObject(responce);
            String status_code = jsonObject1.getString("status_code");
            String msg = jsonObject1.getString("msg");
            String values = jsonObject1.getString("values");
            if (status_code.equals("200")) {
                if (pd != null) {
                    pd.dismiss();
                }
                userListModels.clear();
                JSONArray jsonArray = new JSONArray(values.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    UserListModel serviceList = new UserListModel(
                            jsonObject2.getString("user_info_id"),
                            jsonObject2.getString("location_code"),
                            jsonObject2.getString("user_info_name"),
                            jsonObject2.getString("user_fast_name"),
                            jsonObject2.getString("user_last_name"),
                            jsonObject2.getString("user_password"),
                            jsonObject2.getString("user_phone"),
                            jsonObject2.getString("user_email"),
                            jsonObject2.getString("user_refer_no"),
                            jsonObject2.getString("user_point"),
                            jsonObject2.getString("user_address"),
                            jsonObject2.getString("user_latitude"),
                            jsonObject2.getString("user_longitude"),
                            jsonObject2.getString("user_status"),
                            jsonObject2.getString("create_data")
                    );
                    userListModels.add(serviceList);
                }
                UserListAdapter serviceAdapter = new UserListAdapter(getApplicationContext(), R.layout.service_adapter, userListModels);
                user_item.setAdapter(serviceAdapter);


            } else {
                if (pd != null) {
                    pd.dismiss();
                }
                alertDialog(SearchUser.this,"No Data Found !!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (pd != null) {
                pd.dismiss();
            }
        }

    }


    public void  alertDialog(Context context, String message) {new AlertDialog.Builder(context).setMessage(message).setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            }).show();
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
}