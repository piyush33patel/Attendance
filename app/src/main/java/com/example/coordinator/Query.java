package com.example.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Query extends AppCompatActivity {

    private ProgressDialog progressDialog;
    ListView listView;
    JSONObject jobj;
    JsonArrayRequest jar;
    HashMap<String,String> hashMap;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);


        String uniques ="";
        String unique = getIntent().getStringExtra("unique");
        uniques = getIntent().getStringExtra("uniques");

        progressDialog = new ProgressDialog(Query.this);
        listView = (ListView)findViewById(R.id.query_list);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if(uniques != null)
            studentVia(uniques);
        else {
            jar = new JsonArrayRequest(Constants.ROOT_URL+ "UIETApp/" + unique, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.dismiss();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            hashMap = new HashMap<>();
                            jobj = response.getJSONObject(i);
                            String s1 = jobj.getString("rollno");
                            String s2 = jobj.getString("name");
                            hashMap.put("sno", "" + (i + 1));
                            hashMap.put("rollno", s1);
                            hashMap.put("name", s2);
                            arrayList.add(hashMap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String[] from = {"sno", "rollno", "name"};
                    int[] to = {R.id.serial_num, R.id.roll_no, R.id.st_name};
                    CustomSimpleAdapter csa = new CustomSimpleAdapter(getApplicationContext(), arrayList, R.layout.query_items, from, to);
                    listView.setAdapter(csa);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
            rq.add(jar);
        }

    }

    void studentVia(final String title){
        //function
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REPORT_STUDENT_VIA_TITLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray;
                        progressDialog.dismiss();
                        try {
                            jsonArray = new JSONArray(response);
                            for(int i = 0 ; i<jsonArray.length() ; i++)
                            {
                                try
                                {
                                    hashMap = new HashMap<>();
                                    jobj = jsonArray.getJSONObject(i);
                                    String s1 = jobj.getString("rollno");
                                    String s2 = jobj.getString("name");
                                    hashMap.put("sno", ""+(i+1));
                                    hashMap.put("rollno", s1);
                                    hashMap.put("name",s2);
                                    arrayList.add(hashMap);
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String[] from = {"sno","rollno", "name"};
                        int[] to = {R.id.serial_num, R.id.roll_no, R.id.st_name};
                        CustomSimpleAdapter csa = new CustomSimpleAdapter(getApplicationContext(),arrayList, R.layout.query_items, from, to);
                        listView.setAdapter(csa);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Something is Wrong", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title",title);
                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        //function
    }

}
