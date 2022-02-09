package com.abtl.rabbiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity {
    ListView user_item;
    ArrayList<UserListModel> userListModels;
    private ProgressDialog pd;
    String P_USER_INFO_ID = "", url_get_user_service = "http://103.91.54.60/OAA/getuserlist.php";
    View parentLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_item = findViewById(R.id.user_item);
        parentLayout = findViewById(android.R.id.content);

        sharedPreferences = getSharedPreferences("OAA_DATA", Context.MODE_PRIVATE);
        P_USER_INFO_ID = sharedPreferences.getString("USER_INFO_ID", "");
        userListModels = new ArrayList<>();

        getUserService();
        pd = ProgressDialog.show(MainActivity.this, "Getting Service Information",
                "Please wait...");
    }


    private void getUserService() {
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
                Toast.makeText(MainActivity.this, "Server Time Out", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("user_phone", "");
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
                user_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                           /* Intent intent = new Intent(MainActivity.this, MapsPage.class);
                            intent.putExtra("USER_INFO_ID_SP", listtofcustomer.get(i).getServiceByID());
                            intent.putExtra("USER_INFO_NAME_SP", listtofcustomer.get(i).getServiceByName());
                            startActivity(intent);*/
                    }
                });

            } else {
                if (pd != null) {
                    pd.dismiss();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

}